package utils;

import org.openqa.selenium.WebDriver;
import pom.DashboardPage;

public class LoginUtils {
    private final WebDriver driver;
    private DashboardPage dashboardPage;

    public LoginUtils(WebDriver driver) {
        this.driver = driver;
        dashboardPage = new DashboardPage(this.driver);
    }

    public DashboardPage getDashboardPage(){
        return this.dashboardPage;
    }

    public void login(String username,String password){
        this.dashboardPage.login(username,password);
    }


}
