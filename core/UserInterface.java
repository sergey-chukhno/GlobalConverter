package core;

import java.util.Scanner;

public class UserInterface {
  private final Scanner scanner;
  private final Validator validator;

  public UserInterface(Scanner scanner) {
    this.scanner = scanner;
    this.validator = new Validator();
  }

  public ConversionConfig promptUser() {
    String direction = promptDirection();
    boolean useCipher = false;
    String cipherType = null;
    int cipherKey = 0;
    String inputString;
    if (direction.equals("toBase")) {
      useCipher = promptYesNo("Encrypt the string before conversion? (y/n): ");
      if (useCipher) {
        cipherType = "caesar";
        cipherKey = promptCipherKey();
      }
      inputString = promptValidString();
    } else {
      useCipher = promptYesNo("Decrypt the result? (y/n): ");
      if (useCipher) {
        cipherType = "caesar";
        cipherKey = promptCipherKey();
      }
      inputString = promptNonEmpty("Enter the base-encoded string to convert to text: ");
    }
    String baseInput = promptValidBase(direction, inputString);
    return new ConversionConfig(inputString, baseInput, direction, useCipher, cipherType, cipherKey);
  }

  private String promptDirection() {
    while (true) {
      System.out.println("Would you like to:");
      System.out.println("1. Convert text to base");
      System.out.println("2. Convert base to text");
      System.out.print("Enter option (1 or 2): ");
      String dirOption = scanner.nextLine().trim();
      if (dirOption.equals("1"))
        return "toBase";
      if (dirOption.equals("2"))
        return "fromBase";
      System.out.println("Invalid option. Please enter 1 or 2.");
    }
  }

  public boolean promptYesNo(String prompt) {
    while (true) {
      System.out.print(prompt);
      String option = scanner.nextLine().trim().toLowerCase();
      if (option.equals("y"))
        return true;
      if (option.equals("n"))
        return false;
      System.out.println("Please enter 'y' or 'n'.");
    }
  }

  private int promptCipherKey() {
    while (true) {
      System.out.print("Enter Caesar cipher key (positive integer): ");
      String keyStr = scanner.nextLine().trim();
      try {
        int key = Integer.parseInt(keyStr);
        if (validator.isValidKey(key))
          return key;
      } catch (NumberFormatException ignored) {
      }
      System.out.println("Invalid key. Please enter a positive integer.");
    }
  }

  private String promptValidString() {
    while (true) {
      System.out.print("Enter the string to convert (letters and numbers only): ");
      String input = scanner.nextLine();
      if (validator.isValidString(input))
        return input;
      System.out.println("Invalid input. Please enter a valid string.");
    }
  }

  private String promptNonEmpty(String prompt) {
    while (true) {
      System.out.print(prompt);
      String input = scanner.nextLine();
      if (!input.isEmpty())
        return input;
      System.out.println("Invalid input. Please enter a valid string.");
    }
  }

  private String promptValidBase(String direction, String inputString) {
    while (true) {
      System.out.print("Enter the conversion base (hexadecimal/-h, octal/-o, decimal/-d, binary/-b, text/-t): ");
      String baseInput = scanner.nextLine();
      if (validator.isValidBase(baseInput)) {
        if (direction.equals("fromBase") && !validator.isValidBaseString(inputString, baseInput)) {
          System.out.println("Invalid base-encoded string for the selected base. Please try again.");
          continue;
        }
        return baseInput;
      }
      System.out.println("Invalid base. Please enter a valid base option.");
    }
  }

  public void printResult(String result) {
    System.out.println("Result: " + result);
  }

  public void printMessage(String message) {
    System.out.println(message);
  }
}