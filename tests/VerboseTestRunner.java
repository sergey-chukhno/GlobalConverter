package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class VerboseTestRunner {
  public static void main(String[] args) {
    JUnitCore junit = new JUnitCore();
    junit.addListener(new VerboseRunListener());
    Result result = junit.run(
        BinaryBaseTest.class,
        DecimalBaseTest.class,
        HexadecimalBaseTest.class,
        OctalBaseTest.class,
        ValidatorTest.class);
    System.out.println("\nTotal tests run: " + result.getRunCount());
    System.out.println("Failures: " + result.getFailureCount());
    if (!result.wasSuccessful()) {
      result.getFailures().forEach(f -> System.out.println(f.toString()));
    } else {
      System.out.println("All tests passed!");
    }
  }
}