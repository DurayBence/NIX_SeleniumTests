import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class TestParameterized {

    @BeforeAll
    static void setup() {
        LoginPage.open();
        LoginPage.login(LoginPage.usernames.STANDARD_USER);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Fleece Jacket", "Sauce Labs Bike Light"})
    void testPrice(String namePattern) {
        WebElement product = InventoryPage.getProductByName(namePattern);
        String inventoryPrice = InventoryPage.getProductPrice(product);
        InventoryPage.getAddToCartButton(product).click();
        LoggedInPages.openCart();
        String cartPrice = MyCartPage.getCartItemPrice(MyCartPage.getNthCartItem(0));
        assertEquals(inventoryPrice, cartPrice);
    }

    @AfterEach
    void reset() {
        LoggedInPages.resetAppState();
    }

    @AfterAll
    static void tearDown() {
        LoginPage.driver.quit();
    }
}
