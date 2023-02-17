import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.How.*;

public class HotelSearch {
    @Test
    public void searchHotel() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match' and text()='Dubai']")).click();

        driver.findElement(By.name("checkin")).sendKeys("17/03/2023");
        driver.findElement(By.name("checkin")).click();

        driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//th[@class='switch' and text()='February 2023']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElements(By.xpath("//span[@class='month' and text()='Mar']"))
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
        driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.id("adultMinusBtn")).click();
        driver.findElement(By.id("travellersInput")).click();


        driver.findElement(By.xpath("//button[@type='submit' and text()=' Search']")).click();

        List<String> hotelsList = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("innerHTML"))
                .collect(Collectors.toList());



        System.out.println(hotelsList.size());
        hotelsList.forEach(System.out::println);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Jumeirah Beach Hotel",hotelsList.get(0),"Failed to find");
        softAssert.assertEquals("Oasis Beach Tower",hotelsList.get(1),"Failed to find");
        softAssert.assertEquals("Rose Rayhaan Rotana",hotelsList.get(2),"Failed to find");
        softAssert.assertEquals("Hyatt Regency Perth",hotelsList.get(3),"Failed to find");


        driver.quit();
        softAssert.assertAll();
    }
}
