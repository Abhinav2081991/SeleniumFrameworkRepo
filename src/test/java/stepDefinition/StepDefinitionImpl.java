package stepDefinition;

import Context.TestContext;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepDefinitionImpl extends BaseTest {

    WebDriver driver;

//    // Dependency Injection.
//    TestContext testContext;
//
//    public StepDefinitionImpl(TestContext testContext){
//        this.testContext = testContext;
//    }

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

//        OR Using Dependency injection (Pico Container - Constructor Based.)

//        testContext.getHomePage().selectProduct(productName);
//        testContext.getHomePage().clickCartButton();

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
//        List<Map<String,String>> map = dataTable.asMaps(String.class, String.class);
//        for(Map<String,String> m: map){
//            System.out.println(m.get("Item"));
//            System.out.println(m.get("Quantity"));
//        }
    }

    @Given("I want to test Datatable with Item and Quantity Lists")
    public void i_want_to_test_datatable_with_item_and_quantity_lists(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> list = dataTable.asLists(String.class);
        System.out.println("Printing List of List Items");
        for(List<String> m: list){
            System.out.println(m.get(0));
            System.out.println(m.get(1));
        }
    }

// Implementation of Datatable feature
@Given("I want to test Datatable with Item and Quantity Maps")
public void i_want_to_test_datatable_with_item_and_quantity_maps(io.cucumber.datatable.DataTable datatable) {
        // Write code here that turns the phrase above into concrete actions
        List<Map<String,String>>  list =  datatable.asMaps(String.class, String.class);
        System.out.println("printing List of Map Items");
        for(Map<String,String> s : list){
            System.out.println(s.get("Item"));
            System.out.println(s.get("Quantity"));

        }

    }

    @Given("I want to use the list as datatable lists")
    public void i_want_to_use_the_list_as_datatable_lists(io.cucumber.datatable.DataTable dataTable) {
        List<String> list = dataTable.asList();
        System.out.println("Printing single list column using Datatable");
        for(String s: list){
            System.out.println(s);
        }
    }





}
