package core;

/**
 * Implements the Caesar cipher algorithm for encryption and decryption of
 * strings.
 */
public class CaesarCipher implements Cipher {
  @Override
  public String encrypt(String input, int key) {
    if (input == null)
      return null;
    StringBuilder sb = new StringBuilder();
    for (char c : input.toCharArray()) {
      if (c >= 'A' && c <= 'Z') {
        sb.append((char) ('A' + (c - 'A' + key) % 26));
      } else if (c >= 'a' && c <= 'z') {
        sb.append((char) ('a' + (c - 'a' + key) % 26));
      } else if (c >= '0' && c <= '9') {
        sb.append((char) ('0' + (c - '0' + key) % 10));
      } else {
        sb.append(c); // leave other characters unchanged
      }
    }
    return sb.toString();
  }

  @Override
  public String decrypt(String input, int key) {
    if (input == null)
      return null;
    StringBuilder sb = new StringBuilder();
    for (char c : input.toCharArray()) {
      if (c >= 'A' && c <= 'Z') {
        sb.append((char) ('A' + (c - 'A' - key + 26) % 26));
      } else if (c >= 'a' && c <= 'z') {
        sb.append((char) ('a' + (c - 'a' - key + 26) % 26));
      } else if (c >= '0' && c <= '9') {
        sb.append((char) ('0' + (c - '0' - key + 10) % 10));
      } else {
        sb.append(c); // leave other characters unchanged
      }
    }
    return sb.toString();
  }
}