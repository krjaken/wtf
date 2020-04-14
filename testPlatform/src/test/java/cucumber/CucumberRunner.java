package cucumber;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;


@CucumberOptions(features = {"src/test/java/cucumber/test.feature"},
        glue = "src/test/java/cucumber/MyStepdefs",
        strict = true,
        plugin =  {"summary","json:target/cucumber-report.json"})
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
