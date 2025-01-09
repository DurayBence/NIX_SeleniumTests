import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoggedInPages {
    protected static WebDriver driver = LoginPage.driver;
    protected static WebDriverWait wait = LoginPage.wait;

    protected static WebElement getYourCartButton() {
        return driver.findElement(By.xpath("//a[@class=\"shopping_cart_link\"]"));
    }

    protected static void openCart(){
        try {
            getYourCartButton().click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        } catch (NoSuchElementException _) {}
    }

    protected static int getCartQuantity() {
        int result = 0;
        try {
            result = Integer.parseInt(getYourCartButton().findElement(By.tagName("span")).getText());
        } catch (NoSuchElementException _) { }
        return result;
    }

    protected static void resetAppState() {
        try {
            driver.findElement(By.xpath("//button[contains(.,\"Open Menu\")]")).click();
            wait.until(ExpectedConditions.elementToBeClickable((By.id("reset_sidebar_link")))).click();
            driver.findElement(By.id("inventory_sidebar_link")).click();
            driver.findElement(By.id("react-burger-cross-btn")).click();
            wait.until(ExpectedConditions.attributeToBe(By.id("bm-menu-wrap"), "aria-hidden", "false"));
        } catch (Exception _) {}
    }
}
