package pl.seleniumdemo.tests;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.time.Duration;
import java.util.List;

public class HotelSearchTest extends BaseTest {
    /* Searching for hotel*/
    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("20/04/2023", "29/04/2023");
        hotelSearchPage.setTravellers(1, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelsList = resultsPage.getHotelNames();

        /*
        System.out.println(hotelsList.size());
        hotelsList.forEach(System.out::println);
        */

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(hotelsList.get(0), "Jumeirah Beach Hotel", "Failed to find");
        softAssert.assertEquals(hotelsList.get(1), "Oasis Beach Tower", "Failed to find");
        softAssert.assertEquals(hotelsList.get(2), "Rose Rayhaan Rotana", "Failed to find");
        softAssert.assertEquals(hotelsList.get(3), "Hyatt Regency Perth", "Failed to find");

        softAssert.assertAll();
        driver.quit();
    }

    /* Searching for hotel without city name + assertion */
    @Test
    public void searchHotel_v1Test() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

        hotelSearchPage.setDates("15/03/2023", "29/03/2023");
        hotelSearchPage.setTravellers(0, 1);
        hotelSearchPage.performSearch();

        ResultsPage resultPage = new ResultsPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(resultPage.getResultHeading()));

        Assert.assertEquals(resultPage.getHeadingText(), "No Results Found");

    }
}
