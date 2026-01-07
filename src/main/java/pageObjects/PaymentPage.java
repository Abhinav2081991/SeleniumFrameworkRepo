package pageObjects;

import abstractComponents.AbstractParentComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PaymentPage extends AbstractParentComponent {

    WebDriver driver;
    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//div[contains(text(),'Credit Card N')]/following-sibling::input")
    WebElement cardNumber;

    @FindBy(xpath="//div[@class='user__name mt-5']/input")
    WebElement email;

    @FindBy(xpath="//input[@placeholder='Select Country']")
    WebElement countryInput;

    @FindBy(xpath="//input[@placeholder='Select Country']/parent::div/section//span")
    List<WebElement> countrySuggestions;

    @FindBy(xpath="//div[contains(text(),'Expiry')]/following-sibling::select[1]")
    WebElement expiryMonth;

    @FindBy(xpath="//div[contains(text(),'Expiry')]/following-sibling::select[2]")
    WebElement expiryYear;

    @FindBy(xpath="//div[contains(text(),'Expiry')]/following-sibling::select[2]")
    WebElement cvvCode;

    By nameInput = By.tagName("input");

    @FindBy(xpath="//div[contains(text(),'Name')]/following-sibling::input")
    WebElement nameOnCard;

    @FindBy(css=".action__submit")
    WebElement placeOrder;

    @FindBy(xpath="//h1[contains(@class,'hero-primary')]")
    WebElement successfulMessage;


    public void enterCardNumber(String cardNumberDetails){
        waitForElementToBeVisibleWebElement(cardNumber);
        cardNumber.clear();
        cardNumber.sendKeys(cardNumberDetails);
    }

    public void enterEmail(String emailDetails){
        waitForElementToBeVisibleWebElement(email);
        email.clear();
        email.sendKeys(emailDetails);
    }

    public void enterCountry(String country){
        countryInput.sendKeys(country);
        try{
            countrySuggestions.stream().filter(s->s.getText().equals(country)).findFirst().orElse(null).click();
        }
        catch (Exception e){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();"
                    ,countrySuggestions.stream().filter(s->s.getText().equals(country)).findFirst().orElse(null));
        }

    }

    public void selectExpiryMonth(String month){
        Select selectExpiryMonth = new Select(expiryMonth);
        selectExpiryMonth.selectByVisibleText(month);
    }

    public void selectExpiryYear(String year){
        Select selectExpiryYear = new Select(expiryYear);
        selectExpiryYear.selectByVisibleText(year);
    }

    public void enterCVV(String cvvDetails){
        cvvCode.sendKeys(cvvDetails);
    }

    public void enterNameOnCard(String nameOnCardDetails){
        nameOnCard.sendKeys(nameOnCardDetails);
    }

    public void clickOnPlaceOrder(){

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", placeOrder);
        }catch (Exception e){
            placeOrder.click();
        }
    }

    public boolean validateSuccessfulMessage(String message){
        String successfulMessage1 =successfulMessage.getText();
        return successfulMessage1.equals(message);
    }
}
