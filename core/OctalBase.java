package core;

public class OctalBase extends Base {
  public OctalBase() {
    super("octal", "-o");
  }

  @Override
  public String toBase(String input) {
    if (input == null)
      return null;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      int ascii = input.charAt(i);
      String octal = toOctal(ascii);
      sb.append(octal);
      if (i < input.length() - 1) {
        sb.append(" "); // separator
      }
    }
    return sb.toString();
  }

  @Override
  public String fromBase(String input) {
    if (input == null || input.isEmpty())
      return null;
    String[] octalValues = input.split(" ");
    StringBuilder sb = new StringBuilder();
    for (String octal : octalValues) {
      int ascii = fromOctal(octal);
      sb.append((char) ascii);
    }
    return sb.toString();
  }

  // Manual conversion: int to octal string (no prefix)
  private String toOctal(int value) {
    if (value == 0)
      return "0";
    StringBuilder octal = new StringBuilder();
    int v = value;
    while (v > 0) {
      int digit = v % 8;
      octal.insert(0, (char) ('0' + digit));
      v /= 8;
    }
    // Pad to 3 digits for ASCII
    while (octal.length() < 3) {
      octal.insert(0, '0');
    }
    return octal.toString();
  }

  // Manual conversion: octal string to int
  private int fromOctal(String octal) {
    int result = 0;
    for (int i = 0; i < octal.length(); i++) {
      char c = octal.charAt(i);
      if (c < '0' || c > '7') {
        throw new IllegalArgumentException("Invalid octal character: " + c);
      }
      int digit = c - '0';
      result = result * 8 + digit;
    }
    return result;
  }
}