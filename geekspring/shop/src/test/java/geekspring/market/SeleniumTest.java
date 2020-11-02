package geekspring.market;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeSuite
    public void init() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void addProductToCart() {
        driver.get("http://localhost:8181/shop");
        WebElement btnAddInCart = driver.findElement(By.id("btnAddInCart"));
        btnAddInCart.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("http://localhost:8181/cart");
        WebElement product = driver.findElement(By.className("table-responsive"));
        String titleProduct = product.findElement(By.className("mt-3")).getText();
        Assert.assertTrue(!titleProduct.isEmpty());

    }

    @AfterSuite
    public void shutdown() {
        this.driver.quit();
    }

}
