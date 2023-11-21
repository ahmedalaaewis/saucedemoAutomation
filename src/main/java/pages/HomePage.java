package pages;

import UIElements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(String productName) {
        // Locate the product name element
        By addToCartButtonLocator = By.xpath("//button[contains(@id, 'add-to-cart-" + productName.toLowerCase().replace(" ", "-") + "')]");

        // Click the "Add to Cart" button
        Button addToCartButton = new Button(driver, addToCartButtonLocator);
        addToCartButton.click();
    }

    public void removeFromCart(String productName){
        By removeFromCartLocator = By.xpath("//button[contains(@id, 'remove-" + productName.toLowerCase().replace(" ", "-") + "')]");

        // Click the "Add to Cart" button
        Button removeFromCartButton = new Button(driver, removeFromCartLocator);
        removeFromCartButton.click();
    }

    public void clickOnShoppingCart()
    {
        By shoppingCartLocator = By.cssSelector("#shopping_cart_container > a");
        Button shoppingCartButton = new Button(driver, shoppingCartLocator);
        shoppingCartButton.click();
    }
}
