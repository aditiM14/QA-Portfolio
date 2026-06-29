package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    // Locators
    private By pageTitle = By.className("title");

    private By backpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");

    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");
    
    private By sortDropdown = By.className("product_sort_container");
    private By inventoryItems = By.className("inventory_item_name");
    private By inventoryPrices = By.className("inventory_item_price");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void addBackpack() {
        driver.findElement(backpackButton).click();
    }

    public void addBikeLight() {
        driver.findElement(bikeLightButton).click();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
    
    public int getCartBadgeCount() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));

        return Integer.parseInt(badge.getText());

    }
    
    public void selectSortOption(String option) {

        Select select = new Select(driver.findElement(sortDropdown));

        select.selectByVisibleText(option);
    }
    
    public List<String> getProductNames() {

        List<WebElement> elements = driver.findElements(inventoryItems);

        List<String> names = new ArrayList<>();

        for(WebElement e : elements) {

            names.add(e.getText());
        }

        return names;
    }
    
    public List<Double> getProductPrices() {

        List<WebElement> elements = driver.findElements(inventoryPrices);

        List<Double> prices = new ArrayList<>();

        for(WebElement e : elements) {

            prices.add(Double.parseDouble(e.getText().replace("$","")));
        }

        return prices;
    }

}