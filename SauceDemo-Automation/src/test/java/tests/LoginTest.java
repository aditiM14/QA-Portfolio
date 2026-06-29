package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("url"));

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void validLoginTest() {

        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));

        Assert.assertEquals(inventoryPage.getPageTitle(),"Products","Login failed!");
    }
    
    @Test
    public void invalidPasswordTest() {

        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("invalidPassword"));

        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
    }
    
    @Test
    public void lockedUserTest() {

        loginPage.login(ConfigReader.getProperty("lockedUser"),ConfigReader.getProperty("password"));

        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Sorry, this user has been locked out.");
    }
    
    @Test
    public void emptyUsernameTest() {

        loginPage.login("",ConfigReader.getProperty("password"));

        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username is required");
    }
    
    @Test
    public void emptyPasswordTest() {

        loginPage.login(ConfigReader.getProperty("username"),"");

        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Password is required");
    }
    
    @Test
    public void emptyCredentialsTest() {

        loginPage.login("", "");

        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username is required");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}