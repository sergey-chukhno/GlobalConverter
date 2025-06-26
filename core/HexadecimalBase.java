package core;

public class HexadecimalBase extends Base {
  public HexadecimalBase() {
    super("hexadecimal", "-h");
  }

  @Override
  public String toBase(String input) {
    if (input == null)
      return null;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      int ascii = input.charAt(i);
      String hex = toHex(ascii);
      sb.append(hex);
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
    String[] hexValues = input.split(" ");
    StringBuilder sb = new StringBuilder();
    for (String hex : hexValues) {
      int ascii = fromHex(hex);
      sb.append((char) ascii);
    }
    return sb.toString();
  }

  // Manual conversion: int to hex string (lowercase, no 0x prefix)
  private String toHex(int value) {
    if (value == 0)
      return "0";
    StringBuilder hex = new StringBuilder();
    int v = value;
    while (v > 0) {
      int digit = v % 16;
      char hexChar = (char) (digit < 10 ? '0' + digit : 'a' + (digit - 10));
      hex.insert(0, hexChar);
      v /= 16;
    }
    // Pad to 2 digits for ASCII
    while (hex.length() < 2) {
      hex.insert(0, '0');
    }
    return hex.toString();
  }

  // Manual conversion: hex string to int
  private int fromHex(String hex) {
    int result = 0;
    for (int i = 0; i < hex.length(); i++) {
      char c = hex.charAt(i);
      int digit;
      if (c >= '0' && c <= '9') {
        digit = c - '0';
      } else if (c >= 'a' && c <= 'f') {
        digit = 10 + (c - 'a');
      } else if (c >= 'A' && c <= 'F') {
        digit = 10 + (c - 'A');
      } else {
        throw new IllegalArgumentException("Invalid hex character: " + c);
      }
      result = result * 16 + digit;
    }
    return result;
  }
}