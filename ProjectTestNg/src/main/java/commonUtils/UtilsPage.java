package commonUtils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;


public class UtilsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public UtilsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(7000));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String solveCaptcha(WebElement imageElement) {
        String str = null;
        try{
            File src = imageElement.getScreenshotAs(OutputType.FILE);
            String path ="/Users/opsaway/Desktop/ProjectTestNg/captures/captcha.png";
            FileUtils.copyFile(src, new File(path));
            Thread.sleep(3000);
            ITesseract image = new Tesseract();
            image.setDatapath("/opt/homebrew/share/tessdata/");
            str = image.doOCR(new File(path));
            System.out.println("Image OCR Done..");
            System.out.println(str);
        }
        catch (Exception e){
            System.out.println("Exception caught:"+e.getMessage());
        }
        return str;
    }

}