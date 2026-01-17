package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String fileName = screenshotName + "_" + System.currentTimeMillis() + ".png";
        String directoryPath = System.getProperty("user.dir") + "/Files/Screenshots/";

        // ADD THIS: Ensure the folder exists
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String destinationPath = directoryPath + fileName;
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(destinationPath);
            FileUtils.copyFile(src, dest);
            System.out.println("SUCCESS: Screenshot saved to: " + destinationPath);
        } catch (IOException e) {
            System.out.println("ERROR: Could not capture screenshot: " + e.getMessage());
        }
        return "../Screenshots/" + fileName;
    }
}