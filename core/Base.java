package core;

/**
 * Enumerates supported bases and provides mapping between user input and
 * internal logic.
 */
public abstract class Base {
  private final String name;
  private final String abbreviation;

  public Base(String name, String abbreviation) {
    this.name = name;
    this.abbreviation = abbreviation;
  }

  public String getName() {
    return name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  /**
   * Converts the input string to this base.
   * 
   * @param input The string to convert.
   * @return The converted string.
   */
  public abstract String toBase(String input);

  /**
   * Converts the input string from this base to text.
   * 
   * @param input The string in this base.
   * @return The converted text string.
   */
  public abstract String fromBase(String input);
}