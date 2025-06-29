package tests;

import core.HexadecimalBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class HexadecimalBaseTest {
  @Test
  public void testToBaseAndFromBase() {
    HexadecimalBase base = new HexadecimalBase();
    String input = "AB";
    String hex = base.toBase(input);
    assertEquals("41 42", hex);
    String text = base.fromBase(hex);
    assertEquals(input, text);
  }
}