package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ShoppingCartPage;
import pages.CheckoutPage;
import resources.DriverManager;

public class TC02_FullPurchaseTest {
    private DriverManager driverManager;

    @BeforeClass
    public void setUp() {
        driverManager = new DriverManager();
        driverManager.navigateTo("https://www.saucedemo.com/");
    }

    @Test
    public void testFullPurchase() {

            WebDriver driver = driverManager.getDriver();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");

            HomePage homepage = new HomePage(driver);
            homepage.addToCart("Sauce Labs Backpack");
            homepage.addToCart("Sauce Labs Bolt T-Shirt");
            homepage.removeFromCart("Sauce Labs Backpack");
            homepage.clickOnShoppingCart();

            ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
            Assert.assertFalse(shoppingCartPage.isProductInCart("Sauce Labs Backpack"));
            shoppingCartPage.removeAllProducts();
            Assert.assertTrue(shoppingCartPage.isCartEmpty(), "Cart is not empty");

            shoppingCartPage.clickContinueShopping();
            homepage.addToCart("Sauce Labs Bike Light");
            homepage.clickOnShoppingCart();
            shoppingCartPage.checkout();

            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.enterCheckoutInformation("John", "Doe", "12345");
            checkoutPage.printOrderSummary();
            checkoutPage.ClickFinish();
            Assert.assertTrue(checkoutPage.isCheckoutCompleted(), "Order completion message not displayed");

    }

    @AfterClass
    public void tearDown() {
        driverManager.closeAfterExecution();
    }
}
