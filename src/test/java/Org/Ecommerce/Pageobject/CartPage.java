package Org.Ecommerce.Pageobject;

import Org.Ecommerce.Pageobject.PlaceOrder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//div[@class='cartSection']/h3")
    List<WebElement> cartProduct;



    @FindBy(xpath = "//li[@class='totalRow']/button")
    WebElement checkout;
    public Boolean verifyProduct(String Product)
    {
        // List<WebElement> cartProduct=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        Boolean match=cartProduct.stream().anyMatch(c->c.getText().equalsIgnoreCase(Product));
        return match;

    }
    public PlaceOrder gotoCheckout() throws InterruptedException {
        checkout.click();
        Thread.sleep(2000);
        PlaceOrder po=new PlaceOrder(driver);
        return po;

    }


}
