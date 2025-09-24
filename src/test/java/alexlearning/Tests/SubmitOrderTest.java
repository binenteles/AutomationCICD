package alexlearning.Tests;

import alexlearning.TestComponents.BaseTest;
import alexlearning.pageobjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    @Test(dataProvider = "getData", groups = "PurchaseOrder")
    public void submitOrder(HashMap<String, String> input) {
        String country = "romania";
        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
        productCatalog.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalog.goToCartPage();
        Assert.assertTrue(cartPage.findProductInCart(input.get("productName")));
        CheckoutPage checkoutPage = cartPage.clickOnCheckout();
        checkoutPage.selectCountry(country);
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    //to verify  if ZARA COAT 3 is displaying in orders pages
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApplication("alex_jely@yahoo.com", "LearnSelenium1");
        OrderPage ordersPage = productCatalog.goToOrderPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
    }


    //Extent Reports

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJasonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\Data\\PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("email", "alex_jely@yahoo.com");
//        map.put("password", "LearnSelenium1");
//        map.put("productName", "ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("email", "roberrt45@element.com");
//        map1.put("password", "Learn40Nothing1");
//        map1.put("productName", "ADIDAS ORIGINAL");

