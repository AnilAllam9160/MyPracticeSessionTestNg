package runners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"Steps"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner extends  AbstractTestNGCucumberTests {
    ExtentSparkReporter htmlReporter;
    ExtentReports reports;
    public static ExtentTest test;

    @BeforeClass
    public void startReport() {
        try {
            htmlReporter = new ExtentSparkReporter("src/ExtentReport.html");
            reports = new ExtentReports();
            reports.attachReporter(htmlReporter);

            reports.setSystemInfo("Machine", "Mac");
            reports.setSystemInfo("OS", "IOS");
            reports.setSystemInfo("User", "Allam");
            reports.setSystemInfo("Browser", "Chrome");

            htmlReporter.config().setDocumentTitle("Irctc Railways");
            htmlReporter.config().setReportName("");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd yyyy,hh:mm a'('zzzz')'");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (reports != null) {
                reports.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setupTest() {
        test = reports.createTest("Cucumber Test");
    }

    @AfterMethod
    public void getTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "FAIL", ExtentColor.RED));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "PASSED", ExtentColor.GREEN));
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "PASSED", ExtentColor.YELLOW));
        }
    }

    static {
        System.out.println("java.library.path: " + System.getProperty("java.library.path")+"......");
    }

}
