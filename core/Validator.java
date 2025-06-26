package core;

public class Validator {
  /**
   * Validates if the input string contains only alphanumeric characters.
   */
  public boolean isValidString(String input) {
    if (input == null || input.isEmpty()) {
      return false;
    }
    for (char c : input.toCharArray()) {
      if (!Character.isLetterOrDigit(c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Validates if the provided base option is supported.
   */
  public boolean isValidBase(String base) {
    if (base == null)
      return false;
    String b = base.trim().toLowerCase();
    return b.equals("hexadecimal") || b.equals("-h") ||
        b.equals("octal") || b.equals("-o") ||
        b.equals("decimal") || b.equals("-d") ||
        b.equals("binary") || b.equals("-b") ||
        b.equals("text") || b.equals("-t");
  }

  /**
   * Validates if the provided cipher option is supported.
   * For now, only "caesar" and "-c" are supported.
   */
  public boolean isValidCipher(String cipher) {
    if (cipher == null)
      return false;
    String c = cipher.trim().toLowerCase();
    return c.equals("caesar") || c.equals("-c");
  }

  /**
   * Validates if the provided key is valid for the cipher.
   * For Caesar cipher, key should be a positive integer (1 or greater).
   */
  public boolean isValidKey(int key) {
    return key > 0;
  }

  /**
   * Validates if the input string is a valid base-encoded string for fromBase
   * direction.
   * Accepts digits, spaces, and valid base characters (a-f for hex, etc.).
   */
  public boolean isValidBaseString(String input, String base) {
    if (input == null || input.isEmpty()) {
      return false;
    }
    String b = base.trim().toLowerCase();
    for (char c : input.toCharArray()) {
      if (c == ' ')
        continue;
      if (b.equals("hexadecimal") || b.equals("-h")) {
        if (!(Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
          return false;
        }
      } else if (b.equals("octal") || b.equals("-o")) {
        if (!(c >= '0' && c <= '7')) {
          return false;
        }
      } else if (b.equals("decimal") || b.equals("-d")) {
        if (!Character.isDigit(c)) {
          return false;
        }
      } else if (b.equals("binary") || b.equals("-b")) {
        if (!(c == '0' || c == '1')) {
          return false;
        }
      } else if (b.equals("text") || b.equals("-t")) {
        // For text, allow any character
        return true;
      }
    }
    return true;
  }
}