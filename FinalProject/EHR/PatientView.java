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
                System.out.println(ConsoleColors.GREEN + "=====PATIENT MANAGEMENT=====" + ConsoleColors.RESET);
                System.out.println("[1] View Patient Records");
                System.out.println("[2] Create New Records");
                System.out.println("[3] Update Patient Records");
                System.out.println("[5] Search Patient Records");
                System.out.println("[4] Remove Existing Record");
                System.out.println("[6] Back To Menu");
                System.out.println(ConsoleColors.GREEN + "============================" + ConsoleColors.RESET);
                int choice = InputValidator.getIntInput("Enter choice: ", 1, 6);
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
                        deletePatient();
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
        System.out.println(ConsoleColors.GREEN + "=====LIST OF PATIENTS=====" + ConsoleColors.RESET);
        
        List<Patient> patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println(ConsoleColors.RED + "\nNo patients found" + ConsoleColors.RESET);
        } else {
            for (Patient p : patients) {
                Doctor doctor = doctorService.getDoctorById(p.getDoctorId());
                String doctorName = (doctor != null) ? doctor.getName() : "No doctor assigned";
                
                System.out.println(
                    ConsoleColors.CYAN + "ID: " + ConsoleColors.RESET + p.getId() + "\n" +
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
        System.out.println(ConsoleColors.GREEN + "=====CREATE PATIENT RECORDS=====" + ConsoleColors.RESET);
        
        int id = patientService.getNextId();
        System.out.println("Patient ID: " + ConsoleColors.CYAN + id + ConsoleColors.RESET);
        
        String name = InputValidator.getStringInput("Full Name: ");
        int age = InputValidator.getIntInput("Age: ", 0, 120); 
        String dob = InputValidator.getStringInput("Date of Birth: ");
        String gender = InputValidator.getStringInput("Gender: ");
        String contact = InputValidator.getStringInput("Contact: ");
        String address = InputValidator.getStringInput("Address: ");
        String emergency = InputValidator.getStringInput("Emergency Contact: ");
        String allergies = InputValidator.getStringInput("Allergies: ");
        String meds = InputValidator.getStringInput("Current Medications: ");
        String history = InputValidator.getStringInput("Medical History: ");
        String diagnosis = InputValidator.getStringInput("Diagnosis: ");
        String treatment = InputValidator.getStringInput("Treatment Plan: ");
        
        System.out.println("\nAvailable Doctors:");
        List<Doctor> doctors = doctorService.getAllDoctors();
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
        int doctorId = InputValidator.getIntInput("Assign Doctor ID: ", 1, Integer.MAX_VALUE);
        
        Patient patient = new Patient(id, name, age, dob, gender, contact, address, 
                                    emergency, allergies, meds, history, 
                                    diagnosis, treatment, doctorId);
        patientService.addPatient(patient);
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
        System.out.println(ConsoleColors.GREEN + "=====UPDATE PATIENT RECORDS=====" + ConsoleColors.RESET);
        
        int id = InputValidator.getIntInput("Enter Patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = patientService.getPatientById(id);
        
        if (patient == null) {
            System.out.println(ConsoleColors.RED + "Patient not found" + ConsoleColors.RESET);
            InputValidator.pressEnterToContinue();
            return;
        }
        
        System.out.println("\nCurrent Information:");
        System.out.println(patient);
        
        System.out.println("\nEnter new values (leave blank to keep current):");
        String name = InputValidator.getStringInput("Name [" + patient.getName() + "]: ");
        String ageInput = InputValidator.getStringInput("Age [" + patient.getAge() + "]: "); // Get as string first
        int age = ageInput.isEmpty() ? patient.getAge() : Integer.parseInt(ageInput); // Then parse if not empty
        String dob = InputValidator.getStringInput("DOB [" + patient.getDob() + "]: ");
        String gender = InputValidator.getStringInput("Gender [" + patient.getGender() + "]: ");
        String contact = InputValidator.getStringInput("Contact [" + patient.getContact() + "]: ");
        String address = InputValidator.getStringInput("Address [" + patient.getAddress() + "]: ");
        String emergency = InputValidator.getStringInput("Emergency [" + patient.getEmergencyContact() + "]: ");
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
            contact.isEmpty() ? patient.getContact() : contact,
            address.isEmpty() ? patient.getAddress() : address,
            emergency.isEmpty() ? patient.getEmergencyContact() : emergency,
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
    
    private void deletePatient() {
        try {
            MenuUtil.clearScreen();
            System.out.println(ConsoleColors.GREEN + "=====REMOVE PATIENT RECORD=====" + ConsoleColors.RESET);
            
            int id = InputValidator.getIntInput("Enter Patient ID: ", 1, Integer.MAX_VALUE);
            Patient patient = patientService.getPatientById(id);
            
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "Patient not found" + ConsoleColors.RESET);
                InputValidator.pressEnterToContinue();
                return;
            }
            
            System.out.println("\nPatient to delete:");
            System.out.println(patient);
            
            boolean confirm = InputValidator.getYesNoInput(ConsoleColors.RED + 
                "Confirm deletion? (yes/no): " + ConsoleColors.RESET);
            
            if (confirm) {
                patientService.deletePatient(id);
                System.out.println(ConsoleColors.GREEN + "Patient deleted!" + ConsoleColors.RESET);
            } else {
                System.out.println("Deletion cancelled");
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error deleting patient: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }

    private void searchPatient() {
        try {
            MenuUtil.clearScreen();
            System.out.println(ConsoleColors.GREEN + "=====SEARCH PATIENT RECORDS=====" + ConsoleColors.RESET);
            
            int id = InputValidator.getIntInput("Enter Patient ID: ", 1, Integer.MAX_VALUE);
            Patient patient = patientService.getPatientById(id);
            
            if (patient == null) {
                System.out.println(ConsoleColors.RED + "Patient not found" + ConsoleColors.RESET);
            } else {
                System.out.println("\nPatient Record:");
                System.out.println(patient);
                
                Doctor doctor = doctorService.getDoctorById(patient.getDoctorId());
                if (doctor != null) {
                    System.out.println("\nAssigned Doctor:");
                    System.out.println(doctor);
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error searching patient: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }
}