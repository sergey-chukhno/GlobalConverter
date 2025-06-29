package core;

public class ConversionService {
  private final Validator validator;

  public ConversionService() {
    this.validator = new Validator();
  }

  public String process(ConversionConfig config) {
    if (config == null)
      throw new IllegalArgumentException("Config cannot be null");
    // Validate base
    if (!validator.isValidBase(config.baseInput)) {
      throw new IllegalArgumentException("Invalid base: " + config.baseInput);
    }
    Base base = BaseFactory.getBase(config.baseInput);
    if (base == null) {
      throw new IllegalArgumentException("Could not resolve base: " + config.baseInput);
    }
    Converter converter = new Converter();
    String result;
    if ("toBase".equalsIgnoreCase(config.direction)) {
      String input = config.inputString;
      if (config.useCipher) {
        if (!validator.isValidCipher(config.cipherType)) {
          throw new IllegalArgumentException("Invalid cipher: " + config.cipherType);
        }
        if (!validator.isValidKey(config.cipherKey)) {
          throw new IllegalArgumentException("Invalid key: " + config.cipherKey);
        }
        Cipher cipher = new CaesarCipher();
        input = cipher.encrypt(input, config.cipherKey);
      }
      result = converter.toBase(input, base);
    } else {
      String input = config.inputString;
      result = converter.fromBase(input, base);
      if (config.useCipher) {
        if (!validator.isValidCipher(config.cipherType)) {
          throw new IllegalArgumentException("Invalid cipher: " + config.cipherType);
        }
        if (!validator.isValidKey(config.cipherKey)) {
          throw new IllegalArgumentException("Invalid key: " + config.cipherKey);
        }
        Cipher cipher = new CaesarCipher();
        result = cipher.decrypt(result, config.cipherKey);
      }
    }
    return result;
  }
}