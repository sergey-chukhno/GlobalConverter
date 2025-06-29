package core;

import java.util.Scanner;

public class Main {
    /**
     * Entry point of the application. Uses command-line mode if arguments are
     * present, otherwise launches interactive mode.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInterface ui = new UserInterface(scanner);
        ConversionService service = new ConversionService();
        if (hasCommandLineArgs(args)) {
            ConversionConfig config = ArgumentParser.parseArgs(args);
            try {
                String result = service.process(config);
                ui.printResult(result);
            } catch (IllegalArgumentException e) {
                ui.printMessage("Error: " + e.getMessage());
            }
        } else {
            boolean continueConversion = true;
            while (continueConversion) {
                ConversionConfig config = ui.promptUser();
                try {
                    String result = service.process(config);
                    ui.printResult(result);
                } catch (IllegalArgumentException e) {
                    ui.printMessage("Error: " + e.getMessage());
                }
                continueConversion = ui.promptYesNo("Would you like to convert another string? (y/n): ");
                if (!continueConversion) {
                    ui.printMessage("Thank you for using Global Converter. Bye!");
                }
            }
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
}