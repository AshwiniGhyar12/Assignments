package SeleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;


public class HomePage
{
WebDriver driver;
public HomePage(WebDriver driver)
{
    this.driver=driver;
    PageFactory.initElements(driver,this);
}
@FindBy(xpath = "//span[contains(text(),'Login')]")
WebElement loginButton;

public void navigateToFlipkartApplication()
{
    driver.get("https://www.flipkart.com/");
}
public void getLinksUsingForEach()
{
    long start = System.currentTimeMillis();
    List<WebElement> links = driver.findElements(By.tagName("a"));
    System.out.println("Total links count on Flipkart homepage: "+links.size());
    System.out.println("Links as below");
    for (WebElement i:links)
    {
        System.out.println(i.getAttribute("href"));
    }
    long end = System.currentTimeMillis();
    System.out.println("Time taken:"+(end-start));
}
    public void getLinksUsingLambdaAndStream()
    {
        long start = System.currentTimeMillis();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links count on Flipkart homepage: "+links.size());
        System.out.println("Links as below");
        List<String> linkText = links.stream().map(ele -> ele.getAttribute("href")).toList();
        System.out.println("Size of links available:"+linkText.size());
        linkText.forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("Time taken:"+(end-start));
    }

    public void getLinksUsingParallelStream()
    {
        long start = System.currentTimeMillis();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total links count on Flipkart homepage: "+links.size());
        System.out.println("Links as below");
        long count = links.stream()
            .parallel()
            .map(element -> element.getAttribute("href"))
            .count();
        System.out.println("Count: " + count);
        List<String> linkText = links.stream()
                .parallel()
                .map(element -> element.getAttribute("href")).toList();
        linkText.forEach(System.out::println);
        long end = System.currentTimeMillis();
        System.out.println("Time taken:"+(end-start));
    }
}
