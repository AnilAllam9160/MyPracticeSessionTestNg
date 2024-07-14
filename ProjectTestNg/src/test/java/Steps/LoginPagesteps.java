package Steps;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import runners.TestRunner;
import utils.WebDriverManager;

import java.io.IOException;


public class LoginPagesteps {

    WebDriver driver = WebDriverManager.getDriver();
    LoginPage loginPage = new LoginPage(driver);


    @When("Login to IRCTC Application")
    public void user_login_to_application() throws InterruptedException, IOException {
        TestRunner.test.log(Status.INFO, "User Logging To IRCTC Application");
        loginPage.clickOnLoginButton();
        loginPage.enterUserName("aneelAllam");
        loginPage.enterPassword("qweryu");
        loginPage.getCaptchaCode();
        Thread.sleep(7000);
        loginPage.clickOnSubmitButton();
    }
}
