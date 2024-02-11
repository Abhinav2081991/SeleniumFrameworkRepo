package framework;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.LandingPage;
import pageObjects.PaymentPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestWithFramework2 extends BaseTest {

 WebDriver driver;

 @BeforeMethod(alwaysRun = true)
 public void initiateTheBrowser() throws IOException {
     this.driver = initializeDriver();
 }

 @AfterMethod(alwaysRun = true)
 public void closeTheBrowser() throws IOException {
     this.driver.close();
 }

//    @Test
//    public void test_Validate_Errors_While_Login() throws IOException {
//
//        LandingPage lp = new LandingPage(driver);
//        launchApplicationWIthINvalidCredentials();
//        String errorMessageActual = lp.getErrorMessage();
//        Assert.assertEquals(errorMessageActual,"Incorrect email or password.");
//
//    }

    @Test(groups = "PositiveFlow2",dataProvider = "getDataExcel",retryAnalyzer = Retry.class, enabled = false)
    public void test1_FromExcel_ArrayList(ArrayList<String> input) throws IOException {

        LandingPage lp = new LandingPage(driver);
        HomePage hp = new HomePage(driver);
        CartPage cp = new CartPage(driver);
        PaymentPage pp =  new PaymentPage(driver);
        launchApplication(input.get(0), input.get(1));
//        lp.goTo("https://rahulshettyacademy.com/client");
//        lp.login("abhinavtest@gmail.com","Newchandela47@");
        hp.selectProduct(input.get(2));
        hp.clickCartButton();
        Assert.assertTrue(cp.validateTheCheckedOutProducts(input.get(2)));
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
    public Object[][] getDataExcel() throws IOException {

        ArrayList<String> al1 = getDataFromExcel("Purchase Order 1");
        ArrayList<String> al2 = getDataFromExcel("Purchase Order 2");
        ArrayList<String> al4 = getDataFromExcel("Purchase Order 4");
       return new Object[][]{{al1},{al2},{al4}};
    }


    @Test(groups = "PositiveFlow2",dataProvider = "getDataExcelObjectArray")
    public void test2_From_Excel_DataProvider(String email, String password, String productName,String test, String tcNo) throws IOException {

        LandingPage lp = new LandingPage(driver);
        HomePage hp = new HomePage(driver);
        CartPage cp = new CartPage(driver);
        PaymentPage pp =  new PaymentPage(driver);
        launchApplication(email, password);
//        lp.goTo("https://rahulshettyacademy.com/client");
//        lp.login("abhinavtest@gmail.com","Newchandela47@");
        hp.selectProduct(productName);
        hp.clickCartButton();
        Assert.assertTrue(cp.validateTheCheckedOutProducts(productName));
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
    public Object[][] getDataExcelObjectArray() throws IOException {

        FileInputStream file  = new FileInputStream(System.getProperty("user.dir")+"//testDataExcel.xlsx");
        XSSFWorkbook workbook  =new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("testDataSheet");

        DataFormatter formatter =  new DataFormatter();

        int rowsCount = sheet.getPhysicalNumberOfRows();
        XSSFRow row = sheet.getRow(0);
        int columnCount = row.getLastCellNum();
        Object[][] data = new Object[rowsCount-1][columnCount];

        for(int i = 0; i<rowsCount-1;i++){

            row =  sheet.getRow(i+1);
            for(int j=0; j<columnCount; j++){
                XSSFCell cell = row.getCell(j);
                data[i][j] = formatter.formatCellValue(cell);
            }
        }

        return data;

    }



}
