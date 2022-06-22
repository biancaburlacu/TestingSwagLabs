import components.Item;
import components.User;
import env.Environment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CartUtils;
import utils.LoginUtils;

public class ProductsPageTest {
    private WebDriver driver;
    private final Environment environment = new Environment();
    private CartUtils cartUtils;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }
    private void preconditions() {
        driver = new ChromeDriver();
        driver.get(environment.getEnvironmentURL());
        LoginUtils loginUtils = new LoginUtils(driver);
        loginUtils.login(User.USER, User.PASS);
        cartUtils = new CartUtils(driver);
    }

    @Test
    public void loadProductsPageTest(){
        preconditions();
        Assertions.assertTrue(cartUtils.getProductsPage().waitForPageToLoad());
    }

    @Test
    public void addProductsToCart(){
        preconditions();
        cartUtils.getProductsPage().addProductToCart(Item.BACKPACK);
        cartUtils.getProductsPage().addProductToCart(Item.BIKE_LIGHT);
        cartUtils.getProductsPage().addProductToCart(Item.ONESIE);
        Assertions.assertEquals(3,cartUtils.getProductsPage().getNumberOfItemsInTheCart());
    }

    @Test
    public void addAndRemoveProductsFromCart(){
        preconditions();
        cartUtils.getProductsPage().addProductToCart(Item.BACKPACK);
        cartUtils.getProductsPage().addProductToCart(Item.BIKE_LIGHT);
        cartUtils.getProductsPage().addProductToCart(Item.ONESIE);
        Assertions.assertEquals(3,cartUtils.getProductsPage().getNumberOfItemsInTheCart());
        cartUtils.getProductsPage().removeProduct(Item.ONESIE);
        Assertions.assertEquals(2,cartUtils.getProductsPage().getNumberOfItemsInTheCart());
    }

    @Test
    public void removeNonExistingProductFromCart(){
        preconditions();
        Assertions.assertEquals(Boolean.FALSE, cartUtils.getProductsPage().checkCartItems().apply(driver));
        Assertions.assertEquals(Boolean.FALSE, cartUtils.getProductsPage().checkIfProductCanBeRemovedFromCart(Item.BACKPACK).apply(driver));
    }

    @Test
    public void goToShoppingCart(){
        preconditions();
        cartUtils.getProductsPage().addProductToCart(Item.BIKE_LIGHT);
        cartUtils.getProductsPage().addProductToCart(Item.ONESIE);
        Assertions.assertEquals(2,cartUtils.getProductsPage().getNumberOfItemsInTheCart());
        cartUtils.getProductsPage().goToShoppingCart();
        Assertions.assertEquals("YOUR CART",driver.findElement(By.className("title")).getText());
    }

    @AfterEach
    public void closeDriver(){
        driver.quit();
    }

}
