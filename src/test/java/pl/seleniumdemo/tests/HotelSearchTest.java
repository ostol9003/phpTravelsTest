package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;

import java.util.List;

public class HotelSearchTest extends BaseTest {
    /* Searching for hotel*/
    @Test
    public void searchHotelTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

        List<String> hotelsList = hotelSearchPage.setCity("Dubai")
                .setDates("20/04/2023", "29/04/2023")
                .setTravellers(1, 1)
                .performSearch().getHotelNames();


        System.out.println(hotelsList.size());
        hotelsList.forEach(System.out::println);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(hotelsList.get(0), "Jumeirah Beach Hotel", "Failed to find");
        softAssert.assertEquals(hotelsList.get(1), "Oasis Beach Tower", "Failed to find");
        softAssert.assertEquals(hotelsList.get(2), "Rose Rayhaan Rotana", "Failed to find");
        softAssert.assertEquals(hotelsList.get(3), "Hyatt Regency Perth", "Failed to find");

        softAssert.assertAll();
        driver.quit();
    }

    /* Searching for hotel without city name*/
    @Test
    public void searchHotel_v1Test() {
        ResultsPage resultPage = new HotelSearchPage(driver)
                .setDates("15/03/2023", "29/03/2023")
                .setTravellers(0, 1)
                .performSearch();

        Assert.assertTrue(resultPage.getResultHeading().isDisplayed());
        Assert.assertEquals(resultPage.getHeadingText(), "No Results Found");

    }
}
