package stepDefinitions;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    WebDriver driver;
    

    @Given("I open Chrome and navigate to {string}")
    public void I_open_chrome_and_navigate_to(String url) {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @When("I enter {string} and {string}")
    public void i_enter_and(String user, String pass) {
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(pass);
    }

    @And("I click login")
    public void i_click_login() {
        driver.findElement(By.id("login-button")).click();
    }
    @And("I add the {string} to the cart")
    public void i_add_item_to_cart(String itemName) {
        // 1. Set up the Fluent Wait
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        // 2. Wait for the button and click it
        // We are looking for the 'Add to cart' button specifically for the Backpack
        WebElement addToCartBtn = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            }
        });

        addToCartBtn.click();
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expectedCount) throws InterruptedException {
        String actualCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        Assert.assertEquals(actualCount, expectedCount);
        
        // Keeping your 20-second wait so you can see the '1' on the cart!
        Thread.sleep(20000); 
        driver.quit();
    }

   
}