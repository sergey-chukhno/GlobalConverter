public class DecimalBaseTest {
  public static void main(String[] args) {
    DecimalBase decimalBase = new DecimalBase();

    // Test toBase
    String input = "Hello123";
    String decimal = decimalBase.toBase(input);
    System.out.println("toBase(\"Hello123\"): " + decimal);

    // Test fromBase
    String restored = decimalBase.fromBase(decimal);
    System.out.println("fromBase(decimal): " + restored);

    // Edge cases
    String empty = "";
    System.out.println("toBase(\"\"): '" + decimalBase.toBase(empty) + "'");
    System.out.println("fromBase(\"\"): '" + decimalBase.fromBase(empty) + "'");

    // Test with special characters (should match ASCII)
    String special = "AZaz09";
    String specialDecimal = decimalBase.toBase(special);
    System.out.println("toBase(\"AZaz09\"): " + specialDecimal);
    System.out.println("fromBase(specialDecimal): " + decimalBase.fromBase(specialDecimal));
  }
}