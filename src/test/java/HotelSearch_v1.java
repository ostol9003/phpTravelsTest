import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/* Searching for hotel without city name + assertion */

public class HotelSearch_v1 {
    @Test
    public void searchHotel_v1() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");


        driver.findElement(By.name("checkin")).sendKeys("17/05/2023");
        driver.findElement(By.name("checkin")).click();

        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//th[@class='switch' and text()='February 2023']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//span[@class='month' and text()='May']"))
                .stream()
                .filter(WebElement::isDisplayed) //el -> el.isDisplayed()
                .findFirst()
                .ifPresent(WebElement::click); // el -> el.click()

        driver.findElement(By.name("checkout")).click();

        driver.findElements(By.xpath("//td[@class='day ' and text()='30']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);


        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.id("travellersInput")).click();

        driver.findElement(By.xpath("//button[@type='submit' and text()=' Search']")).click();

/*   // first option
        List<String> hotelsList = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("innerHTML"))
                .toList();

        Assert.assertTrue(hotelsList.size() > 0,"No results, hotels list empty!");
        System.out.println(hotelsList.size());
*/

        // second option

        WebElement noResultHeading = driver.findElement(By.xpath("//div[@class='itemscontainer']//h2"));
        Assert.assertTrue(noResultHeading.isDisplayed());
        Assert.assertEquals(noResultHeading.getText(),"No Results Found");
        driver.quit();

    }
}
