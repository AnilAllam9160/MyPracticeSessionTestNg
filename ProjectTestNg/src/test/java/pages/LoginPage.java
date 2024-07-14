package pages;

import commonUtils.UtilsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage  {
    WebDriver driver;
    UtilsPage utilsPage;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
      this.utilsPage = new UtilsPage(driver);
    }

    public WebElement menuButton() {
        return utilsPage.waitForElementToBeVisible(By.xpath("(//div[contains(@class,'h_menu_drop_button')]//i)[1]"));
    }

    public WebElement loginButton() {
        return utilsPage.waitForElementToBeVisible(By.xpath("//a[@aria-label='Click here to Login in application']"));
    }

    public WebElement userName() {
        return utilsPage.waitForElementToBeVisible(By.xpath("//input[@formcontrolname='userid']"));
    }

    public WebElement userPassword() {
        return utilsPage.waitForElementToBeVisible(By.xpath("//input[@formcontrolname='password']"));
    }

    public WebElement captchaCode() {
        return utilsPage.waitForElementToBeVisible(By.xpath("//div[@class='captcha_div']//img"));
    }

    public WebElement submitButton() {
        return utilsPage.waitForElementToBeVisible(By.xpath("//div[@class='modal-body']//button[@type='submit']"));
    }



    public LoginPage clickOnLoginButton() {
        this.loginButton().click();
        return this;
    }

    public LoginPage enterUserName(String userName) {
        this.userName().sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        this.userPassword().sendKeys(password);
        return this;
    }

    public LoginPage getCaptchaCode()  {
        WebElement imageElement = this.captchaCode();
        String captchaSolution = utilsPage.solveCaptcha(imageElement);
        WebElement captchaInput = driver.findElement(By.xpath("//input[@id='captcha']"));
        captchaInput.sendKeys(captchaSolution);
        return this;
    }

    public LoginPage clickOnSubmitButton() {
        this.submitButton().click();
        return this;
    }

}
