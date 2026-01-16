package TestComponents;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AuthenticationPopUp {
    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        DesiredCapabilities ds = new DesiredCapabilities();
        ds.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        ds.setCapability(ChromeOptions.CAPABILITY, options);

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("user-data-dir=/path/to/custom-profile");

        WebDriver driver = new ChromeDriver(options);
        driver.get("http://username:password@the-insecure-site.com");

        // ---- In Selenium 4.0 ----
        ((HasAuthentication) driver).register(UsernameAndPassword.of("username","password"));
        driver.get("Application URL");
    }
}
