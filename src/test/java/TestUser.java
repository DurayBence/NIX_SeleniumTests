import org.openqa.selenium.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestUser {

    @BeforeAll
    static void setup() {
        LoginPage.open();
    }

    @Test
    void loginLockedOutUser() {
        LoginPage.login(LoginPage.usernames.LOCKED_OUT_USER);
        assertNotNull(LoginPage.getHeaderLogoText());
        assertEquals("Epic sadface: Sorry, this user has been locked out.", LoginPage.getErrorMessage());
        LoginPage.closeErrorMessage();
        assertEquals("", LoginPage.getErrorMessage());
    }

    @Test
    void emulatePurchase() {
        //2-3.
        LoginPage.login(LoginPage.usernames.STANDARD_USER);

        //4-5.
        InventoryPage.selectProductOrder(InventoryPage.productOrder.LOHI);
        assertEquals("$7.99", InventoryPage.getNthProduct(0).getText());

        //6-7.
        WebElement tshirt = InventoryPage.getProductByName("Bolt");
        WebElement button = InventoryPage.getAddToCartButton(tshirt);
        button.click();
        button = InventoryPage.getAddToCartButton(tshirt);
        assertEquals("Remove", button.getText());
        assertEquals(1, InventoryPage.getCartQuantity());

        //8-9.
        String productName = InventoryPage.getProductName(tshirt);
        String description = InventoryPage.getProductDescription(tshirt);
        String price = InventoryPage.getProductPrice(tshirt);

        InventoryPage.openCart();
        WebElement firstCartItem = MyCartPage.getNthCartItem(0);
        assertEquals(MyCartPage.getCartItemName(firstCartItem), productName);
        assertEquals(MyCartPage.getCartItemDescription(firstCartItem), description);
        assertEquals(MyCartPage.getCartItemPrice(firstCartItem), price);

        //10-11.
        MyCartPage.removeCartItem(firstCartItem);
        assertEquals(0, MyCartPage.getCartQuantity());
        MyCartPage.continueShopping();

        //12-13.
        WebElement backpack = InventoryPage.getProductByName("Backpack");
        productName = InventoryPage.getProductName(backpack);
        //description = InventoryPage.getProductDescription(backpack);
        price = InventoryPage.getProductPrice(backpack);
        InventoryPage.getAddToCartButton(backpack).click();
        InventoryPage.openCart();
        MyCartPage.checkout();

        //14-17.
        CheckoutPage.fillInputAndContinue("Thomas", "Tester", "12345");
        assertTrue(CheckoutPage.cartItemNameMatches(0, productName));
        assertTrue(CheckoutPage.getItemTotalPrice().contains(price));
        CheckoutPage.finishCheckout();
        assertEquals("Pony Express", CheckoutPage.getCheckoutResponseImage().getDomAttribute("alt"));
    }

    @AfterEach
    void reset() {
        LoggedInPages.resetAppState();
    }

    @AfterAll
    static void tearDown() {
        //LoginPage.driver.quit();
    }
}
