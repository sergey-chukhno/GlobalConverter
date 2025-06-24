/**
 * Defines the contract for encryption and decryption methods.
 */
public interface Cipher {
  /**
   * Encrypts the input string using the provided key.
   * 
   * @param input The string to encrypt.
   * @param key   The encryption key.
   * @return The encrypted string.
   */
  String encrypt(String input, int key);

  /**
   * Decrypts the input string using the provided key.
   * 
   * @param input The string to decrypt.
   * @param key   The decryption key.
   * @return The decrypted string.
   */
  String decrypt(String input, int key);
}