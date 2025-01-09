import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

class InventoryPage extends LoggedInPages {

    enum productOrder {AZ, ZA, LOHI, HILO}
    public static void selectProductOrder(productOrder po) {
        Select orderSelector = new Select(driver.findElement(By.tagName("select")));
        orderSelector.selectByValue(po.toString().toLowerCase());
    }

    public static WebElement getNthProduct(int index) {
        return driver.findElements(By.xpath("//div[@class=\"inventory_item_price\"]")).get(index);
    }

    public static WebElement getProductByName(String charSequence) {
        WebElement result = null;
        try {
            List<WebElement> finds = driver.findElements(By.xpath(".//div[@class=\"inventory_item\"]"));
            for (WebElement w : finds) {
                if (getProductName(w).contains(charSequence)) {
                    result = w;
                    break;
                }
            }
        } catch (NoSuchElementException _) { }
        return result;
    }

    public static WebElement getAddToCartButton(WebElement product) {
        return product.findElement(By.xpath(".//descendant::button"));
    }

    public static String getProductName(WebElement product) {
        return product.findElement(By.xpath(".//descendant::div[@class=\"inventory_item_name \"]")).getText();
    }

    public static String getProductDescription(WebElement product) {
        return product.findElement(By.xpath(".//descendant::div[@class=\"inventory_item_desc\"]")).getText();
    }

    public static String getProductPrice(WebElement product) {
        return product.findElement(By.xpath(".//descendant::div[@class=\"inventory_item_price\"]")).getText();
    }
}
