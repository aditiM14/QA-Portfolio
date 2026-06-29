package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class CheckoutTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.getDriver();

        driver.get(ConfigReader.getProperty("url"));

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));

        inventoryPage.addBackpack();
        
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1);
        
        inventoryPage.openCart();

        cartPage.clickCheckout();
    }

    @Test
    public void successfulCheckoutTest() {

        checkoutPage.fillCheckoutDetails("Harry","Potter","64224");

        checkoutPage.clickContinue();

        checkoutPage.clickFinish();

        Assert.assertEquals(checkoutPage.getSuccessMessage(),"Thank you for your order!");
    }

    @Test
    public void missingFirstNameTest() {

        checkoutPage.fillCheckoutDetails("","Potter","64224");

        checkoutPage.clickContinue();

        Assert.assertEquals(checkoutPage.getErrorMessage(),"Error: First Name is required");
    }

    @Test
    public void missingLastNameTest() {

        checkoutPage.fillCheckoutDetails("Harry","","64224");

        checkoutPage.clickContinue();

        Assert.assertEquals(checkoutPage.getErrorMessage(),"Error: Last Name is required");
    }

    @Test
    public void missingPostalCodeTest() {

        checkoutPage.fillCheckoutDetails("Harry","Potter","");

        checkoutPage.clickContinue();

        Assert.assertEquals(checkoutPage.getErrorMessage(),"Error: Postal Code is required");
    }

    @Test
    public void cancelCheckoutTest() {

        checkoutPage.clickCancel();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }

}