package FinalProject.EHR;

import java.util.List;

public class DoctorView {
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
                System.out.println("+=====================================+");
                System.out.println("|         DOCTOR MANAGEMENT           |");
                System.out.println("+=====================================+");
                System.out.println("|  [1] View All Doctors               |");
                System.out.println("|  [2] Add New Doctor                 |");
                System.out.println("|  [3] Archived Records               |");
                System.out.println("|  [4] Back To Main Menu              |");
                System.out.println("+=====================================+");
                int choice = InputValidator.getIntInput("Enter choice: ", 1, 4);
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
            System.out.println("+=====================================+");
            System.out.println("|         LIST OF ALL DOCTORS         |");
            System.out.println("+=====================================+");
            
            List<Doctor> doctors = doctorService.getAllDoctors();
            if (doctors.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "\nNo doctors found" + ConsoleColors.RESET);
            } else {
                for (Doctor doctor : doctors) {
                    System.out.println(
                        ConsoleColors.CYAN + "ID: " + doctor.getId() + ConsoleColors.RESET + "\n" +
                        "Name: " + doctor.getName() + "\n" +
                        "Specialization: " + doctor.getSpecialization() + "\n" +
                        "--------------------------------------"
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
            System.out.println("+=====================================+");
            System.out.println("|           ADD NEW DOCTOR            |");
            System.out.println("+=====================================+");
            
            String name = InputValidator.getStringInput("Doctor Name: ");
            String specialization = InputValidator.getStringInput("Specialization: ");
            
            int newId = doctorService.getAllDoctors().size() + 1;
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
                System.out.println("+=====================================+");
                System.out.println("|       ARCHIVED RECORDS MENU         |");
                System.out.println("+=====================================+");
                System.out.println("|  [1] View All Archived Records      |");
                System.out.println("|  [2] Search Archived Record         |");
                System.out.println("|  [3] Back To Doctor Menu            |");
                System.out.println("+=====================================+");
                
                System.out.println(ConsoleColors.YELLOW + "\nPrivacy Notice:");
                System.out.println("All archived information is protected under confidentiality policies." + ConsoleColors.RESET);
                
                if (!InputValidator.getYesNoInput("\nDo you understand? (yes/no): ")) {
                    System.out.println(ConsoleColors.RED + "\nCannot proceed without confirmation." + ConsoleColors.RESET);
                    InputValidator.pressEnterToContinue();
                    return;
                }

                int choice = InputValidator.getIntInput("\nEnter choice: ", 1, 3);
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