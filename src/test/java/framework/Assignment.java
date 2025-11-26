package framework;

import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Assignment {

    @Test
    public void getAllLinks() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        driver.findElement(By.xpath("//*[@class='gLFyf']")).sendKeys("Way2Automation");
        driver.findElement(By.cssSelector("input[class='gNO89b']")).click();
        List<WebElement> listOfSearch = driver.findElements(By.xpath("//h3"));
        for(WebElement w : listOfSearch){
            if(w.getText().contains("Way2Automation")){
                File file = w.getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(file,new File (System.getProperty("user.dir")+ "//screenshots//file.png"));
                w.click();
                break;
            }
        }
        List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
        for(WebElement m : allLinks){
            System.out.println(m.getAttribute("href"));
            HttpURLConnection  con = (HttpURLConnection)new URL(m.getAttribute("href")).openConnection();
            con.setRequestMethod("HEAD");
            con.connect();
            if(con.getResponseCode()>400){
                System.out.println("Links are broken");
            }
            else{
                System.out.println("All links are fine");
            }

        }
    }
}
