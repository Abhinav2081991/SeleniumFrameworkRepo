package abstractComponents;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.OrderPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[contains(text(),' ORDERS')]")
    WebElement orderPageButton;

    public void waitForElementToBeInvisible(WebElement element){
        WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions
                .invisibilityOf(element));
    }

    public void waitForElementToBeVisible(By element){
        WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(element));
    }

    public void waitForElementToBeVisibleWebElement(WebElement element){
        WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions
                .visibilityOf(element));
    }

    public OrderPage navigateToOrderPage(){
        orderPageButton.click();
        return new OrderPage(driver);
    }

    public void handleWindows(){

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> itr = windows.iterator();

        String parentWindow = itr.next();

        while(itr.hasNext()){
            String childWindow = itr.next();
            driver.switchTo().window(childWindow);

//            driver.findElement(RelativeLocator.with(By.xpath("")).above(""));

        }
    }


    public void takeScreenShotInFailure(String methodName) throws IOException {
        TakesScreenshot ts  = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.sir")+"//screenshots//"+methodName+".png"));

    }

    public void testFluentWait(){

        Wait wait =  new FluentWait(driver).withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(""))));
    }

}
