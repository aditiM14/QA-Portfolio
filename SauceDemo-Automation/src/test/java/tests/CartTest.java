package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class CartTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.getDriver();

        driver.get(ConfigReader.getProperty("url"));

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));

    }

    @Test
    public void addOneProductTest() {

        inventoryPage.addBackpack();

        Assert.assertEquals(inventoryPage.getCartBadgeCount(),1);

        inventoryPage.openCart();

        Assert.assertTrue(cartPage.isBackpackDisplayed());
    }

    @Test
    public void addTwoProductsTest() {

        inventoryPage.addBackpack();
        inventoryPage.addBikeLight();

        Assert.assertEquals(inventoryPage.getCartBadgeCount(),2);

        inventoryPage.openCart();

        Assert.assertTrue(cartPage.isBackpackDisplayed());

        Assert.assertTrue(cartPage.isBikeLightDisplayed());
    }

    @Test
    public void removeProductTest() {

        inventoryPage.addBackpack();

        inventoryPage.openCart();

        Assert.assertEquals(cartPage.getCartItemCount(), 1);

        cartPage.removeBackpack();

        Assert.assertEquals(cartPage.getCartItemCount(), 0);
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }

}