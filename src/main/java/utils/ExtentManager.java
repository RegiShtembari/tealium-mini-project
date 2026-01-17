package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentManager {

    public static ExtentReports createInstance(String testName) {
        // 1. Get the path to your project root
        String projectPath = System.getProperty("user.dir");

        // 2. Define the full directory path (Using Capital 'F' for Files)
        String directory = projectPath + "/Files/Report/";
        String fileName = testName + "_Report.html";

        // 3. IMPORTANT: Create the folder if it doesn't exist
        File reportDir = new File(directory);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // 4. Initialize the reporter
        ExtentSparkReporter spark = new ExtentSparkReporter(directory + fileName);
        spark.config().setReportName(testName + " Execution Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }
}