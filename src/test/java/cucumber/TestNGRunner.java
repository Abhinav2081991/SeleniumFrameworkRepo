package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber",
        glue ="stepDefinition",
        monochrome = true,
        dryRun = false,
        plugin = {"html:target/cucumber.html"},
        tags = "@Datatable")
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
