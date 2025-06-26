package core;

public class BinaryBase extends Base {
  public BinaryBase() {
    super("binary", "-b");
  }

  @Override
  public String toBase(String input) {
    if (input == null)
      return null;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      int ascii = input.charAt(i);
      String binary = toBinary(ascii);
      sb.append(binary);
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
    String[] binaryValues = input.split(" ");
    StringBuilder sb = new StringBuilder();
    for (String binary : binaryValues) {
      int ascii = fromBinary(binary);
      sb.append((char) ascii);
    }
    return sb.toString();
  }

  // Manual conversion: int to binary string (no prefix)
  private String toBinary(int value) {
    if (value == 0)
      return "0";
    StringBuilder binary = new StringBuilder();
    int v = value;
    while (v > 0) {
      int digit = v % 2;
      binary.insert(0, (char) ('0' + digit));
      v /= 2;
    }
    // Pad to 8 digits for ASCII
    while (binary.length() < 8) {
      binary.insert(0, '0');
    }
    return binary.toString();
  }

  // Manual conversion: binary string to int
  private int fromBinary(String binary) {
    int result = 0;
    for (int i = 0; i < binary.length(); i++) {
      char c = binary.charAt(i);
      if (c != '0' && c != '1') {
        throw new IllegalArgumentException("Invalid binary character: " + c);
      }
      int digit = c - '0';
      result = result * 2 + digit;
    }
    return result;
  }
}