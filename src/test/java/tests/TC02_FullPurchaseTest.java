package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ShoppingCartPage;
import pages.CheckoutPage;
import resources.DriverManager;
import resources.ScreenshotUtility;

public class TC02_FullPurchaseTest {
    private DriverManager driverManager;
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    @BeforeClass
    public void setUp() {
        driverManager = new DriverManager();
        driverManager.navigateTo("https://www.saucedemo.com/");
        driver = driverManager.getDriver();

        extentReports = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent-report.html");
        extentReports.attachReporter(spark);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        // Capture screenshot after each method invocation and attach to the ExtentReport
        String screenshotPath = ScreenshotUtility.captureScreenshot(driver, extentTest, result.getMethod().getMethodName());
        extentTest.log(Status.INFO, "Test step: " + result.getMethod().getMethodName());
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, result.getThrowable());
        }
    }

    @Test
    public void testFullPurchase() {
        // Start ExtentTest for the current test case
        extentTest = extentReports.createTest("testFullPurchase");

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
        // Flush the ExtentReports to write the results to the output
        extentReports.flush();
        driverManager.closeAfterExecution();
    }
}
