package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.ArrayList;
import java.util.List;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        // random email
        int randomNumber = (int) (Math.random() * 1000);
        String email = "marcin" + randomNumber;
        String lastName = "Ostolski";
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.settAtribute(signUpPage.getFirstNameInput(), "Marcin");
        signUpPage.settAtribute(signUpPage.getLastNameInput(), lastName);
        signUpPage.settAtribute(signUpPage.getPhoneInput(), "700800900");
        signUpPage.settAtribute(signUpPage.getEmailInput(), email + "@gmail.com");
        signUpPage.settAtribute(signUpPage.getPasswordInput(), "123test");
        signUpPage.settAtribute(signUpPage.getConfirmPasswordInput(), "123test");

        signUpPage.performSignUP();

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Marcin Ostolski");

    }

    @Test
    public void signUpEmptySheetTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.performSignUP();

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

    }

    @Test
    public void signUpEmailFailTest() {
        int randomNumber = (int) (Math.random() * 1000);
        String email = "marcin" + randomNumber;
        String lastName = "Ostolski";
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.settAtribute(signUpPage.getFirstNameInput(), "Marcin");
        signUpPage.settAtribute(signUpPage.getLastNameInput(), lastName);
        signUpPage.settAtribute(signUpPage.getPhoneInput(), "700800900");
        signUpPage.settAtribute(signUpPage.getEmailInput(), email);
        signUpPage.settAtribute(signUpPage.getPasswordInput(), "123test");
        signUpPage.settAtribute(signUpPage.getConfirmPasswordInput(), "123test");

        signUpPage.performSignUP();

        List<String> failsList = signUpPage.getFailsList();
        Assert.assertTrue(failsList.contains("The Email field must contain a valid email address."));

    }
}
