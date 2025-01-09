import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

class MyCartPage extends LoggedInPages {

    public static WebElement getNthCartItem(int index) {
        return driver.findElements(By.xpath("//div[@class=\"cart_item\"]")).get(index);
    }

    public static void removeCartItem(WebElement cartItem) {
        try {
            cartItem.findElement(By.xpath(".//descendant::button")).click();
        } catch (NoSuchElementException _) {
        }
    }

    public static String getCartItemName(WebElement cartItem) {
        return cartItem.findElement(By.xpath(".//div/a/div")).getText();
    }

    public static String getCartItemDescription(WebElement cartItem) {
        return cartItem.findElement(By.xpath(".//div/div")).getText();
    }

    public static String getCartItemPrice(WebElement cartItem) {
        return cartItem.findElement(By.xpath(".//div[@class=\"inventory_item_price\"]")).getText();
    }

    public static void continueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("inventory_item")));
    }

    public static void checkout() {
        driver.findElement(By.id("checkout")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
    }
}
