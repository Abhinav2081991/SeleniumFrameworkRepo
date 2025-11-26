package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {

    int count=0;
    int retry=5;
    @Override
    public boolean retry(ITestResult iTestResult) {

        if(count<retry){
            count++;
            return true;
        }
        return false;
    }
}
