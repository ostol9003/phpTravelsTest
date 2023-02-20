package pl.seleniumdemo.pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(HotelSearchPage.class.getName());


    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openSignUpForm() {
        clickBtn(myAccountLink);
        clickBtn(signUpLink);
    }

    public void setCity(String cityName) {
        logger.info("Setting city: " + cityName);
        searchCitySpan.click();
        searchCityInput.sendKeys(cityName);
        String xpath = String.format("//span[@class='select2-match' and text()='%s']", cityName);
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Setting city done");

    }

    public void setDates(String checkIn, String checkOut) {
        logger.info("Setting dates");
        checkinInput.sendKeys(checkIn);
        checkinInput.click();
        checkoutInput.sendKeys(checkOut);
        checkoutInput.click();
        logger.info("Setting dates done");
    }

    public void setTravellers(int adultsToAdd, int childToAdd) {
        logger.info("Adding addults: " + adultsToAdd + " and kids: " + childToAdd);
        travellersInput.click();
        addTraveler(adultPlusBtn, adultsToAdd);
        addTraveler(childPlusBtn, childToAdd);
        logger.info("Adding travellers done");
    }

    public void performSearch() {
        logger.info("Performing search");
        searchButton.click();
        logger.info("Performing search done");
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
