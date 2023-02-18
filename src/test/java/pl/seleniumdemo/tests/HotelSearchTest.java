package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {
    @Test
    /* Searching for hotel*/
    public void searchHotelTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("20/04/2023","29/04/2023");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

        List<String> hotelsList = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("innerHTML"))
                .toList();


        System.out.println(hotelsList.size());
        hotelsList.forEach(System.out::println);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(hotelsList.get(0), "Jumeirah Beach Hotel", "Failed to find");
        softAssert.assertEquals(hotelsList.get(1), "Oasis Beach Tower", "Failed to find");
        softAssert.assertEquals(hotelsList.get(2), "Rose Rayhaan Rotana", "Failed to find");
        softAssert.assertEquals(hotelsList.get(3), "Hyatt Regency Perth", "Failed to find");

        softAssert.assertAll();
    }

    @Test
    /* Searching for hotel without city name + assertion */
    public void searchHotel_v1Test() {

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
        Assert.assertEquals(noResultHeading.getText(), "No Results Found");

    }
}
