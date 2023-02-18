package pl.seleniumdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class HotelSearchPage {

    @FindBy(xpath = "//span[text()='Search by Hotel or City Name']")
    private WebElement searchCitySpan;

    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement searchCityInput;

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement hotelMatch;

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

public HotelSearchPage(WebDriver driver){
    PageFactory.initElements(driver,this);
}

public void setCity(String cityName){
    searchCitySpan.click();
    searchCityInput.sendKeys(cityName);
    hotelMatch.click();
}

public void setDates (String checkIn, String checkOut){
    checkinInput.sendKeys(checkIn);
    checkinInput.click();
    checkoutInput.sendKeys(checkOut);
    checkoutInput.click();
}

public void setTravellers (){
    travellersInput.click();
    adultPlusBtn.click();
    childPlusBtn.click();
}

public void performSearch(){
    searchButton.click();
}


}
