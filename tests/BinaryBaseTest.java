package tests;

import core.BinaryBase;

public class BinaryBaseTest {
  public static void main(String[] args) {
    BinaryBase binaryBase = new BinaryBase();

    // Test toBase
    String input = "Hello123";
    String binary = binaryBase.toBase(input);
    System.out.println("toBase(\"Hello123\"): " + binary);

    // Test fromBase
    String restored = binaryBase.fromBase(binary);
    System.out.println("fromBase(binary): " + restored);

    // Edge cases
    String empty = "";
    System.out.println("toBase(\"\"): '" + binaryBase.toBase(empty) + "'");
    System.out.println("fromBase(\"\"): '" + binaryBase.fromBase(empty) + "'");

    // Test with special characters (should match ASCII)
    String special = "AZaz09";
    String specialBinary = binaryBase.toBase(special);
    System.out.println("toBase(\"AZaz09\"): " + specialBinary);
    System.out.println("fromBase(specialBinary): " + binaryBase.fromBase(specialBinary));
  }
}