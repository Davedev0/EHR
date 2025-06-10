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
                System.out.println(
                    "+=====================================+\n" +
                    "|         DOCTOR MANAGEMENT           |\n" +
                    "+=====================================+\n" +
                    "|  [1] View All Doctors               |\n" +
                    "|  [2] Add New Doctor                 |\n" +
                    "|  [3] Archived Records               |\n" +
                    "|  [4] Back To Main Menu              |\n" +
                    "+=====================================+"
                );
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
            System.out.println(
                "+=====================================+\n" +
                "|           ADD NEW DOCTOR            |\n" +
                "+=====================================+"
            );
            
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
                System.out.println(
                    "+=====================================+\n" +
                    "|       ARCHIVED RECORDS MENU         |\n" +
                    "+=====================================+\n" +
                    "|  [1] View All Archived Records      |\n" +
                    "|  [2] Search Archived Record         |\n" +
                    "|  [3] Back To Doctor Menu            |\n" +
                    "+=====================================+"
                );
                
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