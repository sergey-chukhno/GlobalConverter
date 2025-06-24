public class Converter {
  /**
   * Converts the input string to the specified base using the Base class
   * hierarchy.
   * 
   * @param input The string to convert.
   * @param base  The target Base instance.
   * @return The converted string.
   */
  public String toBase(String input, Base base) {
    if (base == null) {
      throw new IllegalArgumentException("Base cannot be null");
    }
    return base.toBase(input);
  }

  /**
   * Converts the input string from the specified base to text using the Base
   * class hierarchy.
   * 
   * @param input The string in the specified base.
   * @param base  The source Base instance.
   * @return The converted text string.
   */
  public String fromBase(String input, Base base) {
    if (base == null) {
      throw new IllegalArgumentException("Base cannot be null");
    }
    return base.fromBase(input);
  }
}