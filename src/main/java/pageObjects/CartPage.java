package pageObjects;

import abstractComponents.AbstractComponent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="div[class='subtotal cf ng-star-inserted'] button")
    WebElement checkOutButton;

    @FindBy(css="div ul h3")
    List<WebElement> checkedOutProducts;

    public void clickOnCheckOutButton(){
        try{
            checkOutButton.click();
        }
        catch (Exception e){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();",checkOutButton);
        }
    }

    public boolean validateTheCheckedOutProducts(String productName){
        return checkedOutProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
    }



}
