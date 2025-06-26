package core;

public class TextBase extends Base {
  public TextBase() {
    super("text", "-t");
  }

  @Override
  public String toBase(String input) {
    // For text, return the input as is
    return input;
  }

  @Override
  public String fromBase(String input) {
    // For text, return the input as is
    return input;
  }
}