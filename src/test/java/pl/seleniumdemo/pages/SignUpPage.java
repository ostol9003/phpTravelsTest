package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<String> getFailsList() {
        return failsList.stream()
                .map(WebElement::getText)
                .toList();
    }


    public SignUpPage setFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public SignUpPage setLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public SignUpPage setPhone(String phone) {
        phoneInput.sendKeys(phone);
        return this;
    }

    public SignUpPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public SignUpPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public SignUpPage setConfirmPassword(String confirmPassword) {
        confirmPasswordInput.sendKeys(confirmPassword);
        return this;
    }


    public LoggedUserPage performSignUP() {
        signUpButton.click();
        return new LoggedUserPage(driver);
    }
}