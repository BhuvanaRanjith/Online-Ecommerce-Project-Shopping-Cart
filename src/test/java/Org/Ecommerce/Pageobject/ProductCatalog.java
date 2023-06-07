package Org.Ecommerce.Pageobject;

import Org.Ecommerce.AbstractComponent.AbstactComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCatalog extends AbstactComponent {

    WebDriver driver;
    public ProductCatalog(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> allProdcut;
    By products= By.cssSelector(".mb-3");
    By addTocart=By.cssSelector(".card-body button:last-of-type");
    By toastMessage=By.cssSelector("#toast-container");

    public List<WebElement> getProductList()

    {
        waitelemntToappear(products);
        return allProdcut;
    }
    public WebElement getProductBYName(String Product)
    {

        WebElement itenNeeded=getProductList().stream().filter(p->p.findElement(By.cssSelector("b")).getText().equals(Product)).findFirst().orElse(null);
        return itenNeeded;

    }
    public void addProductToCart(String Product)
    {
        WebElement itenNeeded=getProductBYName(Product);
        itenNeeded.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        waitelemntToappear(toastMessage);
    }


}

