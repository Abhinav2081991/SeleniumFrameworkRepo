package stepDefinition;

import TestComponents.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.collections4.map.HashedMap;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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


    @Given("I have below items in the shopping cart")
    public void i_have_below_items_in_the_shopping_cart(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String,String>> list = dataTable.asMaps();
        for(Map<String,String> map : list){
            System.out.println(map.get("Item"));
            System.out.println(map.get("Quantity"));
        }
    }

    @Given("I have below items in the shopping cart list")
    public void i_have_below_items_in_the_shopping_cart_list(io.cucumber.datatable.DataTable dataTable) {
       List<List<String>> list = dataTable.asLists();

        System.out.println(list.get(0).get(0));
        System.out.println(list.get(0).get(1));
        System.out.println(list.get(1).get(0));
        System.out.println(list.get(1).get(1));
        System.out.println(list.get(2).get(0));
        System.out.println(list.get(2).get(1));


    }

}
