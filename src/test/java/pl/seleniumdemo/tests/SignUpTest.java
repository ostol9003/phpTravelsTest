package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() throws IOException {
        ExtentTest test = extentReports.createTest("Sign up Test");
        // random email
        int randomNumber = (int) (Math.random() * 1000);
        String email = "marcin" + randomNumber;
        String lastName = "Ostolski";
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Marcin");
        signUpPage.setLastName(lastName);
        signUpPage.setPhone("700800900");
        signUpPage.setEmail(email + "@gmail.com");
        signUpPage.setPassword("123test");
        signUpPage.setConfirmPassword("123test");
        test.log(Status.PASS, "Completing the sheet done", SeleniumHelper.getScreenshot(driver));

        signUpPage.performSignUP();
        test.log(Status.PASS, "Performing sign up done", SeleniumHelper.getScreenshot(driver));
        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Marcin Ostolski");
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));

    }

    @Test
    public void signUpEmptySheetTest() throws IOException {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();
        ExtentTest test = extentReports.createTest("Sign up empty sheet Test");
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.performSignUP();
        test.log(Status.PASS, "Performing sign up done", SeleniumHelper.getScreenshot(driver));

        List<String> failsList = signUpPage.getFailsList();

        SoftAssert softAsert = new SoftAssert();

        List<String> fails = new ArrayList<>();
        fails.add("The Email field is required.");
        fails.add("The Password field is required.");
        fails.add("The Password field is required.");
        fails.add("The First name field is required.");
        fails.add("The Last Name field is required.");

        failsList.forEach(e -> softAsert.assertTrue(fails.contains(e)));

        softAsert.assertAll();
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));

    }

    @Test
    public void signUpEmailFailTest() throws IOException {
        ExtentTest test = extentReports.createTest("Sign up empty with email fail Test");

        String lastName = "Ostolski";
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();


        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName("Marcin");
        signUpPage.setLastName(lastName);
        signUpPage.setPhone("700800900");
        signUpPage.setEmail("marcin");
        signUpPage.setPassword("123test");
        signUpPage.setConfirmPassword("123test");
        test.log(Status.PASS, "Completing the sheet done", SeleniumHelper.getScreenshot(driver));
        signUpPage.performSignUP();
        test.log(Status.PASS, "Performing sign up done", SeleniumHelper.getScreenshot(driver));
        List<String> failsList = signUpPage.getFailsList();
        Assert.assertTrue(failsList.contains("The Email field must contain a valid email address."));
        test.log(Status.PASS, "Assertions passed", SeleniumHelper.getScreenshot(driver));
    }
}
