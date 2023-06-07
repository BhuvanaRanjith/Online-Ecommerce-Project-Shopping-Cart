package productTest;

import Org.Ecommerce.Pageobject.*;
import Org.Ecommerce.TestComponents.BaseClass;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class StandAloneTest extends BaseClass {
    public String Product = "ZARA COAT 3";


    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrderTest(HashMap<String,String> input) throws IOException, InterruptedException {

       landing.goTo();
        ProductCatalog pc = landing.Loginpage(input.get("email"),input.get("password"));

        List<WebElement> p = pc.getProductList();
        pc.addProductToCart(input.get("Product"));
        CartPage cp = pc.goTocartPage();
        //
        Boolean match = cp.verifyProduct(input.get("Product"));
        Assert.assertTrue(match);
        PlaceOrder po = cp.gotoCheckout();

        po.checkOut("India");
        confirmationPage cm = po.placeOrder();

        String expectedMessage = cm.setConfirmMessage();
        Assert.assertTrue(expectedMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        cm.clickOnSignOut();


    }

    @Test(dependsOnMethods = {"submitOrderTest"})

    public void orderHistoryTest() throws InterruptedException {

        ProductCatalog pc = landing.Loginpage("practicedemo17@gmail.com", "Welcome@123");

        OrderPage orderPage = pc.goToOrderPage();

    Assert.assertTrue(orderPage.verifyOrder(Product));

    }

    @DataProvider
    public Object[][] getData() throws IOException {



    List<HashMap<String,String>> data=getJsonDataMap(System.getProperty("user.dir") + "\\src\\test\\java\\Data\\Purchaseorder.json");
       return new Object[][] {{data.get(0)}};
    }
//Get the Data using DataProvider
    //@DataProvider
    //public Object[][] getData()
    //{
    //    return new Object[][] {{"practicedemo17@gmail.com","Welcome@123","ZARA COAT 3"},{"ts7837212@gmail.com","Welcome@123","ADIDAS ORIGINAL"}};
    //}
//HapMap Code
    //        HashMap<String,String> map=new HashMap<String,String>();
//        map.put("email","practicedemo17@gmail.com");
//        map.put("password","Welcome@123");
//        map.put("Product","ZARA COAT 3");
//
//
//        HashMap<String,String> map1=new HashMap<String,String>();
//        map1.put("email","ts7837212@gmail.com");
//        map1.put("password","Welcome@123");
//        map1.put("Product","ADIDAS ORIGINAL");

}

