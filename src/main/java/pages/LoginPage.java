package pages;

import UIElements.Button;
import UIElements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private TextBox usernameField = new TextBox(driver, By.id("user-name"));
    private TextBox passwordField = new TextBox(driver, By.id("password"));
    private Button loginButton = new Button(driver, By.id("login-button"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        // Use TextBox and Button UI element classes to interact with login page elements
        usernameField.clearAndType(username);
        passwordField.clearAndType(password);

        loginButton.click();
    }

    public boolean isLoginErrorMessageDisplayed() {
        // Check if the login error message is displayed
        return driver.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error > h3")).isDisplayed();
    }

    public String getLoginErrorMessage() {
        // Get the text of the login error message
        return driver.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error > h3")).getText();
    }
}
