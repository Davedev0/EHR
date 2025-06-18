package FinalProject;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class EHRSYSTEM {
    
    public static void start() {
        try {
            displayIntro();
            
            DoctorService doctorService = new DoctorService();
            PatientService patientService = new PatientService(doctorService);
            
            PatientView patientView = new PatientView(patientService, doctorService);
            DoctorView doctorView = new DoctorView(doctorService, patientView);
                
            MenuUtil.displayWelcomeMessage();
            
            boolean running = true;
            while (running) {
                try {
                    MenuUtil.displayMainMenu();
                    int choice = InputValidator.getIntInput("Enter your choice: ", 1, 4);
                    
                    switch (choice) {
                        case 1: 
                            patientView.displayMenu();
                            break;
                        case 2: 
                            doctorView.displayMenu();
                            break;
                        case 3: 
                            MenuUtil.displayAbout();
                            break;
                        case 4: 
                            running = false;
                            break;
                        default:
                            System.out.println(ConsoleColors.RED + "Invalid choice!" + ConsoleColors.RESET);
                    }
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED + "An error occurred: " + e.getMessage() + ConsoleColors.RESET);
                    InputValidator.pressEnterToContinue();
                }
            }
            
            System.out.println(ConsoleColors.BLUE + "\nThank you for using the Electronic Health Records System!" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.YELLOW + "System shutdown successfully." + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "A critical error occurred: " + e.getMessage() + ConsoleColors.RESET);
            System.out.println("Please contact system administrator.");
        }
    }

    private static void displayIntro() {
        System.out.println(
             " ______   _    _   _____  \n" +
             "|  ____| | |  | | |  __ \\\n" +
             "| |__    | |__| | | |__) |\n" +
             "|  __|   |  __  | |  _  / \n" +
             "| |____  | |  | | | | \\ \\ \n" +
             "|______| |_|  |_| |_|  \\_\\\n" 
              
        );
        System.out.print(ConsoleColors.YELLOW + "Please wait to continue");
        
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(900);
                System.out.print(".");
                System.out.flush();
            }
            System.out.print(ConsoleColors.RESET);
            Thread.sleep(1000); 
            MenuUtil.clearScreen();
        } catch (InterruptedException e) {
            System.out.print(e);
        }
    }
}

class ConsoleColors {
    public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[0;30m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";
    public static final String BLACK_BOLD = "\033[1;30m";
    public static final String RED_BOLD = "\033[1;31m";
    public static final String GREEN_BOLD = "\033[1;32m";
    public static final String YELLOW_BOLD = "\033[1;33m";
    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String PURPLE_BOLD = "\033[1;35m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String WHITE_BOLD = "\033[1;37m";
}

class Doctor {
    private int id;
    private String name;
    private String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return ConsoleColors.PURPLE + "ID: " + ConsoleColors.RESET + id + " | " +
               ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET + name;
    }
}

class DoctorService {
    private List<Doctor> doctors = new ArrayList<>();
    private int nextId = 1;  

    public DoctorService() {
        initializeDoctors();
    }

    private void initializeDoctors() {
        addDoctor(new Doctor(nextId++, "Dr. Anggit Kopal", "Cardiology"));
        addDoctor(new Doctor(nextId++, "Dr. Mark Rentoria", "Neurology"));
        addDoctor(new Doctor(nextId++, "Dr. John Quidan", "Pediatrics"));
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        if (doctor.getId() >= nextId) {
            nextId = doctor.getId() + 1;
        }
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }

