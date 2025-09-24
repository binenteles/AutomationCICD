package alexlearning.Tests;

import alexlearning.TestComponents.BaseTest;
import alexlearning.TestComponents.Retry;
import alexlearning.pageobjects.CartPage;
import alexlearning.pageobjects.CheckoutPage;
import alexlearning.pageobjects.ConfirmationPage;
import alexlearning.pageobjects.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidations extends BaseTest {
    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() {
        landingPage.loginApplication("alex_jely@yahoo1.com", "LearnS6elenium1");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

    @Test
    public void productErrorValidation() {
        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApplication("alex_jely@yahoo.com", "LearnSelenium1");
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();
        Assert.assertFalse(cartPage.findProductInCart("ZARA COAT 33"));

    }
}
