package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    // Locators
    private By backpack = By.id("item_4_title_link");
    private By bikeLight = By.id("item_0_title_link");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");
    private By checkoutButton = By.id("checkout");
    private By cartItems = By.className("cart_item");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isBackpackDisplayed() {
        return driver.findElement(backpack).isDisplayed();
    }

    public boolean isBikeLightDisplayed() {
        return driver.findElement(bikeLight).isDisplayed();
    }

    public void removeBackpack() {
        driver.findElement(removeBackpack).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
    
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

}