package core;

import java.util.Scanner;

public class Main {
    // ANSI color codes for CLI output
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Entry point of the application. Uses command-line mode if arguments are
     * present, otherwise launches interactive mode.
     */
    public static void main(String[] args) {
        // ASCII art banner for Global Converter
        System.out.println(ANSI_CYAN +
                "\n" +
                " ██████╗ ██╗      ██████╗ ██████╗  █████╗ ██╗          ██████╗ ██████╗ ███╗   ██╗██╗   ██╗███████╗██████╗ ████████╗███████╗██████╗ \n"
                +
                "██╔════╝ ██║     ██╔═══██╗██╔══██╗██╔══██╗██║         ██╔════╝██╔═══██╗████╗  ██║██║   ██║██╔════╝██╔══██╗╚══██╔══╝██╔════╝██╔══██╗\n"
                +
                "██║  ███╗██║     ██║   ██║██████╔╝███████║██║         ██║     ██║   ██║██╔██╗ ██║██║   ██║█████╗  ██████╔╝   ██║   █████╗  ██████╔╝\n"
                +
                "██║   ██║██║     ██║   ██║██╔══██╗██╔══██║██║         ██║     ██║   ██║██║╚██╗██║╚██╗ ██╔╝██╔══╝  ██╔══██╗   ██║   ██╔══╝  ██╔══██╗\n"
                +
                "╚██████╔╝███████╗╚██████╔╝██████╔╝██║  ██║███████╗    ╚██████╗╚██████╔╝██║ ╚████║ ╚████╔╝ ███████╗██║  ██║   ██║   ███████╗██║  ██║\n"
                +
                " ╚═════╝ ╚══════╝ ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝     ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝  ╚═══╝  ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝  ╚═╝\n"
                +
                "                                                                                                                                    \n"
                +
                "         Universal Base & Cipher CLI Tool\n" +
                ANSI_RESET);
        if (hasHelpArgument(args)) {
            printHelp();
            return;
        }
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
        boolean encrypt = false;
        boolean decrypt = false;
        String cipherType = null;
        int cipherKey = 0;

        // Parse direction, encrypt, decrypt, cipher, and key if present
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals("--direction")) {
                direction = args[i + 1];
            } else if (args[i].equals("--encrypt")) {
                encrypt = true;
            } else if (args[i].equals("--decrypt")) {
                decrypt = true;
            } else if (args[i].equals("--cipher")) {
                cipherType = args[i + 1];
            } else if (args[i].equals("--key")) {
                try {
                    cipherKey = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                    cipherKey = 0;
                }
            }
        }

        while (!valid) {
            // Parse arguments
            inputString = null;
            baseInput = null;
            for (int i = 0; i < args.length - 1; i++) {
                if (args[i].equals("--input")) {
                    StringBuilder sb = new StringBuilder();
                    int j = i + 1;
                    while (j < args.length && !args[j].startsWith("--")) {
                        if (sb.length() > 0)
                            sb.append(" ");
                        sb.append(args[j]);
                        j++;
                    }
                    inputString = sb.toString();
                } else if (args[i].equals("--base")) {
                    baseInput = args[i + 1];
                }
            }

            // Validate
            if (direction.equalsIgnoreCase("toBase")) {
                if (inputString == null || !validator.isValidString(inputString)) {
                    System.out.println(
                            ANSI_RED + "Error: Missing or invalid --input argument. Please enter a valid string:"
                                    + ANSI_RESET + " " + ANSI_YELLOW + "See --help for hint." + ANSI_RESET);
                    inputString = scanner.nextLine();
                    if (!validator.isValidString(inputString)) {
                        System.out.println(ANSI_RED + "Error: Invalid input. Please try again." + ANSI_RESET);
                        continue;
                    }
                }
            } else if (direction.equalsIgnoreCase("fromBase")) {
                if (inputString == null || baseInput == null || !validator.isValidBaseString(inputString, baseInput)) {
                    System.out.println(ANSI_RED
                            + "Error: Missing or invalid --input argument for base string. Please enter a valid base-encoded string:"
                            + ANSI_RESET + " " + ANSI_YELLOW + "See --help for hint." + ANSI_RESET);
                    inputString = scanner.nextLine();
                    if (!validator.isValidBaseString(inputString, baseInput)) {
                        System.out.println(
                                ANSI_RED + "Error: Invalid base-encoded input. Please try again." + ANSI_RESET);
                        continue;
                    }
                }
            }
            if (baseInput == null || !validator.isValidBase(baseInput)) {
                System.out.println(ANSI_RED
                        + "Error: Missing or invalid --base argument. Please enter a valid base (hexadecimal/-h, octal/-o, decimal/-d, binary/-b, text/-t):"
                        + ANSI_RESET + " " + ANSI_YELLOW + "See --help for hint." + ANSI_RESET);
                baseInput = scanner.nextLine();
                if (!validator.isValidBase(baseInput)) {
                    System.out.println(ANSI_RED + "Error: Invalid base. Please try again." + ANSI_RESET);
                    continue;
                }
            }
            // Validate cipher and key if needed
            if ((encrypt && direction.equalsIgnoreCase("toBase"))
                    || (decrypt && direction.equalsIgnoreCase("fromBase"))) {
                if (cipherType == null) {
                    cipherType = "caesar"; // default
                }
                if (!validator.isValidCipher(cipherType)) {
                    System.out.println(
                            ANSI_RED + "Error: Invalid or missing cipher. Only 'caesar' is supported. Please enter cipher:"
                                    + ANSI_RESET + " " + ANSI_YELLOW + "See --help for hint." + ANSI_RESET);
                    cipherType = scanner.nextLine().trim();
                    if (!validator.isValidCipher(cipherType)) {
                        System.out.println(ANSI_RED + "Error: Invalid cipher. Please try again." + ANSI_RESET);
                        continue;
                    }
                }
                if (!validator.isValidKey(cipherKey)) {
                    System.out.println(
                            ANSI_RED + "Error: Missing or invalid key. Please enter a positive integer:" + ANSI_RESET
                                    + " " + ANSI_YELLOW + "See --help for hint." + ANSI_RESET);
                    String keyStr = scanner.nextLine().trim();
                    try {
                        cipherKey = Integer.parseInt(keyStr);
                        if (!validator.isValidKey(cipherKey)) {
                            System.out.println(ANSI_RED + "Error: Invalid key. Please try again." + ANSI_RESET);
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(ANSI_RED + "Error: Invalid key. Please try again." + ANSI_RESET);
                        continue;
                    }
                }
            }
            valid = true;
        }

        Base base = BaseFactory.getBase(baseInput);
        if (base == null) {
            System.out.println(ANSI_RED + "Error: Could not resolve base. Exiting." + ANSI_RESET);
            return;
        }

        Converter converter = new Converter();
        String result;
        if (direction.equalsIgnoreCase("toBase")) {
            if (encrypt) {
                Cipher cipher = new CaesarCipher();
                inputString = cipher.encrypt(inputString, cipherKey);
            }
            showProgressBar(20, 40);
            result = converter.toBase(inputString, base);
        } else {
            result = converter.fromBase(inputString, base);
            if (decrypt) {
                Cipher cipher = new CaesarCipher();
                result = cipher.decrypt(result, cipherKey);
            }
        }
        showSpinner(700);
        System.out.println(ANSI_GREEN + "Success: Conversion complete!" + ANSI_RESET);
        printBoxedResult(result);
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
                System.out.println(ANSI_CYAN + "Would you like to:" + ANSI_RESET);
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
                    System.out.println(ANSI_RED + "Error: Invalid option. Please enter 1 or 2." + ANSI_RESET);
                }
            }

            String inputString;
            boolean useCipher = false;
            String cipherType = null;
            int cipherKey = 0;

            if (direction.equals("toBase")) {
                // Ask if the user wants to encrypt
                while (true) {
                    System.out.print(ANSI_CYAN + "Encrypt the string before conversion? (y/n): " + ANSI_RESET);
                    String encOption = scanner.nextLine().trim().toLowerCase();
                    if (encOption.equals("y")) {
                        useCipher = true;
                        break;
                    } else if (encOption.equals("n")) {
                        break;
                    } else {
                        System.out.println(ANSI_RED + "Error: Please enter 'y' or 'n'." + ANSI_RESET);
                    }
                }
                if (useCipher) {
                    // For now, only support Caesar
                    cipherType = "caesar";
                    while (true) {
                        System.out.print(ANSI_CYAN + "Enter Caesar cipher key (positive integer): " + ANSI_RESET);
                        String keyStr = scanner.nextLine().trim();
                        try {
                            cipherKey = Integer.parseInt(keyStr);
                            if (validator.isValidKey(cipherKey)) {
                                break;
                            } else {
                                System.out.println(
                                        ANSI_RED + "Error: Invalid key. Please enter a positive integer." + ANSI_RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    ANSI_RED + "Error: Invalid key. Please enter a positive integer." + ANSI_RESET);
                        }
                    }
                }
                while (true) {
                    System.out
                            .print(ANSI_CYAN + "Enter the string to convert (letters and numbers only): " + ANSI_RESET);
                    inputString = scanner.nextLine();
                    if (validator.isValidString(inputString)) {
                        break;
                    } else {
                        System.out
                                .println(ANSI_RED + "Error: Invalid input. Please enter a valid string." + ANSI_RESET);
                    }
                }
            } else {
                // Ask if the user wants to decrypt
                while (true) {
                    System.out.print(ANSI_CYAN + "Decrypt the result? (y/n): " + ANSI_RESET);
                    String decOption = scanner.nextLine().trim().toLowerCase();
                    if (decOption.equals("y")) {
                        useCipher = true;
                        break;
                    } else if (decOption.equals("n")) {
                        break;
                    } else {
                        System.out.println(ANSI_RED + "Error: Please enter 'y' or 'n'." + ANSI_RESET);
                    }
                }
                if (useCipher) {
                    // For now, only support Caesar
                    cipherType = "caesar";
                    while (true) {
                        System.out.print(ANSI_CYAN + "Enter Caesar cipher key (positive integer): " + ANSI_RESET);
                        String keyStr = scanner.nextLine().trim();
                        try {
                            cipherKey = Integer.parseInt(keyStr);
                            if (validator.isValidKey(cipherKey)) {
                                break;
                            } else {
                                System.out.println(
                                        ANSI_RED + "Error: Invalid key. Please enter a positive integer." + ANSI_RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    ANSI_RED + "Error: Invalid key. Please enter a positive integer." + ANSI_RESET);
                        }
                    }
                }
                while (true) {
                    System.out.print(ANSI_CYAN + "Enter the base-encoded string to convert to text: " + ANSI_RESET);
                    inputString = scanner.nextLine();
                    if (!inputString.isEmpty()) {
                        break;
                    } else {
                        System.out.println(
                                ANSI_RED + "Error: Invalid input. Please enter a valid base-encoded string."
                                        + ANSI_RESET);
                    }
                }
            }

            String baseInput;
            while (true) {
                System.out.print(ANSI_CYAN
                        + "Enter the conversion base (hexadecimal/-h, octal/-o, decimal/-d, binary/-b, text/-t): "
                        + ANSI_RESET);
                baseInput = scanner.nextLine();
                if (validator.isValidBase(baseInput)) {
                    if (direction.equals("fromBase") && !validator.isValidBaseString(inputString, baseInput)) {
                        System.out.println(ANSI_RED
                                + "Error: Invalid base-encoded string for the selected base. Please try again."
                                + ANSI_RESET + " " + ANSI_YELLOW + "See --help for hint." + ANSI_RESET);
                        System.out.print(ANSI_CYAN + "Enter the base-encoded string to convert to text: " + ANSI_RESET);
                        inputString = scanner.nextLine();
                        continue;
                    }
                    break;
                } else {
                    System.out
                            .println(ANSI_RED + "Error: Invalid base. Please enter a valid base option." + ANSI_RESET
                                    + " " + ANSI_YELLOW + "See --help for hint." + ANSI_RESET);
                }
            }

            Base base = BaseFactory.getBase(baseInput);
            if (base == null) {
                System.out.println(ANSI_RED + "Error: Could not resolve base. Exiting." + ANSI_RESET);
                return;
            }

            Converter converter = new Converter();
            String result;
            if (direction.equals("toBase")) {
                if (useCipher) {
                    Cipher cipher = new CaesarCipher();
                    inputString = cipher.encrypt(inputString, cipherKey);
                }
                showProgressBar(20, 40);
                result = converter.toBase(inputString, base);
            } else {
                result = converter.fromBase(inputString, base);
                if (useCipher) {
                    Cipher cipher = new CaesarCipher();
                    result = cipher.decrypt(result, cipherKey);
                }
            }
            showSpinner(700);
            System.out.println(ANSI_GREEN + "Success: Conversion complete!" + ANSI_RESET);
            printBoxedResult(result);

            // Ask if the user wants to convert another string
            while (true) {
                System.out.print(ANSI_CYAN + "Would you like to convert another string? (y/n): " + ANSI_RESET);
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("y")) {
                    break; // Continue the outer loop
                } else if (answer.equals("n")) {
                    continueConversion = false;
                    System.out.println(ANSI_CYAN + "Thank you for using Global Converter. Bye!" + ANSI_RESET);
                    break;
                } else {
                    System.out.println(ANSI_RED + "Error: Please enter 'y' or 'n'." + ANSI_RESET);
                }
            }
        }
    }

    // Spinner for short operations
    public static void showSpinner(int durationMillis) {
        String[] spinner = { "|", "/", "-", "\\" };
        long end = System.currentTimeMillis() + durationMillis;
        int i = 0;
        while (System.currentTimeMillis() < end) {
            System.out.print("\r" + ANSI_PURPLE + spinner[i % spinner.length] + " Processing..." + ANSI_RESET);
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
            }
            i++;
        }
        System.out.print("\r"); // Clear spinner line
    }

    // Progress bar for encryption/decryption
    public static void showProgressBar(int steps, int delayMillis) {
        System.out.print(ANSI_PURPLE + "[" + ANSI_RESET);
        for (int i = 0; i < steps; i++) {
            System.out.print(ANSI_PURPLE + "=" + ANSI_RESET);
            System.out.flush();
            try {
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
            }
        }
        System.out.println(ANSI_PURPLE + "] Done!" + ANSI_RESET);
    }

    // Print result in a bold green ASCII box
    public static void printBoxedResult(String result) {
        String[] lines = result.split("\n");
        int maxLen = 0;
        for (String line : lines) {
            if (line.length() > maxLen)
                maxLen = line.length();
        }
        String top = ANSI_GREEN + "\033[1m+" + "-".repeat(maxLen + 2) + "+" + ANSI_RESET;
        System.out.println(top);
        for (String line : lines) {
            System.out.println(ANSI_GREEN + "\033[1m| " + line + " ".repeat(maxLen - line.length()) + "|" + ANSI_RESET);
        }
        System.out.println(top);
    }

    /**
     * Prints a well-formatted, colored help message for CLI usage.
     */
    public static void printHelp() {
        System.out.println(ANSI_CYAN + "\nGlobal Converter CLI Help" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Usage:" + ANSI_RESET);
        System.out.println("  java -cp . core.Main --input <string> --base <base> [options]\n");
        System.out.println(ANSI_GREEN + "Options:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "  --input <string>" + ANSI_RESET + "      Input string to convert.");
        System.out.println(ANSI_CYAN + "  --base <base>" + ANSI_RESET
                + "        Target base: hexadecimal/-h, octal/-o, decimal/-d, binary/-b, text/-t");
        System.out.println(ANSI_CYAN + "  --direction <toBase|fromBase>" + ANSI_RESET
                + "  Conversion direction (default: toBase)");
        System.out.println(ANSI_CYAN + "  --encrypt" + ANSI_RESET + "             Encrypt before conversion (toBase)");
        System.out.println(ANSI_CYAN + "  --decrypt" + ANSI_RESET + "             Decrypt after conversion (fromBase)");
        System.out.println(ANSI_CYAN + "  --cipher <caesar>" + ANSI_RESET + "      Cipher type (default: caesar)");
        System.out.println(ANSI_CYAN + "  --key <int>" + ANSI_RESET + "            Cipher key (positive integer)");
        System.out.println(ANSI_CYAN + "  --help" + ANSI_RESET + "                 Show this help message and exit.\n");
        System.out.println(ANSI_GREEN + "Examples:" + ANSI_RESET);
        System.out.println("  java -cp . core.Main --input Hello --base -h --encrypt --key 3");
        System.out.println("  java -cp . core.Main --input 48 65 6c 6c 6f --base -h --direction fromBase\n");
        System.out.println(ANSI_YELLOW + "For interactive mode, run without arguments." + ANSI_RESET);
    }

    /**
     * Checks if --help is present in args.
     */
    private static boolean hasHelpArgument(String[] args) {
        for (String arg : args) {
            if (arg.equals("--help")) {
                return true;
            }
        }
        return false;
    }
}