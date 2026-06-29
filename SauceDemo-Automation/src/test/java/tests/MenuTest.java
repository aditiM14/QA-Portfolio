package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.MenuPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class MenuTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private MenuPage menuPage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.getDriver();

        driver.get(ConfigReader.getProperty("url"));

        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);

        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));

    }

    @Test
    public void logoutTest() {

        menuPage.openMenu();

        menuPage.clickLogout();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }

    @Test
    public void aboutPageTest() {

        menuPage.openMenu();

        menuPage.clickAbout();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucelabs.com"));
    }

    @Test
    public void resetAppStateTest() {

        menuPage.openMenu();

        menuPage.clickResetAppState();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }

}