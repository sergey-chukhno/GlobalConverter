package tests;

import core.DecimalBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class DecimalBaseTest {
  @Test
  public void testToBaseAndFromBase() {
    DecimalBase base = new DecimalBase();
    String input = "AB";
    String decimal = base.toBase(input);
    assertEquals("65 66", decimal);
    String text = base.fromBase(decimal);
    assertEquals(input, text);
  }
}