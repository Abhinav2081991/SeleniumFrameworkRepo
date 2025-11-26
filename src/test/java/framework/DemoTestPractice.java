package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DemoTestPractice {



    @Test
    public void tests() throws IOException, InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/");
        String title = driver.getTitle();
        System.out.println("Title is " + title);

//        WebElement ele = driver.findElement(RelativeLocator.with(By.tagName("a")).below(By.xpath("//a[contains(text(), 'A/B Testing')]")));
////        ele.click();
//        File file = ele.getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/TESTPARTIALSCREENSHOT.png"));
//         Rectangle rect = ele.getRect();
//        System.out.println(rect.getDimension());
//        System.out.println(rect.getHeight());
//        System.out.println(rect.getWidth());
//        System.out.println(rect.getX());
//        System.out.println(rect.getY());

        // Handling shadow dom elements

//        driver.findElement(By.xpath("//*[contains(text(),'Shadow DOM')]")).click();
//        WebElement parentShadowDOM = driver.findElement(By.xpath("//my-paragraph"));
//        SearchContext shadow1 = (SearchContext) ((JavascriptExecutor)driver).executeScript("return arguments[0].shadowRoot", parentShadowDOM);
//        System.out.println(shadow1.findElement(By.cssSelector("span")).getText());

        driver.findElement(By.xpath("//*[contains(text(),'Shadow DOM')]")).click();

        // Wait a bit for the page to load Shadow DOM content
        Thread.sleep(1000);  // or use WebDriverWait for better practice

        WebElement parentShadowDOM = driver.findElement(By.cssSelector("my-paragraph"));

        SearchContext shadow1 = (SearchContext) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", parentShadowDOM);

// ✔ Correct selector inside shadow root
        WebElement shadowTextElement = shadow1.findElement(By.cssSelector("slot"));
        System.out.println("Shadow DOM text: " + shadowTextElement.getText());
        String text = driver.findElement(By.xpath("//my-paragraph/span")).getText();
        System.out.println(text);

    }
}
