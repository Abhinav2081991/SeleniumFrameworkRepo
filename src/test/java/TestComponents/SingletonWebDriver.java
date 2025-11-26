package TestComponents;

import org.openqa.selenium.WebDriver;

public class SingletonWebDriver {

    //1. Private constructor so that this class cannot be instantiated outside this class
    private SingletonWebDriver(){

    }

    //2. Private static variable
     private static SingletonWebDriver instance;

    //3. Private WebDriver reference
    private WebDriver driver;

    //4. public static method to provide the single instance of the class
    public static SingletonWebDriver getInstance(){
        if(instance==null){
            instance = new SingletonWebDriver();
        }
        return instance;
    }

    //5. public method to get driver instance
    public WebDriver getDriver(){
        return driver;
    }

    //6.  Method to close the driver and cleanup
    public void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver =null;
            instance=null;
        }
    }
}
