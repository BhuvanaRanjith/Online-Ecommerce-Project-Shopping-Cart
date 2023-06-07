package Org.Ecommerce.Pageobject;

import Org.Ecommerce.AbstractComponent.AbstactComponent;
import Org.Ecommerce.Pageobject.confirmationPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrder extends AbstactComponent {
    WebDriver driver;

    public PlaceOrder(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);


    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement selectCountry;

    @FindBy(xpath = "//a[normalize-space()='Place Order']")
    WebElement submit;


    public void checkOut(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        selectCountry.click();

    }

    public confirmationPage placeOrder() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", submit);

        return new confirmationPage(driver);


    }

}
