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
        System.out.print(ConsoleColors.YELLOW + "\nPress Enter to continue..." + ConsoleColors.RESET);
        scanner.nextLine();
    }
}