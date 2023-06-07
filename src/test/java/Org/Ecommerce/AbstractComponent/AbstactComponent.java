package Org.Ecommerce.AbstractComponent;

import Org.Ecommerce.Pageobject.CartPage;
import Org.Ecommerce.Pageobject.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstactComponent  {
    WebDriver driver;
    public AbstactComponent(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="[routerlink*='cart']")
    WebElement cart;

   @FindBy(css="[routerlink*='myorders']")
   WebElement order;

    public void waitelemntToappear(By findBY)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
    }

    public void waitforWebElementelemntToappear(WebElement findBY)  {

       WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
       wait.until(ExpectedConditions.visibilityOf(findBY));
    }

    public CartPage goTocartPage()
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(cart));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",cart);
        CartPage cp=new CartPage(driver);
        return cp;

    }

    public OrderPage goToOrderPage()
    {

        order.click();
       OrderPage op=new OrderPage(driver);
        return op;


    }
}
