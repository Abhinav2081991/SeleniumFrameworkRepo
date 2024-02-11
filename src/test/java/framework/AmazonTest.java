package framework;

import TestComponents.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LandingPage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class AmazonTest extends BaseTest {


    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void initiateTheBrowser() throws IOException {
        this.driver = initializeDriver();
    }

//    @AfterMethod(alwaysRun = true)
//    public void closeTheBrowser() throws IOException {
//        driver.close();
//    }

    @Test(groups = "TestAmazon")
    public void testAmazon(){

        LandingPage lp =new LandingPage(driver);

        driver.get("https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_7hz2t19t5c_e&adgrpid=155259815513&hvpone=&hvptwo=&hvadid=676742245123&hvpos=&hvnetw=g&hvrand=8583074760114551966&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9062046&hvtargid=kwd-10573980&hydadcr=14453_2367553");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("iphone");
        driver.findElement(By.xpath("//*[@id='nav-search-submit-button']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"))));


        WebElement childDriver = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));

        List<WebElement> listNames = childDriver.findElements(By.xpath("//*[contains(text(),'Phone')]"));
        List<WebElement> listPrices = childDriver.findElements(By.xpath("//span[@class = 'a-price']"));

        listNames.stream().limit(5).forEach(s-> System.out.println(s.getText()));

        ;Assert.assertEquals(1,2);
//        listPrices.stream().limit(5).forEach(a-> System.out.println(a.getText()));

        //Fluent Wait

//        WebDriverWait wait = new FluentWait<WebDriver>(driver)
//                .withTimeout(Duration.ofSeconds(5))
//                .pollingEvery(Duration.ofSeconds(5))
//                .ignoring(NoSuchElementException.class);
//
//        wait.until(ExpectedConditions.visibilityOf())


//        Actions action = new Actions(driver);
//
//        action.moveToElement(lp.password).click().keyDown(Keys.SHIFT). sendKeys("test");


    }







}
