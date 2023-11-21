package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import resources.DriverManager;

public class TC01_InvalidLogin {
    private DriverManager driverManager;

    @BeforeClass
    public void setUp() {
        driverManager = new DriverManager();
        driverManager.navigateTo("https://www.saucedemo.com/");
    }

    @Test
    public void LoginWithInvalidCredentials() {
        WebDriver driver = driverManager.getDriver();
        LoginPage loginPage = new LoginPage(driver);

        // Use the login method to log in
        loginPage.login("invaliduser", "invalidpassword");
        // Perform assertions for an unsuccessful login
        Assert.assertTrue(loginPage.isLoginErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Epic sadface: Username and password do not match any user in this service");

    }

    @AfterClass
    public void tearDown() {
        driverManager.closeAfterExecution();
        }
    }