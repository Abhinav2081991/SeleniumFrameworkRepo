package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count=0;
    int maxTry=1;

    @Override
    public boolean retry(ITestResult iTestResult) {

        Throwable error = iTestResult.getThrowable();
        if(error instanceof  AssertionError){
            return false;
        }

        if(count<maxTry){
            count++;
            return true;
        }
        return false;
    }
}
