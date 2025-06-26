package tests;

import core.HexadecimalBase;

public class HexadecimalBaseTest {
  public static void main(String[] args) {
    HexadecimalBase hexBase = new HexadecimalBase();

    // Test toBase
    String input = "Hello123";
    String hex = hexBase.toBase(input);
    System.out.println("toBase(\"Hello123\"): " + hex);

    // Test fromBase
    String restored = hexBase.fromBase(hex);
    System.out.println("fromBase(hex): " + restored);

    // Edge cases
    String empty = "";
    System.out.println("toBase(\"\"): '" + hexBase.toBase(empty) + "'");
    System.out.println("fromBase(\"\"): '" + hexBase.fromBase(empty) + "'");

    // Test with special characters (should match ASCII)
    String special = "AZaz09";
    String specialHex = hexBase.toBase(special);
    System.out.println("toBase(\"AZaz09\"): " + specialHex);
    System.out.println("fromBase(specialHex): " + hexBase.fromBase(specialHex));
  }
}