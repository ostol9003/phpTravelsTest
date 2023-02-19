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
        String lastName = "Ostolski";

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Marcin")
                .setLastName(lastName)
                .setPhone("700800900")
                .setEmail("marcin" + randomNumber + "@gmail.com")
                .setPassword("123test")
                .setConfirmPassword("123test")
                .performSignUP();


        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Marcin Ostolski");

    }


    @Test
    public void signUpEmptySheetTest() {
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm();
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

        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Marcin")
                .setLastName("Ostolski")
                .setPhone("700800900")
                .setEmail("marcin")
                .setPassword("123test")
                .setConfirmPassword("123test");

        signUpPage.performSignUP();

        Assert.assertTrue(signUpPage.getFailsList().contains("The Email field must contain a valid email address."));

    }
}
