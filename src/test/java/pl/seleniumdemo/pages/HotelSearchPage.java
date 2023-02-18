package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.tests.DriverFactory;

import java.util.ArrayList;
import java.util.List;


public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchCitySpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchCityInput;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(xpath = "//button[@type='submit' and text()=' Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[text()=' My Account ']")
    private List<WebElement> myAccountLink;

    @FindBy(xpath = "//a[text()='  Sign Up']")
    private List<WebElement> signUpLink;
    private WebDriver driver;


    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openSignUpForm() {
        clickBtn(myAccountLink);
        clickBtn(signUpLink);
    }

    public void setCity(String cityName) {
        searchCitySpan.click();
        searchCityInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void setDates(String checkIn, String checkOut) {
        checkinInput.sendKeys(checkIn);
        checkinInput.click();
        checkoutInput.sendKeys(checkOut);
        checkoutInput.click();
    }

    public void setTravellers(int adultsToAdd, int childToAdd) {
        travellersInput.click();
        addTraveler(adultPlusBtn, adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);
    }

    public void performSearch() {
        searchButton.click();
    }

    private void addTraveler(WebElement travelerBtn, int travelersToAdd) {
        for (int i = 0; i < travelersToAdd; i++) {
            travelerBtn.click();
        }
    }

    private void clickBtn(List<WebElement> element) {
        element.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
    }

}
