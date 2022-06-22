import components.Item;
import components.User;
import env.Environment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CartUtils;
import utils.LoginUtils;
import utils.Screenshot;

public class YourCartPageTest {
    private WebDriver driver;
    private final Environment environment = new Environment();
    private CartUtils cartUtils;

    private void initialize(){
        driver = new ChromeDriver();
        driver.get(environment.getEnvironmentURL());
        LoginUtils loginUtils = new LoginUtils(driver);
        loginUtils.login(User.USER,User.PASS);
        cartUtils = new CartUtils(driver);
    }

    private void addProductsToCart(Item item){
        cartUtils.getProductsPage().addProductToCart(item);
    }
    private void goToYourCart(){
        cartUtils.getProductsPage().goToShoppingCart();
    }
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void loadYourCartPage(){
        initialize();
        addProductsToCart(Item.ONESIE);
        Assertions.assertEquals(1,cartUtils.getProductsPage().getNumberOfItemsInTheCart());
        goToYourCart();
        Assertions.assertTrue(cartUtils.getYourCartPage().waitForPageToLoad());
        Assertions.assertEquals("YOUR CART", cartUtils.getYourCartPage().getPageTitle());
    }
    @Test
    public void goToShoppingCartAndCheckTheProducts(){
        initialize();
        addProductsToCart(Item.BACKPACK);
        Assertions.assertEquals(1,cartUtils.getProductsPage().getNumberOfItemsInTheCart());
        cartUtils.getProductsPage().goToShoppingCart();
        Assertions.assertEquals("YOUR CART",cartUtils.getYourCartPage().getPageTitle());
        Assertions.assertEquals(1,cartUtils.getYourCartPage().getCartItems().size());
        Assertions.assertEquals("Sauce Labs Backpack",cartUtils.getYourCartPage().getCartItems().get(0).getText());
    }
    @Test
    public void removeAllItemsFromCart(){
        initialize();
        addProductsToCart(Item.BACKPACK);
        Assertions.assertEquals(1,cartUtils.getProductsPage().getNumberOfItemsInTheCart());
        cartUtils.getProductsPage().goToShoppingCart();
        Assertions.assertEquals(1,cartUtils.getYourCartPage().getCartItems().size());
        cartUtils.removeProductFromCart(Item.BACKPACK);
        Screenshot.captureScreen(driver);
        Assertions.assertEquals(0,cartUtils.getYourCartPage().getCartItems().size());
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
