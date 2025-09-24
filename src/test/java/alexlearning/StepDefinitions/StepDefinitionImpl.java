package alexlearning.StepDefinitions;

import alexlearning.TestComponents.BaseTest;
import alexlearning.pageobjects.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {
    public LandingPage landingPage;
    public ProductCatalog productCatalog;
    public CartPage cartPage;
    public ConfirmationPage confirmationPage;
    String country = "romania";

    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Login with the username (.+) and password (.+)$")
    public void login_with_username_and_password(String username, String password) {
        productCatalog = landingPage.loginApplication(username, password);
    }

    @When("^I add (.+) to Cart$")
    public void i_add_product_to_cart(String productName) {
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_order(String productName) {
        cartPage = productCatalog.goToCartPage();
        Assert.assertTrue(cartPage.findProductInCart(productName));
        CheckoutPage checkoutPage = cartPage.clickOnCheckout();
        checkoutPage.selectCountry(country);
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void message_displayed_Confirmation_Page(String string) {
        String confirmMessage = confirmationPage.getMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed on Login Page")
    public void error_message_displayed_Login_Page(String info) {
        Assert.assertEquals(landingPage.getErrorMessage(), info);
        driver.close();
    }
}
