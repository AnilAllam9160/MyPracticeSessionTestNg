package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

     private WebDriver driver;


        @Before
        public void setUp() {
           this.driver = WebDriverManager.getDriver();
        }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            captureScreenshot(scenario);
        }
        WebDriverManager.quitDriver();
    }

    private void captureScreenshot(Scenario scenario) {
        try {
            // Capture screenshot as a byte array
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Save screenshot to a file
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotName = "FailureScreenshot_" + scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";
            File screenshotFile = new File(System.getProperty("user.dir") + "/screenshots/" + screenshotName);
            Files.write(screenshotFile.toPath(), screenshot);

            // Embed screenshot in Cucumber HTML report
            scenario.attach(screenshot, "image/png", screenshotName);
            System.out.println("Failure screenshot saved at: " + screenshotFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        @After
        public void tearDown() {
            WebDriverManager.quitDriver();
        }

}

