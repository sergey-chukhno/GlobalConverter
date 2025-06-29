package tests;

import core.Validator;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidatorTest {
  @Test
  public void testIsValidString() {
    Validator v = new Validator();
    assertTrue(v.isValidString("Hello123"));
    assertFalse(v.isValidString("Hello 123!"));
  }

  @Test
  public void testIsValidBase() {
    Validator v = new Validator();
    assertTrue(v.isValidBase("hexadecimal"));
    assertTrue(v.isValidBase("-h"));
    assertFalse(v.isValidBase("foo"));
  }

  @Test
  public void testIsValidCipher() {
    Validator v = new Validator();
    assertTrue(v.isValidCipher("caesar"));
    assertTrue(v.isValidCipher("-c"));
    assertFalse(v.isValidCipher("rot13"));
  }

  @Test
  public void testIsValidKey() {
    Validator v = new Validator();
    assertTrue(v.isValidKey(1));
    assertFalse(v.isValidKey(0));
    assertFalse(v.isValidKey(-5));
  }

  @Test
  public void testIsValidBaseString() {
    Validator v = new Validator();
    assertTrue(v.isValidBaseString("41 42", "hexadecimal"));
    assertFalse(v.isValidBaseString("41 4G", "hexadecimal"));
    assertTrue(v.isValidBaseString("101 102", "octal"));
    assertFalse(v.isValidBaseString("101 108", "octal"));
    assertTrue(v.isValidBaseString("65 66", "decimal"));
    assertFalse(v.isValidBaseString("65 6A", "decimal"));
    assertTrue(v.isValidBaseString("01000001 01000010", "binary"));
    assertFalse(v.isValidBaseString("01000001 01000012", "binary"));
    assertTrue(v.isValidBaseString("Hello", "text"));
  }
}