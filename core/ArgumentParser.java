package core;

public class ArgumentParser {
  public static ConversionConfig parseArgs(String[] args) {
    String inputString = null;
    String baseInput = null;
    String direction = "toBase";
    boolean useCipher = false;
    String cipherType = null;
    int cipherKey = 0;

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "--input":
          if (i + 1 < args.length)
            inputString = args[++i];
          break;
        case "--base":
          if (i + 1 < args.length)
            baseInput = args[++i];
          break;
        case "--direction":
          if (i + 1 < args.length)
            direction = args[++i];
          break;
        case "--encrypt":
          useCipher = true;
          break;
        case "--decrypt":
          useCipher = true;
          direction = "fromBase";
          break;
        case "--cipher":
          if (i + 1 < args.length)
            cipherType = args[++i];
          break;
        case "--key":
          if (i + 1 < args.length) {
            try {
              cipherKey = Integer.parseInt(args[++i]);
            } catch (NumberFormatException e) {
              cipherKey = 0;
            }
          }
          break;
      }
    }
    return new ConversionConfig(inputString, baseInput, direction, useCipher, cipherType, cipherKey);
  }
}