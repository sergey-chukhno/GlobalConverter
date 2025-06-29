package tests;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

public class VerboseRunListener extends RunListener {
  @Override
  public void testStarted(Description description) throws Exception {
    System.out.println("[RUNNING] " + description.getClassName() + "." + description.getMethodName());
  }

  @Override
  public void testFinished(Description description) throws Exception {
    System.out.println("[FINISHED] " + description.getClassName() + "." + description.getMethodName());
  }
}