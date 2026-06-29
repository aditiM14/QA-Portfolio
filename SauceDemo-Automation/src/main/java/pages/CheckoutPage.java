package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");

    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By cancelButton = By.id("cancel");

    private By successMessage = By.className("complete-header");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public void fillCheckoutDetails(String first, String last, String zip) {

    	driver.findElement(firstName).sendKeys(first);

        driver.findElement(lastName).sendKeys(last);

        driver.findElement(postalCode).sendKeys(zip);
    }

    public void clickContinue() {

    	driver.findElement(continueButton).click();
    }

    public void clickFinish() {

    	driver.findElement(finishButton).click();
    }

    public void clickCancel() {

    	driver.findElement(cancelButton).click();
    }

    public String getSuccessMessage() {

    	WebElement element = driver.findElement(successMessage);

        return element.getText();
    }

    public String getErrorMessage() {

    	WebElement element = driver.findElement(errorMessage);

        return element.getText();
    }

}