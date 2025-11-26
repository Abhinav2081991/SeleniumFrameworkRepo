package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends AbstractComponent {

    WebDriver driver;
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".mb-3")
    List<WebElement> products;

    By addToCart = By.cssSelector(".card-body button:last-of-type");

    By toasterMessage = By.cssSelector("#toast-container");

    @FindBy(css=".ng-animating")
    WebElement animation;

    @FindBy(css="#toast-container div")
    WebElement toaster;

    @FindBy(css="button[routerlink='/dashboard/cart']")
    WebElement cartButton;

    @FindBy(xpath = "//section[@id = 'sidebar']//div/*[@id = 'burgundy']")
    WebElement colouredText;

    public void selectProduct(String productName){

        WebElement product = products.stream()
                .filter(s->s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
        product.findElement(addToCart).click();
        waitForElementToBeInvisible(animation);
        waitForElementToBeVisible(toasterMessage);
    }

    public void clickCartButton(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",cartButton);
    }

    public String getColorOfText(){
        String color = colouredText.getAttribute("color");
        return color;
    }
}
