package Org.Ecommerce.Pageobject;

import Org.Ecommerce.AbstractComponent.AbstactComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstactComponent {
    WebDriver driver;
    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> orderPrdouct;
    public OrderPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public Boolean verifyOrder(String Products)
    {
        //List<WebElement> cartProduct=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        Boolean match=orderPrdouct.stream().anyMatch(c->c.getText().equalsIgnoreCase(Products));

        return match;

    }
}
