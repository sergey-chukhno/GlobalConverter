package tests;

import core.BinaryBase;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryBaseTest {
  @Test
  public void testToBaseAndFromBase() {
    BinaryBase base = new BinaryBase();
    String input = "AB";
    String binary = base.toBase(input);
    assertEquals("01000001 01000010", binary);
    String text = base.fromBase(binary);
    assertEquals(input, text);
  }
}