    public Doctor getDoctorById(int id) {
        try {
            for (Doctor doctor : doctors) {
                if (doctor.getId() == id) {
                    return doctor;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error finding doctor: " + e.getMessage() + ConsoleColors.RESET);
            return null;
        }
    }

    public int getNextId() {
        return nextId++;
    }
}

class DoctorView {
    private DoctorService doctorService;
    private PatientView patientView;

    public DoctorView(DoctorService doctorService, PatientView patientView) {
        this.doctorService = doctorService;
        this.patientView = patientView;
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            try {
                MenuUtil.clearScreen();
                System.out.println(
                    "+=====================================+\n" +
                    "|           DOCTOR MANAGEMENT         |\n" +
                    "+=====================================+\n" +
                    "|  [1] View All Doctors               |\n" +
                    "|  [2] Add New Doctor                 |\n" +
                    "|  [3] Archived Records               |\n" +
                    "|  [4] Back To Main Menu              |\n" +
                    "+=====================================+"
                );
                int choice = InputValidator.getIntInput("Enter your choice: ", 1, 4);
                switch (choice) {
                    case 1: 
                        viewDoctors();
                        break;
                    case 2: 
                        addDoctor();
                        break;
                    case 3: 
                        displayArchiveMenu();
                        break;
                    case 4: 
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

    private void viewDoctors() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|         LIST OF ALL DOCTORS         |\n" +
                "+=====================================+"
            );
            
            List<Doctor> doctors = doctorService.getAllDoctors();
            if (doctors.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "\nNo doctors found" + ConsoleColors.RESET);
            } else {
                for (Doctor doctor : doctors) {
                    System.out.println(
                        ConsoleColors.PURPLE + "ID: " + ConsoleColors.RESET + doctor.getId() + " | " +
                        ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET+ doctor.getName() + " | " +
                        ConsoleColors.CYAN + "Specialization: " + ConsoleColors.RESET + doctor.getSpecialization()
                    );
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error displaying doctors: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }

    private void addDoctor() {
        try {
            MenuUtil.clearScreen();
            System.out.println(
                "+=====================================+\n" +
                "|           ADD NEW DOCTOR            |\n" +
                "+=====================================+"
            );
            
            String name = InputValidator.getRequiredStringInput("Doctor Name: ");
            String specialization = InputValidator.getRequiredStringInput("Specialization: ");
            
            int newId = doctorService.getNextId();
            doctorService.addDoctor(new Doctor(newId, name, specialization));
            
            System.out.println(ConsoleColors.GREEN + "\nDoctor added successfully!" + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error adding doctor: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }

    private void displayArchiveMenu() {
        boolean back = false;
        while (!back) {
            try {
                MenuUtil.clearScreen();
                System.out.println(
                    "+=====================================+\n" +
                    "|       ARCHIVED RECORDS MENU         |\n" +
                    "+=====================================+\n" +
                    "|  [1] View All Archived Records      |\n" +
                    "|  [2] Search Archived Record         |\n" +
                    "|  [3] Back To Doctor Menu            |\n" +
                    "+=====================================+"
                );

                int choice = InputValidator.getIntInput("Enter your choice: ", 1, 3);
                switch (choice) {
                    case 1: 
                        patientView.viewArchivedPatients();
                        break;
                    case 2: 
                        patientView.searchArchivedPatient();
                        break;
                    case 3: 
                        back = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "An error occurred: " + e.getMessage() + ConsoleColors.RESET);
                InputValidator.pressEnterToContinue();
            }
        }
    }
}

class InputValidator {
    private static Scanner scanner = new Scanner(System.in);

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(ConsoleColors.RED + "Please enter between " + min + "-" + max + ConsoleColors.RESET);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Invalid number" + ConsoleColors.RESET);
            }
        }
    }
    
    public static long getLongInput(String prompt, long min, long max) {
        while (true) {
            try {
                System.out.print(prompt);
                long value = Long.parseLong(scanner.nextLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(ConsoleColors.RED + "Please enter between " + min + "-" + max + ConsoleColors.RESET);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Invalid number" + ConsoleColors.RESET);
            }
        }
    }

    public static boolean getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) return true;
            if (input.equals("no") || input.equals("n")) return false;
            System.out.println(ConsoleColors.RED + "Please enter 'yes' or 'no' only!" + ConsoleColors.RESET);
        }
    }

    public static void pressEnterToContinue() {
        System.out.print(ConsoleColors.YELLOW + "\nPress Enter to Continue..." + ConsoleColors.RESET);
        scanner.nextLine();
    }
    
    public static String getRequiredStringInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt);
            if (!input.trim().isEmpty()) {
                return input;
            }
            System.out.println(ConsoleColors.RED + "This Field is Required!" + ConsoleColors.RESET);
        }
    }
}

