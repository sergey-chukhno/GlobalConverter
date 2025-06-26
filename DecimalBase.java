public class DecimalBase extends Base {
  public DecimalBase() {
    super("decimal", "-d");
  }

  @Override
  public String toBase(String input) {
    if (input == null)
      return null;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      int ascii = input.charAt(i);
      String decimal = toDecimal(ascii);
      sb.append(decimal);
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
    String[] decimalValues = input.split(" ");
    StringBuilder sb = new StringBuilder();
    for (String decimal : decimalValues) {
      int ascii = fromDecimal(decimal);
      sb.append((char) ascii);
    }
    return sb.toString();
  }

  // Manual conversion: int to decimal string
  private String toDecimal(int value) {
    if (value == 0)
      return "0";
    StringBuilder decimal = new StringBuilder();
    int v = value;
    while (v > 0) {
      int digit = v % 10;
      decimal.insert(0, (char) ('0' + digit));
      v /= 10;
    }
    return decimal.toString();
  }

  // Manual conversion: decimal string to int
  private int fromDecimal(String decimal) {
    int result = 0;
    for (int i = 0; i < decimal.length(); i++) {
      char c = decimal.charAt(i);
      if (c < '0' || c > '9') {
        throw new IllegalArgumentException("Invalid decimal character: " + c);
      }
      int digit = c - '0';
      result = result * 10 + digit;
    }
    return result;
  }
}