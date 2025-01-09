import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

class CheckoutPage extends LoggedInPages {

    public static void fillInputAndContinue(String givenName, String familyName, String zipCode) {
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        inputs.get(0).sendKeys(givenName);
        inputs.get(1).sendKeys(familyName);
        inputs.get(2).sendKeys(zipCode);
        inputs.get(3).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
    }

    public static boolean cartItemNameMatches(int cartIndex, String name) {
        return driver.findElements(By.className("inventory_item_name")).get(cartIndex).getText().matches(name);
    }

    public static String getItemTotalPrice() {
        return driver.findElement(By.className("summary_subtotal_label")).getText();
    }

    public static void finishCheckout() {
        driver.findElement(By.id("finish")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.tagName("h2"))));
    }

    public static WebElement getCheckoutResponseImage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@class=\"pony_express\"]")));
    }
}
