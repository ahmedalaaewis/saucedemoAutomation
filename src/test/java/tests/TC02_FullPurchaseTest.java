package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
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
    }

    @AfterClass
    public void tearDown() {
        //driverManager.closeAfterExecution();
    }
}
