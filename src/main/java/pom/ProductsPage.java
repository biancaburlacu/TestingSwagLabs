package pom;

import components.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;

public class ProductsPage {
    private final WebDriver driver;

    @FindBy(how = How.CSS,using = "#shopping_cart_container")
    private WebElement shoppingCart;
    @FindBy(how = How.CLASS_NAME,using = "title")
    private WebElement pageTitle;
    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_badge")
    private WebElement numberOfItemsInTheCart;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver,this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void addProductToCart(Item item){
        driver.findElement(By.id("add-to-cart-"+item.getItemId())).click();
    }
    public void removeProduct(Item item){
        driver.findElement(By.id("remove-"+item.getItemId())).click();
    }
    public void goToShoppingCart(){
        this.shoppingCart.click();
    }
    public Boolean waitForPageToLoad(){
        List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfAllElements(pageTitle,shoppingCart));
        return !elements.isEmpty();
    }
    public int getNumberOfItemsInTheCart(){
        return Integer.parseInt(numberOfItemsInTheCart.getText());
    }
    public ExpectedCondition<Boolean> checkCartItems(){
        return webDriver -> {
            try{
                return numberOfItemsInTheCart.isDisplayed() && numberOfItemsInTheCart.isEnabled();
            }
            catch (org.openqa.selenium.NoSuchElementException e){
                return false;
            }
        };

    }
    public ExpectedCondition<Boolean> checkIfProductCanBeRemovedFromCart(Item item){
        return webDriver -> {
            try{
                return driver.findElement(By.id("remove-"+item.getItemId())).isDisplayed()
                        ||driver.findElement(By.id("remove-"+item.getItemId())).isEnabled();
            }
            catch (NoSuchElementException e){
                return false;
            }
        };

    }
}
