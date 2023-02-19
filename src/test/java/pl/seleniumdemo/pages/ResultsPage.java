package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ResultsPage {

    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b")
    private List<WebElement> hotelsList;

    @FindBy(xpath = "//div[@class='itemscontainer']//h2")
    private WebElement resultHeading;

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }



    public List<String> getHotelNames() {
        return hotelsList.stream()
                .map(el -> el.getAttribute("textContent"))
                .toList();
    }

    public String getHeadingText() {
        return resultHeading.getText();
    }

    public WebElement getResultHeading() {
        return resultHeading;
    }
}
