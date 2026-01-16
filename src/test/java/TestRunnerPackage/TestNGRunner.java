package TestRunnerPackage;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber",
        glue ="stepDefinition",
        monochrome = true,
        dryRun = false,
        plugin = {"pretty",
                "json:target/cucumber-reports/cucumber.json", "html:target/cucumber.html"},
        tags = "@test1 or @test2 or @test3")
        //tags = "@test1 or @test2 and @test3")
//,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
public class TestNGRunner extends AbstractTestNGCucumberTests {
}


/*
Tags usage -
@a and @b	Both tags required
@a or @b	Either tag
not @a	Exclude tag
 */