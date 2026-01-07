package TestComponents;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverManagerSingletonClass {

//    🔷 3️⃣ Driver Factory (Centralized Driver Control)

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //It helps achieve this by providing an independent WebDriver instance per thread.
    // This ensures that each thread has its own isolated WebDriver instance and variables copy,
    // preventing thread interference or data corruption and improving test stability.

    private DriverManagerSingletonClass(){
    }

    public static WebDriver getDriver(){
        if(driver.get() == null){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
            options.setAcceptInsecureCerts(true);

            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability(String.valueOf(UnexpectedAlertBehaviour.ACCEPT), CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR);
            dc.setCapability(CapabilityType.ENABLE_DOWNLOADS, true);
            dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            options.merge(dc);
            driver.set(new ChromeDriver(options));
        }
        return (WebDriver) driver.get();
    }

    public static void quitDriver(){
        if(driver.get()!=null){
            driver.get().quit();
            driver.remove();  // to prevent memory leaks.
        }
    }

}
