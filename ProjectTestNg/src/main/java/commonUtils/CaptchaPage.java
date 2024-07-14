package commonUtils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;


import java.io.File;
import java.io.IOException;



public class CaptchaPage {


    public static String getEnterCaptcha(WebElement captchaImage) throws TesseractException, IOException {

        //WebElement captchaImageElement = captchaImage;
        File src = captchaImage.getScreenshotAs(OutputType.FILE);
        String path = "ProjectTestNg/captures/captcha.png";

        try {
            FileUtils.copyFile(src, new File(path));
            // Read the CAPTCHA text using Tesseract
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("/opt/homebrew/opt/tesseract/share"); // Specify the path to tessdata folder
            String captchaText = tesseract.doOCR(new File(path));
            System.out.println("CAPTCHA Text: " + captchaText.trim());
            return captchaText;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }


}





