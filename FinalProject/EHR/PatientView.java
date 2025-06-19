package FinalProject.EHR;

import java.util.List;

public class PatientView {
    private PatientService patientService;
    private DoctorService doctorService;

    public PatientView(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            try {
                MenuUtil.clearScreen();
                System.out.println(
                    "+=========================================+\n" +
                    "|        PATIENT MANAGEMENT SYSTEM        |\n" +
                    "+=========================================+\n" +
                    "|  [1] View Patient Records               |\n" +
                    "|  [2] Create New Records                 |\n" +
                    "|  [3] Update Patient Records             |\n" +
                    "|  [4] Search Patient Records             |\n" +
                    "|  [5] Remove Existing Record             |\n" +
                    "|  [6] Back To Main Menu                  |\n" +
                    "+=========================================+"
                );
                int choice = InputValidator.getIntInput("Enter your choice: ", 1, 6);
                switch (choice) {
                    case 1: 
                        viewPatients();
                        break;
                    case 2: 
                        createPatient();
                        break;
                    case 3: 
                        updatePatient();
                        break;
                    case 4: 
                        searchPatient();
                        break;
                    case 5: 
                        archivePatient();
                        break;
                    case 6: 
                        back = true;
                        break;
                    default:
                        System.out.println(ConsoleColors.RED + "Invalid choice!" + ConsoleColors.RESET);
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "An error occurred: " + e.getMessage() + ConsoleColors.RESET);
                InputValidator.pressEnterToContinue();
            }
        }
    }

    private void viewPatients() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|       LIST OF PATIENT RECORDS       |\n" +
                "+=====================================+"
            );
            
            List<Patient> patients = patientService.getAllPatients();
            if (patients.isEmpty()) {
                System.out.println(ConsoleColors.RED + "\nNo patients found" + ConsoleColors.RESET);
            } else {
                for (Patient p : patients) {
                    Doctor doctor = doctorService.getDoctorById(p.getDoctorId());
                    String doctorName = (doctor != null) ? doctor.getName() : "No doctor assigned";
                    
                    System.out.println(
                        ConsoleColors.PURPLE + "ID: " + ConsoleColors.RESET + p.getId() + "\n" +
                        ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET + p.getName() + "\n" +
                        ConsoleColors.CYAN + "Age: " + ConsoleColors.RESET + p.getAge() + "\n" +
                        ConsoleColors.CYAN + "Birthday: " + ConsoleColors.RESET + p.getDob() + "\n" +
                        ConsoleColors.CYAN + "Gender: " + ConsoleColors.RESET + p.getGender() + "\n" +
                        ConsoleColors.CYAN + "Address: " + ConsoleColors.RESET + p.getAddress() + "\n" +
                        ConsoleColors.CYAN + "Contact: " + ConsoleColors.RESET + p.getContact() + "\n" +
                        ConsoleColors.CYAN + "Emergency Contact: " + ConsoleColors.RESET + p.getEmergencyContact() + "\n" +
                        ConsoleColors.CYAN + "Allergies: " + ConsoleColors.RESET + p.getAllergies() + "\n" +
                        ConsoleColors.CYAN + "Current Medications: " + ConsoleColors.RESET + p.getCurrentMeds() + "\n" +
                        ConsoleColors.CYAN + "Medical History: " + ConsoleColors.RESET + p.getMedicalHistory() + "\n" +
                        ConsoleColors.CYAN + "Diagnosis: " + ConsoleColors.RESET + p.getDiagnosis() + "\n" +
                        ConsoleColors.CYAN + "Treatment Plan: " + ConsoleColors.RESET + p.getTreatmentPlan() + "\n" +
                        ConsoleColors.CYAN + "Assigned Doctor: " + ConsoleColors.RESET + doctorName + "\n" +
                        "--------------------------------------"
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error displaying patients: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }

    private void createPatient() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|       CREATE PATIENT RECORDS        |\n" +
                "+=====================================+"
            );
            
            int id = patientService.getNextId();
            System.out.println(ConsoleColors.PURPLE + "Patient ID: " + ConsoleColors.RESET + id);
            
            String name = InputValidator.getValidLetterInput("Full Name: ");
            int age = InputValidator.getIntInput("Age: ", 0, 100);
            String dob = InputValidator.getRequiredStringInput("Date of Birth: ");
            String gender = InputValidator.getValidGenderInput("Gender: ");
            long contact = InputValidator.getLongInput("Contact: ",1000000000L, 99999999999L);
            String address = InputValidator.getRequiredStringInput("Address: ");
            long emergency = InputValidator.getLongInput("Emergency Contact: ", 1000000000L, 99999999999L);
            String allergies = InputValidator.getValidLetterInput("Allergies: ");
            String meds = InputValidator.getValidLetterInput("Current Medications: ");
            String history = InputValidator.getValidLetterInput("Medical History: ");
            String diagnosis = InputValidator.getValidLetterInput("Diagnosis: ");
            String treatment = InputValidator.getValidLetterInput("Treatment Plan: ");
            
            System.out.println("\nAvailable Doctors:");
            List<Doctor> doctors = doctorService.getAllDoctors();
            if (doctors.isEmpty()) {
                System.out.println(ConsoleColors.RED + "No doctors available to assign!" + ConsoleColors.RESET);
                InputValidator.pressEnterToContinue();
                return;
            }
            
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
                int doctorId;
                Doctor assignedDoctor;
        do {
            doctorId = InputValidator.getIntInput("Assign Doctor ID: ", 1, Integer.MAX_VALUE);
            assignedDoctor = doctorService.getDoctorById(doctorId);
            if (assignedDoctor == null) {
                System.out.println(ConsoleColors.RED + "Invalid Doctor ID! Please try Again." + ConsoleColors.RESET);
            }
        } while (assignedDoctor == null);
            
            Patient patient = new Patient(id, name, age, dob, gender, contact, address, 
                                        emergency, allergies, meds, history, 
                                        diagnosis, treatment, doctorId);
            patientService.addPatient(patient);
            System.out.println();
            System.out.println(ConsoleColors.GREEN + "Patient added successfully!" + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error adding patient: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }
    private void updatePatient() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|       UPDATE PATIENT RECORDS        |\n" +
                "+=====================================+"
            );
            
            int id = InputValidator.getIntInput("Enter Patient ID: ", 1, Integer.MAX_VALUE);
            Patient patient = patientService.getPatientById(id);
            
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "Patient not found" + ConsoleColors.RESET);
                return;
            }
            
            System.out.println("\nCurrent Information:");
            System.out.println(patient);
            
            System.out.println("\nEnter new values (leave blank to keep current):");
            String name = InputValidator.getStringInput("Name [" + patient.getName() + "]: ");
            String ageInput = InputValidator.getStringInput("Age [" + patient.getAge() + "]: ");
            int age = ageInput.isEmpty() ? patient.getAge() : Integer.parseInt(ageInput);
            String dob = InputValidator.getStringInput("DOB [" + patient.getDob() + "]: ");
            String gender = InputValidator.getStringInput("Gender [" + patient.getGender() + "]: ");
            String contactInput = InputValidator.getStringInput("Contact [" + patient.getContact() + "]: ");
            long contact = contactInput.isEmpty() ? patient.getContact() : Long.parseLong(contactInput);
            String address = InputValidator.getStringInput("Address [" + patient.getAddress() + "]: ");
            String emergencyInput = InputValidator.getStringInput("Emergency [" + patient.getEmergencyContact() + "]: ");
            long emergency = emergencyInput.isEmpty() ? patient.getEmergencyContact() : Long.parseLong(emergencyInput);
            String allergies = InputValidator.getStringInput("Allergies [" + patient.getAllergies() + "]: ");
            String meds = InputValidator.getStringInput("Meds [" + patient.getCurrentMeds() + "]: ");
            String history = InputValidator.getStringInput("History [" + patient.getMedicalHistory() + "]: ");
            String diagnosis = InputValidator.getStringInput("Diagnosis [" + patient.getDiagnosis() + "]: ");
            String treatment = InputValidator.getStringInput("Treatment [" + patient.getTreatmentPlan() + "]: ");
            
            Patient updated = new Patient(
                patient.getId(),
                name.isEmpty() ? patient.getName() : name,
                age,
                dob.isEmpty() ? patient.getDob() : dob,
                gender.isEmpty() ? patient.getGender() : gender,
                contact,
                address.isEmpty() ? patient.getAddress() : address,
                emergency,
                allergies.isEmpty() ? patient.getAllergies() : allergies,
                meds.isEmpty() ? patient.getCurrentMeds() : meds,
                history.isEmpty() ? patient.getMedicalHistory() : history,
                diagnosis.isEmpty() ? patient.getDiagnosis() : diagnosis,
                treatment.isEmpty() ? patient.getTreatmentPlan() : treatment,
                patient.getDoctorId()
            );
            
            patientService.updatePatient(updated);
            System.out.println(ConsoleColors.GREEN + "Patient updated!" + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error updating patient: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }
    
    private void searchPatient() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|        SEARCH PATIENT RECORDS       |\n" +
                "+=====================================+"
            );
            
            int id = InputValidator.getIntInput("Enter Patient ID: ", 1, Integer.MAX_VALUE);
            Patient patient = patientService.getPatientById(id);
            
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "Patient not found" + ConsoleColors.RESET);
            } else {
                System.out.println("\nPatient Record:");
                System.out.println(patient);
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error searching patient: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }
    
    private void archivePatient() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|        ARCHIVE PATIENT RECORD       |\n" +
                "+=====================================+"
            );
            
            System.out.println(ConsoleColors.YELLOW + "\nNote: Patient records will be archived, not deleted.");
            System.out.println("Archived records can be accessed through the Archived Records menu." + ConsoleColors.RESET);
            
            if (!InputValidator.getYesNoInput("\nDo you understand this process? (yes/no): ")) {
                System.out.println(ConsoleColors.RED + "\nCannot proceed without confirmation." + ConsoleColors.RESET);
                InputValidator.pressEnterToContinue();
                return;
            }

            int id = InputValidator.getIntInput("\nEnter Patient ID to archive: ", 1, Integer.MAX_VALUE);
            Patient patient = patientService.getPatientById(id);
            
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "\nPatient not found." + ConsoleColors.RESET);
                return;
            }
            
            System.out.println("\nPatient to archive:");
            System.out.println(
            ConsoleColors.PURPLE + "ID: " + ConsoleColors.RESET + patient.getId() + " | " +
            ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET + patient.getName() + " | " +
            ConsoleColors.CYAN + "Gender: " + ConsoleColors.RESET + patient.getGender()
            );
            
            boolean confirm = InputValidator.getYesNoInput(ConsoleColors.RED + 
                "\nConfirm archiving this record? (yes/no): " + ConsoleColors.RESET);
            
            if (confirm) {
                patientService.archivePatient(id);
                System.out.println(ConsoleColors.GREEN + "\nArchiving patient record...");
                Thread.sleep(1000);
                System.out.println("Patient record has been successfully archived!" + ConsoleColors.RESET);
            } else {
                System.out.println("Archiving cancelled");
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error archiving patient: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }
    
    public void viewArchivedPatients() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|      ARCHIVED PATIENT RECORDS       |\n" +
                "+=====================================+"
            );
            
            List<Patient> patients = patientService.getArchivedPatients();
            if (patients.isEmpty()) {
                System.out.println(ConsoleColors.RED + "\nNo archived records found." + ConsoleColors.RESET);
            } else {
                System.out.println("\nArchived Patients:");
                for (Patient p : patients) {
                    System.out.println(
                    ConsoleColors.PURPLE + "ID: " + ConsoleColors.RESET + p.getId() + " | " +  
                    ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET + p.getName() + " | " +
                    ConsoleColors.CYAN + "Gender: " + ConsoleColors.RESET + p.getGender()
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error viewing archived records: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }
    
    public void searchArchivedPatient() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|    SEARCH ARCHIVED PATIENT RECORD   |\n" +
                "+=====================================+"
            );
            
            int id = InputValidator.getIntInput("\nEnter Archived Patient ID: ", 1, Integer.MAX_VALUE);
            Patient patient = patientService.getArchivedPatientById(id);
            
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "\nArchived patient not found." + ConsoleColors.RESET);
            } else {
                System.out.println("\nArchived Patient Record:");
                System.out.println(patient);
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error searching archived records: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }
}