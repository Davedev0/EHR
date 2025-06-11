package FinalProject.EHR;

public class EHRSystem {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        try {
            // Display intro
            displayIntro();
            
            // Initialize services
            DoctorService doctorService = new DoctorService();
            PatientService patientService = new PatientService(doctorService);
            
            // Initialize views
            PatientView patientView = new PatientView(patientService, doctorService);
            DoctorView doctorView = new DoctorView(doctorService, patientView);
            
            // Display welcome message
            MenuUtil.displayWelcomeMessage();
            
            // Main program loop
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
            
            System.out.println(ConsoleColors.BLUE_BOLD + "\nThank you for using the Electronic Health Records System!" + ConsoleColors.RESET);
            System.out.println("System shutdown successfully.");
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "A critical error occurred: " + e.getMessage() + ConsoleColors.RESET);
            System.out.println("Please contact system administrator.");
        }
    }

    private static void displayIntro() {
        System.out.println(ConsoleColors.CYAN);
        System.out.println(
             " ______   _    _   _____ \n" +
             "|  ____| | |  | | |  __ \\\n" +
             "| |__    | |__| | | |__) |\n" +
             "|  __|   |  __  | |  _  / \n" +
             "| |____  | |  | | | | \\ \\ \n" +
             "|______| |_|  |_| |_|  \\_\\\n"
        );
        System.out.println(ConsoleColors.RESET);
        System.out.print("Please wait to continue...");
        
        try {
            Thread.sleep(3000); 
            MenuUtil.clearScreen();
        } catch (InterruptedException e) {
        }
    }
}