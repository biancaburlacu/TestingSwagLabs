package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.PageFactory.initElements;

public class DashboardPage {
    private final WebDriver driver;

    @FindBy(how = How.ID,using = "user-name")
    private WebElement username;

    @FindBy(how = How.ID,using = "password")
    private WebElement password;

    @FindBy(how = How.ID,using = "login-button")
    private WebElement loginButton;
    @FindBy(how = How.CSS, using = "[data-test='error']")
    private WebElement error;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver,this);
    }
    public void setUsername(String username) {
        this.username.sendKeys(username);
    }
    public void setPassword(String password){
        this.password.sendKeys(password);
    }
    public void login(String username, String password){
        setUsername(username);
        setPassword(password);
        this.loginButton.click();
    }
    public Boolean waitForPageToLoad(){
        List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfAllElements(username,password,loginButton)
        );
        return !elements.isEmpty();
    }
    public String waitForMessage(){
       return new WebDriverWait(driver,Duration.ofSeconds(10))
               .until(ExpectedConditions.visibilityOf(this.error)).getText();
    }
}
