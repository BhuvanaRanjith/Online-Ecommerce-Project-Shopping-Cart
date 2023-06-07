package productTest;

import Org.Ecommerce.Pageobject.CartPage;
import Org.Ecommerce.Pageobject.LandingPage;
import Org.Ecommerce.Pageobject.ProductCatalog;
import Org.Ecommerce.TestComponents.BaseClass;
import Org.Ecommerce.TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
@Test(groups = {"ErrorHandling"})
public class ErrorValidationTest extends BaseClass {

    ProductCatalog pc;
    LandingPage landing;
    @Test(groups = {"Error Handling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {
      //  String Product = "ZARA COAT 3";
        landing=new LandingPage(driver);
        landing.Loginpage("practicedemo17@gmail.com","abc");

        Assert.assertEquals("Incorrect email or password.",
                landing.getErrorMessage());



    }
@Test
    public void productErrorValidation() throws IOException, InterruptedException {

        String Product = "ZARA COAT 3";

       landing.Loginpage("ts7837212@gmail.com","Welcome@123");
       pc = new ProductCatalog(driver);
        List<WebElement> p = pc.getProductList();
        pc.addProductToCart(Product);
        CartPage cp = pc.goTocartPage();
        Boolean match = cp.verifyProduct("Zara code33");
        Assert.assertFalse(match);


    }
}