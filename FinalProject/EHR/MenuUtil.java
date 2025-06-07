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
    
    System.out.println("+===========================================================+");
    System.out.println("|                   WELCOME TO EHR SYSTEM                   |");
    System.out.println("|                (Your Health, Our Priority)                |");
    System.out.println("+===========================================================+");
    System.out.println("|                                                           |");
    System.out.println("|  PRIVACY NOTICE:                                          |");
    System.out.println("|  All patient information entered into this system is      |");
    System.out.println("|  confidential and protected. Access to records is         |");
    System.out.println("|  restricted to authorized healthcare staff only.          |");
    System.out.println("|  This system complies with data privacy principles to     |");
    System.out.println("|  ensure the security of patient health information.       |");
    System.out.println("|                                                           |");
    System.out.println("+===========================================================+");
    
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
        System.out.println("+=========================================+");
        System.out.println("|     ELECTRONIC HEALTH RECORD SYSTEM     |");
        System.out.println("+=========================================+");
        System.out.println("|  [1] Patient Management                 |");             
        System.out.println("|  [2] Doctor Portal                      |");
        System.out.println("|  [3] Archived Records                   |");
        System.out.println("|  [4] About Us                           |");
        System.out.println("|  [5] Exit Program                       |");
        System.out.println("+=========================================+");
    }

    public static void displayAbout() {
        clearScreen();
        System.out.println("+=====================================+");
        System.out.println("|          ABOUT THIS PROGRAM         |");
        System.out.println("+=====================================+");
        System.out.println("EHR Management System");
        System.out.println("Version 1.0");
        System.out.println("Final Project and created by BC2MA Palautog");
        InputValidator.pressEnterToContinue();
    }
}