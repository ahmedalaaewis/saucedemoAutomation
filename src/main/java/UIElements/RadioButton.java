package UIElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RadioButton {
    private WebDriver driver;
    private By radioButton;

    public RadioButton(WebDriver driver, By locator) {
        this.driver = driver;
        this.radioButton = locator;
    }

    public void select() {
        WebElement radioButtonElement = driver.findElement(radioButton);
        if (!radioButtonElement.isSelected()) {
            radioButtonElement.click();
        }
    }

    public boolean isSelected() {
        WebElement radioButtonElement = driver.findElement(radioButton);
        return radioButtonElement.isSelected();
    }

    // Add more methods as needed.
}
