package Org.Ecommerce.Pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class confirmationPage {
    WebDriver driver;
    public confirmationPage(WebDriver driver)
    {
       // super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".hero-primary")
    WebElement confirmMessage;
    @FindBy(xpath = "//label[normalize-space()='Orders History Page']")
    WebElement orderClick;

    @FindBy(xpath="(//button[@class='btn btn-custom'])[4]")
    WebElement SignOut;

    public String setConfirmMessage()
    {

        return confirmMessage.getText();

    }
    public void clickOnSignOut()
    {
        SignOut.click();
    }
}



