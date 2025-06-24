public class OctalBaseTest {
  public static void main(String[] args) {
    OctalBase octalBase = new OctalBase();

    // Test toBase
    String input = "Hello123";
    String octal = octalBase.toBase(input);
    System.out.println("toBase(\"Hello123\"): " + octal);

    // Test fromBase
    String restored = octalBase.fromBase(octal);
    System.out.println("fromBase(octal): " + restored);

    // Edge cases
    String empty = "";
    System.out.println("toBase(\"\"): '" + octalBase.toBase(empty) + "'");
    System.out.println("fromBase(\"\"): '" + octalBase.fromBase(empty) + "'");

    // Test with special characters (should match ASCII)
    String special = "AZaz09";
    String specialOctal = octalBase.toBase(special);
    System.out.println("toBase(\"AZaz09\"): " + specialOctal);
    System.out.println("fromBase(specialOctal): " + octalBase.fromBase(specialOctal));
  }
}