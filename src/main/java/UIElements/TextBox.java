package UIElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBox {
    private WebDriver driver;
    private By textBox;

    public TextBox(WebDriver driver, By locator) {
        this.driver = driver;
        this.textBox = locator;
    }

    public void clearAndType(String text) {
        WebElement textBoxElement = driver.findElement(textBox);
        textBoxElement.clear();
        textBoxElement.sendKeys(text);
    }

    public String getText() {
        WebElement textBoxElement = driver.findElement(textBox);
        return textBoxElement.getText();
    }

    // Add more methods as needed.
}
