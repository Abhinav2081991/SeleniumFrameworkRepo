package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReportNG;

import java.io.File;
import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener   {

    ExtentTest test;
    ExtentReports report = ExtentReportNG.getExtentObject();
    ThreadLocal<ExtentTest> thread  = new ThreadLocal<ExtentTest>();
    WebDriver driver;

    @Override
    public void onTestStart(ITestResult result) {
        test = report.createTest(result.getMethod().getMethodName());
        thread.set(test);

    }
    @Override
    public void onTestSuccess(ITestResult result) {

        thread.get().pass("Test Case is passed "+ result.getTestName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

            TakesScreenshot ts = (TakesScreenshot) driver;
            File file = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file,new File(System.getProperty("user.dir")+"/src/test/java/screenshots/"+ result.getTestName() + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }
    @Override
    public void onStart(ITestContext context) {
    }
    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }
}
