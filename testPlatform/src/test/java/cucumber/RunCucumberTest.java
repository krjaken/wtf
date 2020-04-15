package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/cucumber",
        glue = "src/test/java/cucumber",
        tags = "@Smoke")
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
