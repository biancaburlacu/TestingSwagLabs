package pom;

import components.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;

public class YourCartPage {
    private final WebDriver driver;

    @FindBy(how = How.CLASS_NAME,using = "title")
    private WebElement pageTitle;
    @FindBy(how = How.ID,using = "continue-shopping")
    private WebElement continueShoppingButton;
    @FindBy(how = How.ID,using = "checkout")
    private WebElement checkoutButton;
    @FindBy(how = How.CLASS_NAME, using = "inventory_item_name")
    private List<WebElement> cartItems;

    public YourCartPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver,this);
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }
    public Boolean waitForPageToLoad(){
        List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfAllElements(pageTitle,continueShoppingButton,checkoutButton));
        return !elements.isEmpty();
    }
    public String getPageTitle() {
        return pageTitle.getText();
    }
    public void goToCheckout() {
        this.checkoutButton.click();
    }
    public void goToProductsPage(){
        this.continueShoppingButton.click();
    }
    public List<WebElement> getCartItems() {
        return cartItems;
    }
    public void removeItemFromCart(Item item){
        driver.findElement(By.id("remove-"+item.getItemId()));
    }

}
