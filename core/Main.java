package core;

import java.util.Scanner;

public class Main {
    /**
     * Entry point of the application. Offers user a choice between command-line
     * arguments and interactive prompt.
     */
    public static void main(String[] args) {
        System.out.println("Global Converter");
        System.out.println("Choose input mode:");
        System.out.println("1. Command-line arguments");
        System.out.println("2. Interactive prompt");
        System.out.print("Enter option (1 or 2): ");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine().trim();

        if (option.equals("1")) {
            handleCommandLineMode(args);
        } else if (option.equals("2")) {
            handleInteractiveMode(scanner);
        } else {
            System.out.println("Invalid option. Exiting.");
        }
    }

    /**
     * Handles conversion using command-line arguments.
     * 
     * @param args Command-line arguments
     */
    private static void handleCommandLineMode(String[] args) {
        // TODO: Parse args for input string and base
        // TODO: Validate inputs
        // TODO: Perform conversion and display result
        System.out.println("[Command-line mode not yet implemented]");
    }

    /**
     * Handles conversion using interactive prompts.
     * 
     * @param scanner Scanner for user input
     */
    private static void handleInteractiveMode(Scanner scanner) {
        Validator validator = new Validator();
        boolean continueConversion = true;
        while (continueConversion) {
            String inputString;
            while (true) {
                System.out.print("Enter the string to convert (letters and numbers only): ");
                inputString = scanner.nextLine();
                if (validator.isValidString(inputString)) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a valid string.");
                }
            }

            String baseInput;
            while (true) {
                System.out.print(
                        "Enter the conversion base (hexadecimal/-h, octal/-o, decimal/-d, binary/-b, text/-t): ");
                baseInput = scanner.nextLine();
                if (validator.isValidBase(baseInput)) {
                    break;
                } else {
                    System.out.println("Invalid base. Please enter a valid base option.");
                }
            }

            Base base = BaseFactory.getBase(baseInput);
            if (base == null) {
                System.out.println("Error: Could not resolve base. Exiting.");
                return;
            }

            Converter converter = new Converter();
            String result = converter.toBase(inputString, base);
            System.out.println("Result: " + result);

            // Ask if the user wants to convert another string
            while (true) {
                System.out.print("Would you like to convert another string? (y/n): ");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("y")) {
                    break; // Continue the outer loop
                } else if (answer.equals("n")) {
                    continueConversion = false;
                    System.out.println("Thank you for using Global Converter. Bye!");
                    break;
                } else {
                    System.out.println("Please enter 'y' or 'n'.");
                }
            }
        }
    }
}