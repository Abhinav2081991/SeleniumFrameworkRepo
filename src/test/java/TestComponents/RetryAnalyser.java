package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {

    int count=0;
    int retry=5;
    @Override
    public boolean retry(ITestResult result) {

        // Here in this case the retry mechanism will not happen for assertion errors.
        Throwable error = result.getThrowable();
        if(error instanceof AssertionError){
            return false;
        }

        // This will make sure that the retry is happening only for Selenium or Infra issues.
        if(count<retry){
            count++;
            return true;
        }
        return false;
    }
}
