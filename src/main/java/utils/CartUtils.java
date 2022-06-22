package utils;

import components.Item;
import org.openqa.selenium.WebDriver;
import pom.ProductsPage;
import pom.YourCartPage;

public class CartUtils {
    private final WebDriver driver;
    private ProductsPage productsPage;
    private YourCartPage yourCartPage;
    public CartUtils(WebDriver driver) {
        this.driver = driver;
        productsPage = new ProductsPage(this.driver);
        yourCartPage = new YourCartPage(this.driver);
    }

    public ProductsPage getProductsPage() {
        return productsPage;
    }

    public YourCartPage getYourCartPage() {
        return yourCartPage;
    }
    public void addProductToCart(Item item){
        getProductsPage().addProductToCart(item);
    }
    public void goToYourCart(){
        getProductsPage().goToShoppingCart();
    }
    public void removeProductFromCart(Item item){
        getProductsPage().removeProduct(item);
    }
    public int getNumberOfItemsInTheCart(){
        return getProductsPage().getNumberOfItemsInTheCart();
    }

}
