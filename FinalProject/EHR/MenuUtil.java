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
    System.out.println(
        "+===========================================================+\n" +
        "|                   WELCOME TO EHR SYSTEM                   |\n" +
        "|                (Your Health, Our Priority)                |\n" +
        "+===========================================================+\n" +
        "|                                                           |\n" +
        "|  PRIVACY NOTICE:                                          |\n" +
        "|  All patient information entered into this system is      |\n" +
        "|  confidential and protected. Access to records is         |\n" +
        "|  restricted to authorized healthcare staff only.          |\n" +
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