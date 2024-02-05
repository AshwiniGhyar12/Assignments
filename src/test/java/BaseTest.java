import SeleniumAutomation.HomePage;
import SeleniumAutomation.StockPrices;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {
    WebDriver driver= new ChromeDriver();
    @Test
    public void retrieveLinks()
     {
        WebDriverManager.chromedriver().setup();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        HomePage page= new HomePage(driver);
        page.navigateToFlipkartApplication();
        System.out.println("Flipkart Homepage links using ForEach");
        page.getLinksUsingForEach();
        System.out.println("Flipkart Homepage links using lambda and stream");
        page.getLinksUsingLambdaAndStream();
        System.out.println("Flipkart Homepage links using parallel streams");
        page.getLinksUsingParallelStream();
        driver.close();
    }
    @Test
    public void compareStockPrices() throws IOException {
        WebDriverManager.chromedriver().setup();
        StockPrices sp= new StockPrices(driver);
        sp.navigateToReddiffPortal();
        sp.writeDataToExcel();
        HashMap<String, Double> map1 = sp.readXLSDatatoHashmap();
        HashMap<String, Double> map2 =sp.readDataUsingSelenium();
        Assert.assertEquals(map1, map2);
        System.out.println("Equals");
        driver.close();
    }
}
