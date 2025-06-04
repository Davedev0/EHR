package FinalProject.EHR;

public class MenuUtil {
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
    System.out.println("+=====================================+");
    System.out.println("|        WELCOME TO EHR SYSTEM        |");
    System.out.println("+=====================================+");
    System.out.println(ConsoleColors.RED + "\nPrivacy Notice: " + ConsoleColors.RESET);
    System.out.println("All patient information entered into this system is confidential and protected." +
                        "Access to records is restricted to authorized healthcare staff only." +
                        "This system complies with data privacy principles to ensure the" +
                        "security of patient health information. \n");
    boolean confirm = InputValidator.getYesNoInput("Are your sure to continue? (yes/no): ");
            
            if (confirm) {
                InputValidator.pressEnterToContinue();
                displayMainMenu();
            } else {
                System.out.println("\nThank you for using the Electronic Health Records System!");
                System.exit(0);
            }
        }

    public static void displayMainMenu() {
        clearScreen();
        System.out.println(ConsoleColors.GREEN + "=====ELECTRONIC HEALTH RECORD SYSTEM=====" + ConsoleColors.RESET);
        System.out.println("[1] Patient Management");
        System.out.println("[2] Doctor Management");
        System.out.println("[3] About Us");
        System.out.println("[4] Exit");
        System.out.println();
    }

    public static void displayAbout() {
        clearScreen();
        System.out.println(ConsoleColors.GREEN + "=====ABOUT US=====" + ConsoleColors.RESET);
        System.out.println("EHR Management System");
        System.out.println("Version 1.0");
        System.out.println("Final Project");
        InputValidator.pressEnterToContinue();
    }
}