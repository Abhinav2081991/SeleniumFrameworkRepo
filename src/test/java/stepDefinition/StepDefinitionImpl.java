package stepDefinition;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    WebDriver driver;



    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        this.driver = initializeDriver();
        launchUrl();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_With_Username_And_Password(String username, String password) throws IOException {
        loginWithUsernameAndPassword(username, password);
    }


    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) {
        HomePage hp = new HomePage(driver);
        hp.selectProduct(productName);
        hp.clickCartButton();

    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) {
        CartPage cp =  new CartPage(driver);
        Assert.assertTrue(cp.validateTheCheckedOutProducts(productName));
        cp.clickOnCheckOutButton();

    }
        @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_confirmation_page(String string) {
        PaymentPage pp = new PaymentPage(driver);
        pp.enterCardNumber("5555 5555 5555 5555");
        pp.enterEmail("abhinavtiwaritest@gmail.com");
        pp.enterCountry("India");
        pp.selectExpiryMonth("02");
        pp.selectExpiryYear("16");
        pp.enterCVV("444");
        pp.enterNameOnCard("Abhinav Tiwari");
        pp.clickOnPlaceOrder();
        Assert.assertTrue(pp.validateSuccessfulMessage(string));
    }


    @Then("{string} is displayed")
    public void isDisplayed(String messgae) {
        LandingPage lp = new LandingPage(driver);
        String errorMessageActual = lp.getErrorMessage();
        Assert.assertEquals(errorMessageActual,messgae);
    }
}
