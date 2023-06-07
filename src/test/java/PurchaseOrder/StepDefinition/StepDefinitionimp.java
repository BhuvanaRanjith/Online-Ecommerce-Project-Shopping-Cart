package PurchaseOrder.StepDefinition;

import Org.Ecommerce.Pageobject.*;
import Org.Ecommerce.TestComponents.BaseClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionimp extends BaseClass {
    public LandingPage landingPage;
    public ProductCatalog pc;
    public  confirmationPage cm;
    public PlaceOrder po;
    public CartPage cp;
    @Given("I landed on Ecommerce page")
    public void i_landed_on_Ecommerce_page() throws IOException {
        landingPage=launchApplication();
    }

    @Given("^Logged in with (.*) and (.*)$")
    public void loggged_in_with_username_and_password(String username, String password) {
        pc = landing.Loginpage(username,password);

    }
    @When("^I add the (.*) from cart$")
    public void I_add_the_product(String productName) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> p = pc.getProductList();
        pc.addProductToCart(productName);
         cp = pc.goTocartPage();
    }
    @And("^Checkout(.+)and submit the order$")
    public void Checkout_and_submit_the_order(String productName) throws InterruptedException {


        Boolean match = cp.verifyProduct(productName);
        Assert.assertFalse(match);
        PlaceOrder po = cp.gotoCheckout();
        po.checkOut("India");
         cm = po.placeOrder();
    }

    @Then("{string} message is displayed on confirmation page")
    public void message_is_displayed_on_confirmationpage(String string)
    {

        String expectedMessage = cm.setConfirmMessage();
        Assert.assertTrue(expectedMessage.equalsIgnoreCase(string));
        cm.clickOnSignOut();
        driver.close();
    }
    @Then ("{string} message is displayed")
    public void error_message_is_displayed(String str)
    {
        Assert.assertEquals(str,
                landing.getErrorMessage());
    }


}
