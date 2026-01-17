package Helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save it inside your Report folder so the HTML can access it relatively
        String relativePath = "Screenshots/" + screenshotName + "_" + timestamp + ".png";
        String absolutePath = System.getProperty("user.dir") + "/Files/Report/" + relativePath;

        try {
            FileUtils.copyFile(src, new File(absolutePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return the relative path for the Extent Report to use
        return relativePath;
    }
}