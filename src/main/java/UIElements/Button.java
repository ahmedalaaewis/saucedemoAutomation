package UIElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Button {
    private WebDriver driver;
    private By button;

    public Button(WebDriver driver, By locator) {
        this.driver = driver;
        this.button = locator;
    }

    public void click() {
        driver.findElement(button).click();
    }

    public String getText() {
        return driver.findElement(button).getText();
    }

    public boolean isEnabled() {
        return driver.findElement(button).isEnabled();
    }

    // Add more methods as needed.
}
