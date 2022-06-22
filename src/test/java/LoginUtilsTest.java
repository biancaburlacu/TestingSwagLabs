import env.Environment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.DashboardPage;

import static components.User.*;

public class LoginUtilsTest {
    private WebDriver driver;
    private final Environment environment = new Environment();
    private DashboardPage dashboardPage;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }
    public void setLoginPage(){
        driver = new ChromeDriver();
        driver.get(environment.getEnvironmentURL());
        dashboardPage = new DashboardPage(driver);
    }
    @Test
    public void loadDashboardPage(){
        setLoginPage();
        dashboardPage.waitForPageToLoad();
        Assertions.assertTrue(dashboardPage.waitForPageToLoad());
    }
    @Test
    public void successfulLogin(){
        setLoginPage();
        dashboardPage.login(USER,PASS);
        String title = driver.findElement(By.className("title")).getText();
        Assertions.assertEquals("PRODUCTS",title);
    }
    @Test
    public void loginWithLockedUser(){
        setLoginPage();
        dashboardPage.login(LOCKED_USER,PASS);
        Assertions.assertEquals("Epic sadface: Sorry, this user has been locked out."
                ,dashboardPage.waitForMessage());
    }
    @Test
    public void loginInvalidUser(){
        setLoginPage();
        dashboardPage.login(INVALID_USER,PASS);
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service"
                ,dashboardPage.waitForMessage());
    }
    @AfterEach
    public void closeDriver(){
        driver.quit();
    }
}
