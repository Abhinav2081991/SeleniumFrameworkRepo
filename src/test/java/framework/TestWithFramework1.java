package framework;

import TestComponents.BaseTest;
import TestComponents.Retry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class TestWithFramework1  extends BaseTest {

 WebDriver driver;

 @BeforeMethod(alwaysRun = true)
 public void initiateTheBrowser() throws IOException {
     this.driver = initializeDriver();
 }

 @AfterMethod(alwaysRun = true)
 public void closeTheBrowser() throws IOException {
     driver.close();
 }


    @Test(groups = "ErrorHandling", retryAnalyzer = Retry.class)
    public void test_Validate_Errors_While_Login() throws IOException {

        LandingPage lp = new LandingPage(driver);
        launchApplicationWIthINvalidCredentials();
        String errorMessageActual = lp.getErrorMessage();
        Assert.assertEquals(errorMessageActual,"Incorrect email or password.");

    }

    @Test(groups = "PositiveFlow",retryAnalyzer = Retry.class)
    public void test1_Create_An_Order() throws IOException {

        LandingPage lp = new LandingPage(driver);
        HomePage hp = new HomePage(driver);
        CartPage cp = new CartPage(driver);
        PaymentPage pp =  new PaymentPage(driver);
        launchApplication("abhinavtest@gmail.com","Newchandela47@");
//        lp.goTo("https://rahulshettyacademy.com/client");
//        lp.login("abhinavtest@gmail.com","Newchandela47@");
        hp.getColorOfText();
        hp.selectProduct("ZARA COAT 3");
        hp.clickCartButton();
        Assert.assertTrue(cp.validateTheCheckedOutProducts("ZARA COAT 3"));
        cp.clickOnCheckOutButton();
        pp.enterCardNumber("5555 5555 5555 5555");
        pp.enterEmail("abhinavtiwaritest@gmail.com");
        pp.enterCountry("India");
        pp.selectExpiryMonth("02");
        pp.selectExpiryYear("16");
        pp.enterCVV("444");
        pp.enterNameOnCard("Abhinav Tiwari");
        pp.clickOnPlaceOrder();
        Assert.assertTrue(pp.validateSuccessfulMessage("THANKYOU FOR THE ORDER."));
    }


    @Test(groups = "PositiveFlow1",dataProvider = "testData",retryAnalyzer = Retry.class)
    public void test1_Create_An_Order_With_DataProvider(String username, String password, String ProductName) throws IOException {

        LandingPage lp = new LandingPage(driver);
        HomePage hp = new HomePage(driver);
        CartPage cp = new CartPage(driver);
        PaymentPage pp =  new PaymentPage(driver);
        launchApplication(username,password);
//        lp.goTo("https://rahulshettyacademy.com/client");
//        lp.login("abhinavtest@gmail.com","Newchandela47@");
        hp.selectProduct(ProductName);
        hp.clickCartButton();
        Assert.assertTrue(cp.validateTheCheckedOutProducts(ProductName));
        cp.clickOnCheckOutButton();
        pp.enterCardNumber("5555 5555 5555 5555");
        pp.enterEmail("abhinavtiwaritest@gmail.com");
        pp.enterCountry("India");
        pp.selectExpiryMonth("02");
        pp.selectExpiryYear("16");
        pp.enterCVV("444");
        pp.enterNameOnCard("Abhinav Tiwari");
        pp.clickOnPlaceOrder();
        Assert.assertTrue(pp.validateSuccessfulMessage("THANKYOU FOR THE ORDER."));
    }

    @DataProvider
    public Object[][] testData(){
        return new Object[][] {{"abhinavtest@gmail.com","Newchandela47@","ZARA COAT 3"},
                {"abhinavtest@gmail.com","Newchandela47@","IPHONE 13 PRO"} };
    }

    @Test(dependsOnMethods = "test1_Create_An_Order", groups = "PositiveGroup",retryAnalyzer = Retry.class)
    public void test3_Validate_Order() throws IOException {
        LandingPage lp = new LandingPage(driver);
        launchApplication("abhinavtest@gmail.com","Newchandela47@");
        OrderPage op = lp.navigateToOrderPage();
        Assert.assertTrue(op.getProductFromOrderList("ZARA COAT 3"));
    }



    @Test(groups = "PositiveFlow2",dataProvider = "testDataHashMap",retryAnalyzer = Retry.class)
    public void test1_Create_An_Order_HashMap(HashMap<String,String> input) throws IOException {

        LandingPage lp = new LandingPage(driver);
        HomePage hp = new HomePage(driver);
        CartPage cp = new CartPage(driver);
        PaymentPage pp =  new PaymentPage(driver);
        launchApplication(input.get("username"),input.get("password"));
//        lp.goTo("https://rahulshettyacademy.com/client");
//        lp.login("abhinavtest@gmail.com","Newchandela47@");
        hp.selectProduct(input.get("product"));
        hp.clickCartButton();
        Assert.assertTrue(cp.validateTheCheckedOutProducts(input.get("product")));
        cp.clickOnCheckOutButton();
        pp.enterCardNumber("5555 5555 5555 5555");
        pp.enterEmail("abhinavtiwaritest@gmail.com");
        pp.enterCountry("India");
        pp.selectExpiryMonth("02");
        pp.selectExpiryYear("16");
        pp.enterCVV("444");
        pp.enterNameOnCard("Abhinav Tiwari");
        pp.clickOnPlaceOrder();
        Assert.assertTrue(pp.validateSuccessfulMessage("THANKYOU FOR THE ORDER."));
    }

    @DataProvider
    public Object[][] testDataHashMap(){

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username","abhinavtest@gmail.com");
        map1.put("password","Newchandela47@");
        map1.put("product","IPHONE 13 PRO");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("username","abhinavtest@gmail.com");
        map2.put("password","Newchandela47@");
        map2.put("product","ZARA COAT 3");

        return new Object[][]{{map1},{map2}};

    }

    @Test(groups = "PositiveFlow3",dataProvider = "testDataHashMapFromJSON",retryAnalyzer = Retry.class)
    public void test1_Create_An_Order_HashMap_JSON(HashMap<String,String> input) throws IOException {

        LandingPage lp = new LandingPage(driver);
        HomePage hp = new HomePage(driver);
        CartPage cp = new CartPage(driver);
        PaymentPage pp =  new PaymentPage(driver);
        launchApplication(input.get("username"),input.get("password"));
//        lp.goTo("https://rahulshettyacademy.com/client");
//        lp.login("abhinavtest@gmail.com","Newchandela47@");
        hp.selectProduct(input.get("product"));
        hp.clickCartButton();
        Assert.assertTrue(cp.validateTheCheckedOutProducts(input.get("product")));
        cp.clickOnCheckOutButton();
        pp.enterCardNumber("5555 5555 5555 5555");
        pp.enterEmail("abhinavtiwaritest@gmail.com");
        pp.enterCountry("India");
        pp.selectExpiryMonth("02");
        pp.selectExpiryYear("16");
        pp.enterCVV("444");
        pp.enterNameOnCard("Abhinav Tiwari");
        pp.clickOnPlaceOrder();
        Assert.assertTrue(pp.validateSuccessfulMessage("THANKYOU FOR THE ORDER."));

    }

    @DataProvider
    public Object[][] testDataHashMapFromJSON() throws IOException {

        List<HashMap<String, String> > list = getDataFromJson(System.getProperty("user.dir")+"\\data.json");

        return new Object[][]{
                {list.get(0)},{list.get(1)}};

    }
    //THIS METHOD SHOULD BE PRESENT IN THE BastTest Class.
    public List<HashMap<String,String>> getDataFromJson(String filePath) throws IOException {

     String jsonText = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper  = new ObjectMapper();
        List<HashMap<String,String>> data =
                mapper.readValue(jsonText, new TypeReference<List<HashMap<String,String>>>() {
        });
        return data;
    }

}
