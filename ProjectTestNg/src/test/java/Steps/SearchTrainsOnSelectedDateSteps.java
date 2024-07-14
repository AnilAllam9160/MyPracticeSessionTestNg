package Steps;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import runners.TestRunner;
import utils.WebDriverManager;
import java.time.Duration;
import java.util.List;


public class SearchTrainsOnSelectedDateSteps {

     WebDriver driver = WebDriverManager.getDriver();


    @Given("The User is on IRCTC Search Trains Page")
    public void the_user_is_on_irctc_search_trains_page() throws InterruptedException {
        String scenarioName = "The User is on IRCTC Search Trains Page";
        TestRunner.test.log(Status.INFO, "Starting scenario: " + scenarioName);
        TestRunner.test.log(Status.INFO, "Navigating to IRCTC Search Trains Page");
        driver.get("https://www.irctc.co.in/nget/train-search");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-label='Click here to Login in application']")));
        String title = driver.getTitle();
        Assert.assertEquals(title,"IRCTC Next Generation eTicketing System");
        Thread.sleep(10000);
    }

    @When("Select From station")
    public void select_from_station() throws InterruptedException {
        TestRunner.test.log(Status.INFO, "Select From station");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-controls='pr_id_1_list']")));
        driver.findElement(By.xpath("//input[@aria-controls='pr_id_1_list']")).sendKeys("Vz");
        //Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@id='ppr_id_1_list']//li//span)[1]")));
        List<WebElement> fromStationsList= driver.findElements(By.xpath("//*[@id='pr_id_1_list']//li//span"));
        for(int i=0;i<=fromStationsList.size()-1;i++)
        {
            String eachStation=fromStationsList.get(i).getText();
            if(eachStation.contains("VZM"))
            {
                fromStationsList.get(i).click();
                break;
            }
        }
    }
    @When("Select To station")
    public void select_to_station() throws InterruptedException {
        TestRunner.test.log(Status.INFO, "Select To station");
        driver.findElement(By.xpath("//input[@aria-controls='pr_id_2_list']")).sendKeys("SEC");
        Thread.sleep(1000);
        List<WebElement> fromStationsList= driver.findElements(By.xpath("//*[@id='pr_id_2_list']//li//span"));
        for(int i=0;i<=fromStationsList.size()-1;i++)
        {
            String eachStation=fromStationsList.get(i).getText();
            if(eachStation.contains("MALAKPET"))
            {
                fromStationsList.get(i).click();
                break;
            }
        }
    }
    @When("Select Date of Journey")
    public void select_date_of_journey() {
        TestRunner.test.log(Status.INFO, "Select Date of Journey");
        driver.findElement(By.xpath("//*[@id='jDate']//input")).click();
        for (int i = 0; i <= 11; i++) {
            boolean flag = false;
            String yearMonthText = driver.findElement(By.xpath("(//div[contains(@class,'ui-datepicker-title')]/span)[1]")).getText();
            if (yearMonthText.contains("August")) {
                List<WebElement> datesList = driver.findElements(By.xpath("//div[contains(@class,'ui-datepicker-calendar')]//tbody/tr/td/a"));
                for (int j = 0; j <= datesList.size() - 1; j++) {
                    String date = datesList.get(j).getText();
                    if (date.equals("9")) {
                        datesList.get(j).click();
                        flag = true;
                        break;
                    }
                }
            }
            if(flag){
                break;
            }
            else{
                driver.findElement(By.xpath("//span[contains(@class,'ui-datepicker-next')]")).click();
            }

        }
    }
    @When("Select Category")
    public void select_category() throws InterruptedException {
        TestRunner.test.log(Status.INFO, "Select Category");
        driver.findElement(By.xpath("//span[text()='GENERAL']//parent::div//following-sibling::div[contains(@class,'ui-dropdown-trigger')]")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        List<WebElement> categories = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul/p-dropdownitem/li/span")));
        System.out.println(categories.size()+"size of list categories");
        for(int i=0;i<=categories.size()-1;i++)
        {
            String category=categories.get(i).getText();
            System.out.println(category+";;;;;;;;;;");
            if(category.equals("LADIES"))
            {
                categories.get(i).click();
//                Thread.sleep(5000);
                break;
            }
        }
    }
    @Then("Click On Search")
    public void click_on_search() {
        TestRunner.test.log(Status.INFO, "Click On Search");
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        if (submitButton.isEnabled() && submitButton.isDisplayed()) {
            submitButton.click();
        } else {
            System.out.println("Submit button is not clickable.");
        }
    }
}
