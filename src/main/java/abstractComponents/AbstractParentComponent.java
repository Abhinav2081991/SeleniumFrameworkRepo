package abstractComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageObjects.OrderPage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class AbstractParentComponent {

    WebDriver driver;

    public AbstractParentComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(),' ORDERS')]")
    WebElement orderPageButton;

    public void waitForElementToBeInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions
                .invisibilityOf(element));
    }

    public void waitForElementToBeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(element));
    }

    public void waitForElementToBeVisibleWebElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions
                .visibilityOf(element));
    }

    public OrderPage navigateToOrderPage() {
        orderPageButton.click();
        return new OrderPage(driver);
    }

    public void handleWindows() {

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> itr = windows.iterator();
        String parentWindow = itr.next();
        while (itr.hasNext()) {
            String childWindow = itr.next();
            driver.switchTo().window(childWindow);

//            driver.findElement(RelativeLocator.with(By.xpath("")).above(""));
//            Actions actions = new Actions(driver);
//            actions.moveToElement(driver.findElement(By.xpath(""))).click().keyDown(Keys.SHIFT).sendKeys("Text");

//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("window.scrollBy(0,500)");
//            js.executeScript("document.querySelector("css").scrollBy(5,5000)");

        }
    }


    public void takeScreenShotInFailure(String methodName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.sir") + "//screenshots//" + methodName + ".png"));
    }

    /*
    Take Screenshot on Failure with Cucumber
     */
    public void takeScreenShotOnFailureCucumber(Scenario scenario){
        TakesScreenshot ts = (TakesScreenshot) driver;
        if(scenario.isFailed()) {
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"Failed.png", scenario.getName());
        }

    }

    public void FluentWaitforWebElements(WebElement webElement) {

        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(webElement));

        String s = "1";
        Integer i = Integer.valueOf(s);
        int ii = Integer.parseInt(s);
        int a = 10;
        Integer b = Integer.valueOf(11);
        String ss = String.valueOf(a);
        String g = b.toString();
    }

    public void checkBrokenLinks() throws IOException {
        String link = driver.findElement(By.tagName("a")).getAttribute("href");
        HttpURLConnection con = (HttpURLConnection) new URL(link).openConnection();
        con.setRequestMethod("HEAD");
        con.connect();
        int responseCode = con.getResponseCode();
        if (responseCode > 400) {
            System.out.println("Broken Link");
        }
    }

        /*
            Relative Locators.
        driver.findElement(RelativeLocator.with(By.xpath(""))
                .above(driver.findElement(By.xpath("")))).getText();

        driver.findElement(RelativeLocator.with(By.xpath(""))
                .below(driver.findElement(By.xpath("")))).getText();

        driver.findElement(RelativeLocator.with(By.xpath(""))
                .toLeftOf(driver.findElement(By.xpath("")))).getText();

        driver.findElement(RelativeLocator.with(By.xpath(""))
                .toRightOf(driver.findElement(By.xpath("")))).getText();
 */

//            driver.switchTo().window(WindowType.TAB);
//            driver.switchTo().window(WindowType.TAB);

    //Partial Screenshot
//        File file1 = driver.findElement(By.xpath("")).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(file1, new File("test/file.txt"));

    /*
    Rectangle class
     */
    public void TestRectangle(){
        //Get Dimension using Rectangle Class
        WebElement webElement = driver.findElement(By.cssSelector(""));
        Rectangle  rect = webElement.getRect();
        rect.getDimension().getHeight();
        int height = rect.getHeight();
        int width = rect.getWidth();
    }

    /*
    Read from JSON file
     */
    public List<HashMap<String, String>> readFromJSON() throws IOException {
        String jsonContent = FileUtils.readFileToString(new File("File name with Path"), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> list = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return  list;
    }

    /*
    Handle File Downloads
     */
    public void handleDownloads(){
        ChromeOptions options = new ChromeOptions();
        Map<String, String> prefs = new HashMap<>();
        prefs.put("download.default.directory","<Path to download the file");
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver1  =  new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("<Path of Downloaded dir>"), 0));

        // to check if the file exists
        File file = new File("<Path and of Downloaded file>");
        Assert.assertTrue(file.exists());
    }

    public void uploadFilesUsingRobotClass(String filepath) throws AWTException {
        
        //Click on upload button to that dialog box appears - driver.findElement(By.id("uploadBtn")).click();
        //Copy the file path in context
        StringSelection selection = new StringSelection(filepath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null );

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //Preferred way : driver.findElement(By.id("fileUpload")).sendKeys("C:\\path\\file.txt");
    }
    
    public void scroll(WebElement element){

        try{
            Actions action = new Actions(driver);
            action.scrollToElement(element);
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("argument[0].scrollInView(true)", element);
        } catch (Throwable e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,500);");
        }

    }
}
