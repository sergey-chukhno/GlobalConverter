package tests;

import core.OctalBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class OctalBaseTest {
  @Test
  public void testToBaseAndFromBase() {
    OctalBase base = new OctalBase();
    String input = "AB";
    String octal = base.toBase(input);
    assertEquals("101 102", octal);
    String text = base.fromBase(octal);
    assertEquals(input, text);
  }
}