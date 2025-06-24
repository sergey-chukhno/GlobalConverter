public class BaseFactory {
  /**
   * Returns the appropriate Base subclass instance based on user input.
   * Supports both full names and abbreviations (case-insensitive).
   * Returns null if the input does not match any supported base.
   */
  public static Base getBase(String input) {
    if (input == null)
      return null;
    String b = input.trim().toLowerCase();
    switch (b) {
      case "hexadecimal":
      case "-h":
        return new HexadecimalBase();
      case "octal":
      case "-o":
        return new OctalBase();
      case "decimal":
      case "-d":
        return new DecimalBase();
      case "binary":
      case "-b":
        return new BinaryBase();
      case "text":
      case "-t":
        return new TextBase();
      default:
        return null;
    }
  }
}