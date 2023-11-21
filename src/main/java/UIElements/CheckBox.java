package UIElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBox {
    private WebDriver driver;
    private By checkBox;

    public CheckBox(WebDriver driver, By locator) {
        this.driver = driver;
        this.checkBox = locator;
    }

    public void check() {
        WebElement checkBoxElement = driver.findElement(checkBox);
        if (!checkBoxElement.isSelected()) {
            checkBoxElement.click();
        }
    }

    public void uncheck() {
        WebElement checkBoxElement = driver.findElement(checkBox);
        if (checkBoxElement.isSelected()) {
            checkBoxElement.click();
        }
    }

    public boolean isChecked() {
        WebElement checkBoxElement = driver.findElement(checkBox);
        return checkBoxElement.isSelected();
    }

    // Add more methods as needed.
}
