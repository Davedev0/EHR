package FinalProject.EHR;

import java.util.List;

public class DoctorView {
    private DoctorService doctorService;

    public DoctorView(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public void displayMenu() {
        boolean back = false;
        while (!back) {
            try {
                MenuUtil.clearScreen();
                 System.out.println(ConsoleColors.GREEN + "=====DOCTOR MANAGEMENT=====" + ConsoleColors.RESET);
                System.out.println("[1] View All Doctors");
                System.out.println("[2] Add Doctor");
                System.out.println("[3] Back To Menu");
                System.out.println();

                int choice = InputValidator.getIntInput("Enter choice: ", 1, 3);
                switch (choice) {
                    case 1: 
                        viewDoctors();
                        break;
                    case 2: 
                        addDoctor();
                        break;
                    case 3: 
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
            System.out.println(ConsoleColors.GREEN + "=====LIST OF ALL DOCTORS=====" + ConsoleColors.RESET);
            
            List<Doctor> doctors = doctorService.getAllDoctors();
            if (doctors.isEmpty()) {
                System.out.println("No doctors found");
            } else {
                // Replaced lambda with traditional for-each loop
                for (Doctor doctor : doctors) {
                    System.out.println(doctor);
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
            System.out.println(ConsoleColors.GREEN + "=====ADD DOCTOR=====" + ConsoleColors.RESET);
            
            String name = InputValidator.getStringInput("Doctor Name: ");
            String specialization = InputValidator.getStringInput("Specialization: ");
            
            int newId = doctorService.getAllDoctors().size() + 1;
            doctorService.addDoctor(new Doctor(newId, name, specialization));
            System.out.println(ConsoleColors.GREEN + "Doctor added successfully!" + ConsoleColors.RESET);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error adding doctor: " + e.getMessage() + ConsoleColors.RESET);
        } finally {
            InputValidator.pressEnterToContinue();
        }
    }
}