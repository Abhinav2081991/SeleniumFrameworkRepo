package pageObjects;

import abstractComponents.AbstractParentComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractParentComponent {

    WebDriver driver;

    /**
    Constructor for Each Class for each Webpage.
     */
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
//    driver.findElement(By.xpath("//input[@id = 'userEmail']"))
//            .sendKeys("abhinavtest@gmail.com");
//        driver.findElement(By.id("userPassword")).sendKeys("Newchandela47@");
//        driver.findElement(By.cssSelector("#login")).click();

    @FindBy(xpath="//input[@id = 'userEmail']")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement password;

    @FindBy(css="#login")
    WebElement loginButton;

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public void goTo(String url){
        driver.get(url);
    }

    public void login(String email, String password){
        userEmail.sendKeys(email);
        this.password.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage(){
        waitForElementToBeVisibleWebElement(errorMessage);
        return errorMessage.getText();
    }
}
