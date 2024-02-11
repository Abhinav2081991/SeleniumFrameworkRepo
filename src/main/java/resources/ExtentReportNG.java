package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    public static ExtentReports getExtentObject(){

        String reportPath = System.getProperty("user.dir")+ "//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Test Report for Practice");
        reporter.config().setDocumentTitle("Test Document for practice");

        ExtentReports report = new ExtentReports();
        report.attachReporter(reporter);
        report.setSystemInfo("Tester","Abhinav");
        return report;
    }

}
