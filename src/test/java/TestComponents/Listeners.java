package TestComponents;

import abstractComponents.AbstractComponent;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReportNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener   {

    ExtentTest test;
    ExtentReports report = ExtentReportNG.getExtentObject();
    ThreadLocal<ExtentTest> thread  = new ThreadLocal<ExtentTest>();

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
