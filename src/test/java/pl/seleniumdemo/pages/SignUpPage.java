package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.util.List;

public class SignUpPage {

    @FindBy(name = "firstname")
    private WebElement firstNameInput;

    @FindBy(name = "lastname")
    private WebElement lastNameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "confirmpassword")
    private WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> failsList;

    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HotelSearchPage.class.getName());

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<String> getFailsList() {
        SeleniumHelper.waitForNotEmptyList(driver, By.xpath("//div[@class='alert alert-danger']//p"));
        return failsList.stream()
                .map(WebElement::getText)
                .toList();
    }


    public void setFirstName(String firstName) {
        logger.info("Setting first name " + firstName);
        firstNameInput.sendKeys(firstName);
        logger.info("Setting first name done");
    }

    public void setLastName(String lastName) {
        logger.info("Setting last name " + lastName);
        lastNameInput.sendKeys(lastName);
        logger.info("Setting last name done");
    }

    public void setPhone(String phone) {
        logger.info("Setting phone " + phone);
        phoneInput.sendKeys(phone);
        logger.info("Setting phone done");
    }

    public void setEmail(String email) {
        logger.info("Setting email " + email);
        emailInput.sendKeys(email);
        logger.info("Setting email done");
    }

    public void setPassword(String password) {
        logger.info("Setting password " + password);
        passwordInput.sendKeys(password);
        logger.info("Setting password done");
    }

    public void setConfirmPassword(String password) {
        logger.info("Setting confirm password " + password);
        confirmPasswordInput.sendKeys(password);
        logger.info("Setting confirm password done");
    }

    public void performSignUP() {
        logger.info("Performing signing up");
        signUpButton.click();
        logger.info("Performing signing up done");
    }

}