class MenuUtil {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }
    }

    public static void displayWelcomeMessage() {
        System.out.println(
            "+===========================================================+\n" +
            "|                   WELCOME TO EHR SYSTEM                   |\n" +
            "|                (Your Health, Our Priority)                |\n" +
            "+===========================================================+\n" +
            "|                                                           |\n" +
            "|  PRIVACY NOTICE:                                          |\n" +
            "|  All patient information entered into this system is      |\n" +
            "|  confidential and protected. Access to records is        |\n" +
            "|  restricted to authorized healthcare staff only.         |\n" +
            "|  This system complies with data privacy principles to     |\n" +
            "|  ensure the security of patient health information.       |\n" +
            "|                                                           |\n" +
            "+===========================================================+"
        );
        
        boolean confirm = InputValidator.getYesNoInput("Are you sure to continue? (yes/no): ");
      
        if (confirm) {
            InputValidator.pressEnterToContinue();
        } else {
            System.out.print("\nThank you for using the Electronic Health Records System!");
            System.exit(0);
        }
    }

    public static void displayMainMenu() {
        clearScreen();
        System.out.println(
            "+=========================================+\n" +
            "|     ELECTRONIC HEALTH RECORD SYSTEM     |\n" +
            "+=========================================+\n" +
            "|  [1] Patient Management                 |\n" +             
            "|  [2] Doctor Portal                      |\n" +
            "|  [3] About Us                           |\n" +
            "|  [4] Exit Program                       |\n" +
            "+=========================================+"
        );
    }

    public static void displayAbout() {
        clearScreen();
        System.out.println(
            "+===========================================================+\n" +
            "|                   ABOUT THIS PROGRAM                      |\n" +
            "+===========================================================+\n" +
            "|                                                           |\n" +
            "|  Electronic Health Record (EHR) Management System         |\n" +
            "|                                                           |\n" +
            "|  This system was developed as a final project for         |\n" +
            "|  CC103 - Computer Science course. It demonstrates         |\n" +
            "|  our understanding of Java programming concepts,          |\n" +
            "|  including object-oriented principles, data               |\n" +
            "|  structures, and user interface design.                   |\n" +
            "|                                                           |\n" +
            "|  DEVELOPMENT TEAM:                                        |\n" +
            "|                                                           |\n" +
            "|  • Anggit, John Robert                                    |\n" +
            "|  • Balonzo, John Venedick                                 |\n" +
            "|  • Fuentes, Jhon Dave                                     |\n" +
            "|  • Gascon, Jerell                                         |\n" +
            "|  • Quidan, Excequel                                       |\n" +
            "|  • Rentoria, Rainier                                      |\n" +
            "|                                                           |\n" +
            "|  This project represents our collaborative effort to      |\n" +
            "|  create a functional healthcare management system that    |\n" +
            "|  can efficiently handle patient records while maintaining |\n" +
            "|  data privacy and security.                               |\n" +
            "|                                                           |\n" +
            "+===========================================================+"
        );
        InputValidator.pressEnterToContinue();
    }
}

class Patient {
    private int id;
    private String name;
    private int age;          
    private String dob;
    private String gender;
    private long contact;
    private String address;
    private long emergencyContact;
    private String allergies;
    private String currentMeds;
    private String medicalHistory;
    private String diagnosis;
    private String treatmentPlan;
    private int doctorId;

