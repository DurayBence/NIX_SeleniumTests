import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

class LoginPage {
    static WebDriver driver = Driver.buildChromeDriver();
    private static final Duration threeSec = Duration.ofSeconds(3);
    static WebDriverWait wait = new WebDriverWait(driver, threeSec);

    public static void open() {
        driver.get("https://saucedemo.com");
    }

    enum usernames {
        STANDARD_USER, LOCKED_OUT_USER, PROBLEM_USER,
        PERFORMANCE_GLITCH_USER, ERROR_USER, VISUAL_USER
    }
    public static void login(usernames user) {
        List<WebElement> inputs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("input")));
        try {
            closeErrorMessage();
            inputs.get(0).clear();
            inputs.get(0).sendKeys(user.toString().toLowerCase());
            inputs.get(1).clear();
            inputs.get(1).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            if (!user.equals(usernames.LOCKED_OUT_USER)) {
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[@class=\"inventory_item\"]")));
            }
        } catch (Exception _) { }
    }

    public static String getHeaderLogoText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,\"login_logo\")]"))).getText();
    }

    public static WebElement getErrorContainer() {
        WebElement result = null;
        try {
            result = driver.findElement(By.tagName("h3"));
        } catch (NoSuchElementException _) { }
        return result;
    }

    public static String getErrorMessage() {
        String message = "";
        try {
            message = getErrorContainer().getText();
        } catch (Exception _) { }
        return message;
    }

    public static void closeErrorMessage() {
        try {
            getErrorContainer().findElement(By.className("error-button")).click();
        } catch (Exception _) { }
    }
}
