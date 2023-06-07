package Org.Ecommerce.Pageobject;

import Org.Ecommerce.AbstractComponent.AbstactComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstactComponent {
    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        //initialization
        //useing the initmethod we will use the webelement .
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="userEmail")
    WebElement useremail;

    @FindBy(id="userPassword")
    WebElement userpassword;

    @FindBy(id="login")
    WebElement submit;

@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
WebElement errorMessage;

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");


    }

    public ProductCatalog Loginpage(String email, String password)
    {

        useremail.sendKeys(email);
        userpassword.sendKeys(password);
        submit.click();
        useremail.clear();
        userpassword.clear();
        ProductCatalog pc=new ProductCatalog(driver);
        return pc;
    }

      public String getErrorMessage()
    {
        waitforWebElementelemntToappear(errorMessage);
        return errorMessage.getText();



    }




}
