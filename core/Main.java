package core;

import java.util.Scanner;

public class Main {
    /**
     * Entry point of the application. Uses command-line mode if arguments are
     * present, otherwise launches interactive mode.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (hasCommandLineArgs(args)) {
            handleCommandLineMode(args, scanner);
        } else {
            handleInteractiveMode(scanner);
        }
    }

    /**
     * Checks if both --input and --base arguments are present in args.
     */
    private static boolean hasCommandLineArgs(String[] args) {
        boolean hasInput = false;
        boolean hasBase = false;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("--input")) {
                hasInput = true;
            } else if (args[i].equals("--base")) {
                hasBase = true;
            }
        }
        return hasInput && hasBase;
    }

    /**
     * Handles conversion using command-line arguments.
     * Expected arguments: --input <string> --base <base>
     * 
     * @param args    Command-line arguments
     * @param scanner Scanner for user input (for retry)
     */
    private static void handleCommandLineMode(String[] args, Scanner scanner) {
        Validator validator = new Validator();
        boolean valid = false;
        String inputString = null;
        String baseInput = null;
        String direction = "toBase"; // default

        // Parse direction if present
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("--direction")) {
                direction = args[i + 1];
            }
        }

        while (!valid) {
            // Parse arguments
            inputString = null;
            baseInput = null;
            for (int i = 0; i < args.length - 1; i++) {
                if (args[i].equals("--input")) {
                    inputString = args[i + 1];
                } else if (args[i].equals("--base")) {
                    baseInput = args[i + 1];
                }
            }

            // Validate
            if (direction.equalsIgnoreCase("toBase")) {
                if (inputString == null || !validator.isValidString(inputString)) {
                    System.out.println("Missing or invalid --input argument. Please enter a valid string:");
                    inputString = scanner.nextLine();
                    if (!validator.isValidString(inputString)) {
                        System.out.println("Invalid input. Please try again.");
                        continue;
                    }
                }
            } else if (direction.equalsIgnoreCase("fromBase")) {
                if (inputString == null || baseInput == null || !validator.isValidBaseString(inputString, baseInput)) {
                    System.out.println(
                            "Missing or invalid --input argument for base string. Please enter a valid base-encoded string:");
                    inputString = scanner.nextLine();
                    if (!validator.isValidBaseString(inputString, baseInput)) {
                        System.out.println("Invalid base-encoded input. Please try again.");
                        continue;
                    }
                }
            }
            if (baseInput == null || !validator.isValidBase(baseInput)) {
                System.out.println(
                        "Missing or invalid --base argument. Please enter a valid base (hexadecimal/-h, octal/-o, decimal/-d, binary/-b, text/-t):");
                baseInput = scanner.nextLine();
                if (!validator.isValidBase(baseInput)) {
                    System.out.println("Invalid base. Please try again.");
                    continue;
                }
            }
            valid = true;
        }

        Base base = BaseFactory.getBase(baseInput);
        if (base == null) {
            System.out.println("Error: Could not resolve base. Exiting.");
            return;
        }

        Converter converter = new Converter();
        String result;
        if (direction.equals("fromBase")) {
            result = converter.fromBase(inputString, base);
        } else {
            result = converter.toBase(inputString, base);
        }
        System.out.println("Result: " + result);
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
            // Prompt for direction
            String direction = "toBase";
            while (true) {
                System.out.println("Would you like to:");
                System.out.println("1. Convert text to base");
                System.out.println("2. Convert base to text");
                System.out.print("Enter option (1 or 2): ");
                String dirOption = scanner.nextLine().trim();
                if (dirOption.equals("1")) {
                    direction = "toBase";
                    break;
                } else if (dirOption.equals("2")) {
                    direction = "fromBase";
                    break;
                } else {
                    System.out.println("Invalid option. Please enter 1 or 2.");
                }
            }

            String inputString;
            if (direction.equals("toBase")) {
                while (true) {
                    System.out.print("Enter the string to convert (letters and numbers only): ");
                    inputString = scanner.nextLine();
                    if (validator.isValidString(inputString)) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid string.");
                    }
                }
            } else {
                while (true) {
                    System.out.print("Enter the base-encoded string to convert to text: ");
                    inputString = scanner.nextLine();
                    // We'll prompt for base next, so pass empty string for now
                    // We'll validate fully after base is selected
                    if (!inputString.isEmpty()) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid base-encoded string.");
                    }
                }
            }

            String baseInput;
            while (true) {
                System.out.print(
                        "Enter the conversion base (hexadecimal/-h, octal/-o, decimal/-d, binary/-b, text/-t): ");
                baseInput = scanner.nextLine();
                if (validator.isValidBase(baseInput)) {
                    if (direction.equals("fromBase") && !validator.isValidBaseString(inputString, baseInput)) {
                        System.out.println("Invalid base-encoded string for the selected base. Please try again.");
                        // Re-prompt for base-encoded string
                        System.out.print("Enter the base-encoded string to convert to text: ");
                        inputString = scanner.nextLine();
                        continue;
                    }
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
            String result;
            if (direction.equals("fromBase")) {
                result = converter.fromBase(inputString, base);
            } else {
                result = converter.toBase(inputString, base);
            }
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