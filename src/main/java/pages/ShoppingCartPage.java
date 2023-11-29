package pages;

import UIElements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        // Check if the product is in the cart based on its name
        By productLocator = By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']");
        return isElementPresent(productLocator);
    }

    public void checkout() {
        // Click on the Checkout button to proceed
        By checkoutButtonLocator = By.id("checkout");
        Button checkoutButton = new Button(driver, checkoutButtonLocator);
        checkoutButton.click();
    }

    public void removeProduct(String productName) {
        // Construct the locator for the "Remove" button of the specified product
        By removeButtonLocator = By.xpath("//div[@class='inventory_item_name' and text()='" + productName + "']/following-sibling::div/button");

        // Click the "Remove" button for the specified product
        Button removeButton = new Button(driver, removeButtonLocator);
        removeButton.click();
    }

    public void removeAllProducts() {
        // Find all "Remove" buttons in the cart
        By removeButtonsLocator = By.xpath("//button[contains(@id, 'remove')]");
        List<WebElement> removeButtons = driver.findElements(removeButtonsLocator);

        // Click each "Remove" button to remove the corresponding product
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }

    public void clickContinueShopping() {
        By continueShoppingButtonLocator = By.xpath("//*[@id='continue-shopping']");
        Button continueShoppingButton = new Button(driver, continueShoppingButtonLocator);
        continueShoppingButton.click();
    }

    public boolean isCartEmpty() {
        // Check if the cart is empty based on the absence of elements representing items in the cart
        By cartItemLocator = By.xpath("//div[@class='cart_item']");
        return !isElementPresent(cartItemLocator);
    }

}
