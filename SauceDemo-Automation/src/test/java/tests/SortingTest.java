package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.InventoryPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class SortingTest {

    private WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.getDriver();

        driver.get(ConfigReader.getProperty("url"));

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        loginPage.login(ConfigReader.getProperty("username"),ConfigReader.getProperty("password"));

    }

    @Test
    public void sortByNameAToZTest() {

        inventoryPage.selectSortOption("Name (A to Z)");

        List<String> actual = inventoryPage.getProductNames();

        List<String> expected = new ArrayList<>(actual);

        Collections.sort(expected);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void sortByNameZToATest() {

        inventoryPage.selectSortOption("Name (Z to A)");

        List<String> actual = inventoryPage.getProductNames();

        List<String> expected = new ArrayList<>(actual);

        expected.sort(Collections.reverseOrder());

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void sortPriceLowToHighTest() {

        inventoryPage.selectSortOption("Price (low to high)");

        List<Double> actual = inventoryPage.getProductPrices();

        List<Double> expected = new ArrayList<>(actual);

        Collections.sort(expected);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void sortPriceHighToLowTest() {

        inventoryPage.selectSortOption("Price (high to low)");

        List<Double> actual = inventoryPage.getProductPrices();

        List<Double> expected = new ArrayList<>(actual);

        expected.sort(Collections.reverseOrder());

        Assert.assertEquals(actual, expected);
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }

}