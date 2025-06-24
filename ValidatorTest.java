public class ValidatorTest {
  public static void main(String[] args) {
    Validator validator = new Validator();

    // Test isValidString
    System.out.println("Testing isValidString:");
    System.out.println("'Hello123' -> " + validator.isValidString("Hello123")); // true
    System.out.println("'Hello 123' -> " + validator.isValidString("Hello 123")); // false
    System.out.println("'!@#' -> " + validator.isValidString("!@#")); // false
    System.out.println("'' (empty) -> " + validator.isValidString("")); // false
    System.out.println("null -> " + validator.isValidString(null)); // false
    System.out.println();

    // Test isValidBase
    System.out.println("Testing isValidBase:");
    System.out.println("'hexadecimal' -> " + validator.isValidBase("hexadecimal")); // true
    System.out.println("'-h' -> " + validator.isValidBase("-h")); // true
    System.out.println("'octal' -> " + validator.isValidBase("octal")); // true
    System.out.println("'-o' -> " + validator.isValidBase("-o")); // true
    System.out.println("'decimal' -> " + validator.isValidBase("decimal")); // true
    System.out.println("'-d' -> " + validator.isValidBase("-d")); // true
    System.out.println("'binary' -> " + validator.isValidBase("binary")); // true
    System.out.println("'-b' -> " + validator.isValidBase("-b")); // true
    System.out.println("'text' -> " + validator.isValidBase("text")); // true
    System.out.println("'-t' -> " + validator.isValidBase("-t")); // true
    System.out.println("'HEX' -> " + validator.isValidBase("HEX")); // false
    System.out.println("'base64' -> " + validator.isValidBase("base64")); // false
    System.out.println();

    // Test isValidCipher
    System.out.println("Testing isValidCipher:");
    System.out.println("'caesar' -> " + validator.isValidCipher("caesar")); // true
    System.out.println("'-c' -> " + validator.isValidCipher("-c")); // true
    System.out.println("'aes' -> " + validator.isValidCipher("aes")); // false
    System.out.println("'' (empty) -> " + validator.isValidCipher("")); // false
    System.out.println();

    // Test isValidKey
    System.out.println("Testing isValidKey:");
    System.out.println("3 -> " + validator.isValidKey(3)); // true
    System.out.println("0 -> " + validator.isValidKey(0)); // false
    System.out.println("-1 -> " + validator.isValidKey(-1)); // false
  }
}