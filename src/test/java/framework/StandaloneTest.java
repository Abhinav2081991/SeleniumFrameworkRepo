package framework;

import TestComponents.RetryAnalyser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StandaloneTest implements WebDriverListener {

    @Test(retryAnalyzer = RetryAnalyser.class)
    public void test(){

        WebDriverManager.chromedriver().setup();
        WebDriver driver =  new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        Assert.assertTrue(false);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); -- This is deprecated.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); 
        driver.findElement(By.xpath("//input[@id = 'userEmail']"))
                .sendKeys("abhinavtest@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Newchandela47@");
        driver.findElement(By.cssSelector("#login")).click();
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement product = products.stream()
                .filter(s->s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3"))
                        .findFirst().orElse(null);
        String cartProduct = product.findElement(By.cssSelector("b")).getText();

        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(5));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        WebElement toasterMessage = driver.findElements(By.cssSelector("#toast-container div div")).get(0);
        wait.until(ExpectedConditions.visibilityOf(toasterMessage));

        System.out.println(toasterMessage.getText());
        driver.findElement(By.cssSelector("#toast-container div")).getText();
        wait.until(ExpectedConditions.invisibilityOf(toasterMessage));
        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector("div ul h3"));
//        WebElement addedItem = cartItems.stream()
//                .filter(s->s.getText().equalsIgnoreCase("ZARA COAT 3"))
//                .findFirst().orElse(null);
        boolean result = cartItems.stream().anyMatch(s->s.getText().equalsIgnoreCase("ZARA COAT 3"));

//        Assert.assertEquals(addedItem.getText(),cartProduct);

        Assert.assertTrue(result);

        driver.findElement(By.cssSelector("div[class='subtotal cf ng-star-inserted'] button")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Credit Card N')]/following-sibling::input"))
                        .clear();
        driver.findElement(By.xpath("//div[contains(text(),'Credit Card N')]/following-sibling::input"))
                .sendKeys("5555 5555 5555 5555");
        driver.findElement(By.xpath("//div[@class='user__name mt-5']/input")).clear();
        driver.findElement(By.xpath("//div[@class='user__name mt-5']/input")).sendKeys("abhinavtiwaritest@gmail.com");
        driver.findElement(By.xpath("//input[@placeholder='Select Country']"))
                .sendKeys("India");
        List<WebElement> countries =driver.findElements(By.xpath("//input[@placeholder='Select Country']/parent::div/section//span"));

        countries.stream().filter(s->s.getText().equals("India")).findFirst().orElse(null).click();

        Select selectExpiryMonth = new Select(driver.findElement(By.xpath("//div[contains(text(),'Expiry')]/following-sibling::select[1]")));
        selectExpiryMonth.selectByVisibleText("02");

        Select selectExpiryYear = new Select(driver.findElement(By.xpath("//div[contains(text(),'Expiry')]/following-sibling::select[2]")));
        selectExpiryYear.selectByIndex(2);

        driver.findElement(By.xpath("//div[contains(text(),'CVV')]/following-sibling::input"))
                .sendKeys("444");
        driver.findElement(RelativeLocator.with(By.tagName("input"))
                        .below(driver.findElement(By.xpath("//div[contains(text(),'Name')]"))))
                .sendKeys("Abhinav Tiwari");

//        driver.findElement(RelativeLocator.with(By.tagName("input"))
//                        .below(driver.findElement(By.xpath("//div[contains(text(),'Apply')]"))))
//                .sendKeys("TEST");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector(".action__submit")));


//        driver.findElement(By.cssSelector(".action__submit")).click();

        String successfulMessage =driver.findElement(By.xpath("//h1[contains(@class,'hero-primary')]")).getText();
        Assert.assertEquals(successfulMessage,"THANKYOU FOR THE ORDER.");
    }

}
