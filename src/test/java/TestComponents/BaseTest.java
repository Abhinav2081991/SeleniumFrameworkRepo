package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import pageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class BaseTest {

    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    public WebDriver initializeDriver() throws IOException {
        Properties prop  = new Properties();
        prop.load( new FileInputStream(System.getProperty("user.dir")+"\\prop.properties"));
        String browserName =
                System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browserName");
        if(browserName.contains("Chrome")){
            ChromeOptions options  = new ChromeOptions();
            DesiredCapabilities ds =  new DesiredCapabilities();
            ds.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            ds.setAcceptInsecureCerts(true);
            ds.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
            ds.setCapability(CapabilityType.PLATFORM_NAME, "windows");
//            ds.setCapability(ChromeOptions.CAPABILITY, options);
//            ds.setCapability(EdgeOptions.CAPABILITY);
//            options.addArguments("headless");  --> for headless mode.

            options.merge(ds); // this is to merge the DeriredCapabilities and ChromeOptions                                                                                                                                                                                          

            /*
            This is for ThreadLocal, so that driver instance is not shared among the threads
             */
//            ThreadLocal<WebDriver> tl = new ThreadLocal<>();
//            WebDriver driver = new ChromeDriver();
//
//            tl.set(driver);
//
//            tl.get().findElement(By.xpath(""));
//            tl.get().findElement(By.xpath("")).getScreenshotAs(OutputType.FILE);
//            HasAuthentication ha = (HasAuthentication) driver;
//            ha.register(UsernameAndPassword.of("",""));

            WebDriverManager.chromedriver().setup();
            if(browserName.contains("headless")) {
                options.addArguments("--headless");
            }
            options.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(options);
            //driver.manage().window().setSize(new Dimension(1440,900));

        }
        else if (browserName.equalsIgnoreCase("Firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver =  new FirefoxDriver();
        }
        else
        {
            WebDriverManager.edgedriver().setup();
            driver =  new EdgeDriver();
        }
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1440,900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public WebDriver initializeSingletonThreadLocalDriver(){
        driver = DriverManagerSingletonClass.getDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public void launchApplication(String username, String password) throws IOException {
        LandingPage lp = new LandingPage(driver);
        lp.goTo("https://rahulshettyacademy.com/client");
        lp.login(username,password);
    }

    public void launchUrl() throws IOException {
        LandingPage lp = new LandingPage(driver);
        lp.goTo("https://rahulshettyacademy.com/client");
    }

    public void loginWithUsernameAndPassword(String username, String password) throws IOException {
        LandingPage lp = new LandingPage(driver);
        lp.login(username,password);
    }

    public void launchApplicationWIthINvalidCredentials() throws IOException {
        LandingPage lp = new LandingPage(driver);
        lp.goTo("https://rahulshettyacademy.com/client");
        lp.login("abhinavtest@gmail.com","Newchandela47@123");
//
//        Wait<WebDriver> w = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5))
//                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
//
//        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

    }

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File fileSource = ts.getScreenshotAs(OutputType.FILE);
        File fileDestination = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName+".png");
        FileUtils.copyFile(fileSource,fileDestination);
        return System.getProperty(  "user.dir")+"//reports//"+ testCaseName+".png";

    }

    public ArrayList<String> getDataFromExcel(String testCaseName) throws IOException {
        FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir")+"//testDataExcel.xlsx");
        XSSFWorkbook workbook =  new XSSFWorkbook(fileInput);
        int count = workbook.getNumberOfSheets();
        XSSFSheet sheet = null;
        ArrayList<String> arrayList = new ArrayList<String>();
        ArrayList<String> a1 = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> hashMap = new HashMap<Integer, ArrayList<String>>();
        for(int i=0;i<count;i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("testDataSheet")) {
                sheet = workbook.getSheetAt(i);
            }
        }

            Iterator<Row> rows = sheet.rowIterator();
            Row firstRow = rows.next();

            Iterator<Cell> cells = firstRow.cellIterator();
            int k=0;
            int column = 0;
            while(cells.hasNext()){
                Cell cellCalue = cells.next();
                if(cellCalue.getStringCellValue().equalsIgnoreCase("TestCases")){
                    column = k;
                }
                k++;
            }
//            int rowCount = firstRow.getPhysicalNumberOfCells();
//
//            Row r = sheet.getRow(1);
//            int colCount = r.getLastCellNum();
//
//            Object[][] data =  new Object[rowCount-1][colCount];
            System.out.println(column);

            while(rows.hasNext()){
                Row row = rows.next();
                if(row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)){

                    Iterator<Cell> cellsValues = row.cellIterator();
                    while(cellsValues.hasNext()){
                            Cell cell = cellsValues.next();
                        if(cell.getCellType() == CellType.STRING){
                            arrayList.add(cell.getStringCellValue());
                        }
                        else{
                            arrayList.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                        }
                    }
                }
            }
       return arrayList;
    }

//    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            getScreenShot(result.getMethod().getMethodName(), driver);
        }
        driver.close();
    }

}
