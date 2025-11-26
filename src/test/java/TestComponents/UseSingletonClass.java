package TestComponents;

import org.openqa.selenium.WebDriver;

public class HiUseSingletonClass {

    public static void main(String[] args) {

        WebDriver driver =  SingletonWebDriver.getInstance().getDriver();
        driver.get("Application url");
        String title = driver.getTitle();
        System.out.println(title);
        SingletonWebDriver.getInstance().closeDriver();

    }
}