    public Patient(int id, String name, int age, String dob, String gender,long contact, 
                  String address, long emergencyContact, String allergies, 
                  String currentMeds, String medicalHistory, String diagnosis, 
                  String treatmentPlan, int doctorId) {
        this.id = id;
        this.name = name;
        this.age = age;      
        this.dob = dob;
        this.gender = gender;
        this.contact = contact;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.allergies = allergies;
        this.currentMeds = currentMeds;
        this.medicalHistory = medicalHistory;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.doctorId = doctorId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public long getContact() { return contact; }
    public String getAddress() { return address; }
    public long getEmergencyContact() { return emergencyContact; }
    public String getAllergies() { return allergies; }
    public String getCurrentMeds() { return currentMeds; }
    public String getMedicalHistory() { return medicalHistory; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatmentPlan() { return treatmentPlan; }
    public int getDoctorId() { return doctorId; }

    @Override
    public String toString() {
        return ConsoleColors.PURPLE + "ID: " + ConsoleColors.RESET + id + "\n" +
               ConsoleColors.CYAN + "Name: " + ConsoleColors.RESET + name + "\n" +
               ConsoleColors.CYAN + "Age: " + ConsoleColors.RESET + age + "\n" +
               ConsoleColors.CYAN + "DOB: " + ConsoleColors.RESET + dob + "\n" +
               ConsoleColors.CYAN + "Gender: " + ConsoleColors.RESET + gender + "\n" +
               ConsoleColors.CYAN + "Contact: " + ConsoleColors.RESET + contact + "\n" +
               ConsoleColors.CYAN + "Address: " + ConsoleColors.RESET + address + "\n" +
               ConsoleColors.CYAN + "Emergency Contact: " + ConsoleColors.RESET + emergencyContact + "\n" +
               ConsoleColors.CYAN + "Allergies: " + ConsoleColors.RESET + allergies + "\n" +
               ConsoleColors.CYAN + "Current Meds: " + ConsoleColors.RESET + currentMeds + "\n" +
               ConsoleColors.CYAN + "Medical History: " + ConsoleColors.RESET + medicalHistory + "\n" +
               ConsoleColors.CYAN + "Diagnosis: " + ConsoleColors.RESET + diagnosis + "\n" +
               ConsoleColors.CYAN + "Treatment: " + ConsoleColors.RESET + treatmentPlan + "\n" +
               ConsoleColors.CYAN + "Doctor Id: " + ConsoleColors.RESET + doctorId;
    }
}

class PatientService {
    private List<Patient> patients = new ArrayList<>();
    private List<Patient> archivedPatients = new ArrayList<>();
    private int nextId = 1000;
    private DoctorService doctorService;

    public PatientService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void addPatient(Patient patient) {
        try {
            patients.add(patient);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error adding patient: " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public List<Patient> getArchivedPatients() {
        return new ArrayList<>(archivedPatients);
    }

    public Patient getPatientById(int id) {
        try {
            for (Patient patient : patients) {
                if (patient.getId() == id) {
                    return patient;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error finding patient: " + e.getMessage() + ConsoleColors.RESET);
            return null;
        }
    }

    public Patient getArchivedPatientById(int id) {
        try {
            for (Patient patient : archivedPatients) {
                if (patient.getId() == id) {
                    return patient;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error finding archived patient: " + e.getMessage() + ConsoleColors.RESET);
            return null;
        }
    }

    public int getNextId() {
        return nextId++;
    }

    public void updatePatient(Patient updatedPatient) {
        try {
            for (int i = 0; i < patients.size(); i++) {
                if (patients.get(i).getId() == updatedPatient.getId()) {
                    patients.set(i, updatedPatient);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error updating patient: " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    public void archivePatient(int id) {
        try {
            Patient toArchive = null;
            for (Patient patient : patients) {
                if (patient.getId() == id) {
                    toArchive = patient;
                    break;
                }
            }
            if (toArchive != null) {
                archivedPatients.add(toArchive);
                patients.remove(toArchive);
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error archiving patient: " + e.getMessage() + ConsoleColors.RESET);
        }
    }

    public void deletePatient(int id) {
        try {
            List<Patient> toRemove = new ArrayList<>();
            for (Patient patient : patients) {
                if (patient.getId() == id) {
                    toRemove.add(patient);
                }
            }
            patients.removeAll(toRemove);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error deleting patient: " + e.getMessage() + ConsoleColors.RESET);
        }
    }
}

class PatientView {
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
            
            String name = InputValidator.getRequiredStringInput("Full Name: ");
            int age = InputValidator.getIntInput("Age: ", 0, 100);
            String dob = InputValidator.getRequiredStringInput("Date of Birth: ");
            String gender = InputValidator.getRequiredStringInput("Gender: ");
            long contact = InputValidator.getLongInput("Contact: ",1000000000L, 99999999999L);
            String address = InputValidator.getRequiredStringInput("Address: ");
            long emergency = InputValidator.getLongInput("Emergency Contact: ", 1000000000L, 99999999999L);
            String allergies = InputValidator.getStringInput("Allergies: ");
            String meds = InputValidator.getStringInput("Current Medications: ");
            String history = InputValidator.getStringInput("Medical History: ");
            String diagnosis = InputValidator.getRequiredStringInput("Diagnosis: ");
            String treatment = InputValidator.getRequiredStringInput("Treatment Plan: ");
            
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
