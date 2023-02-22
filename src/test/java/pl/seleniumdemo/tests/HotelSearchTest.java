package pl.seleniumdemo.tests;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.ExcelReader;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HotelSearchTest extends BaseTest {
    /* Searching for hotel*/
    @Test
    public void searchHotelTest() {
        ExtentTest test = extentReports.createTest("Search hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS,"Setting city done");
        hotelSearchPage.setDates("20/04/2023", "29/04/2023");
        test.log(Status.PASS,"Setting dates done");
        hotelSearchPage.setTravellers(1, 1);
        test.log(Status.PASS,"Setting travellers done");
        hotelSearchPage.performSearch();
        test.log(Status.PASS,"Performing search done");
        test.log(Status.PASS,"Screenshot", MediaEntityBuilder.createScreenCaptureFromPath("src/test/resources/screenshots/screenshot.png").build());

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
        test.log(Status.PASS,"Assertions passed");

    }

    @Test(dataProvider = "data")
    public void searchHotelTestWithDataProvider(String city, String hotel) {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity(city);
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
        softAssert.assertEquals(hotelsList.get(0), hotel, "Failed to find");
        softAssert.assertAll();

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

    @DataProvider
    public Object[][] data() throws IOException {
        return ExcelReader.readExcel("testData.xlsx");

    }
}
