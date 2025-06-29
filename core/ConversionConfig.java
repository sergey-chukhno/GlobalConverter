package core;

public class ConversionConfig {
  public String inputString;
  public String baseInput;
  public String direction; // "toBase" or "fromBase"
  public boolean useCipher;
  public String cipherType;
  public int cipherKey;

  public ConversionConfig() {
  }

  public ConversionConfig(String inputString, String baseInput, String direction, boolean useCipher, String cipherType,
      int cipherKey) {
    this.inputString = inputString;
    this.baseInput = baseInput;
    this.direction = direction;
    this.useCipher = useCipher;
    this.cipherType = cipherType;
    this.cipherKey = cipherKey;
  }
}