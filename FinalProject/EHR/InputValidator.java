package FinalProject.EHR;

import java.util.Scanner;

public class InputValidator {
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
                System.out.println(ConsoleColors.RED + "Invalid number! Please try again." + ConsoleColors.RESET);
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
                System.out.println(ConsoleColors.RED + "Invalid number! Please try again." + ConsoleColors.RESET);
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
    
    //regex
    public static String getValidLetterInput(String prompt) {
        while (true) {
            String input = getRequiredStringInput(prompt);
            if (input.matches("^[a-zA-Z\\s,.']+$")) {
                return input;
            }
            System.out.println(ConsoleColors.RED + "Invalid input! It should contain only letters." + ConsoleColors.RESET);
        }
    }
    
   public static String getValidGenderInput(String prompt) {
      while (true) {
        String input = getRequiredStringInput(prompt).trim().toLowerCase();
        if (input.equals("male") || input.equals("m") || 
            input.equals("female") || input.equals("f")) {
            // Return properly capitalized version
            if (input.equals("m")) return "Male";
            if (input.equals("f")) return "Female";
            return input.substring(0, 1).toUpperCase() + input.substring(1);
        }
        System.out.println(ConsoleColors.RED + "Invalid input! Please enter Male/Female only." + ConsoleColors.RESET);
       }
    }
}