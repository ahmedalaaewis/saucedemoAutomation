package pages;

import UIElements.Button;
import UIElements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.util.concurrent.TimeUnit;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterCheckoutInformation(String firstName, String lastName, String zipCode) {
        // Enter the checkout information
        TextBox firstNameField = new TextBox(driver, By.id("first-name"));
        TextBox lastNameField = new TextBox(driver, By.id("last-name"));
        TextBox zipCodeField = new TextBox(driver, By.id("postal-code"));

        firstNameField.clearAndType(firstName);
        lastNameField.clearAndType(lastName);
        zipCodeField.clearAndType(zipCode);

        // Click on the Continue button to proceed to the next step
        By continueButtonLocator = By.id("continue");
        Button continueButton = new Button(driver, continueButtonLocator);
        continueButton.click();
    }


    public void ClickFinish() {
        // Click on the Finish button to complete the purchase
        By finishButtonLocator = By.xpath("//*[@id='finish']");
        Button finishButton = new Button(driver, finishButtonLocator);
        finishButton.click();
    }

    public void printOrderSummary() {
        // Assuming there are specific elements representing sections on the summary page
        printSummary("Payment Information");
        printSummary("Shipping Information");
        printSummary("Price Total");
    }

    private void printSummary(String section) {
        By sectionLabelLocator = By.xpath("//div[contains(@class, 'summary_info_label') and text()='" + section + "']");
        TextBox sectionLabel = new TextBox(driver, sectionLabelLocator);

        // Get text from the label element
        String label = sectionLabel.getText();

        // Print or log the order details
        System.out.print(label + ": ");

        // Check the section and print the corresponding value based on its structure
        if (section.equals("Price Total")) {
            // For "Price Total," look for specific elements
            printValue("//div[contains(@class, 'summary_subtotal_label')]");
            printValue("//div[contains(@class, 'summary_tax_label')]");
            printValue("//div[contains(@class, 'summary_total_label')]");
        } else {
            // For other sections, look for the general value label
            printValue("//div[@class='summary_value_label' and preceding-sibling::div[@class='summary_info_label' and text()='" + section + "']]");
        }
    }

    private void printValue(String valueLocatorXpath) {
        By valueLocator = By.xpath(valueLocatorXpath);
        TextBox sectionValue = new TextBox(driver, valueLocator);
        String value = sectionValue.getText();
        System.out.println(value);
    }

    public boolean isCheckoutCompleted() {
        // Check if the completion message is displayed
        By completionMessageLocator = By.xpath("//h2[text()='Thank you for your order!']");
        return isElementPresent(completionMessageLocator);
    }

}
