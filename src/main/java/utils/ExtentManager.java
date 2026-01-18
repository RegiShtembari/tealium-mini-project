package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.io.File;

public class ExtentManager {

    public static ExtentReports createInstance(String testName) {
        //  project root
        String projectPath = System.getProperty("user.dir");

        //  directory path
        String directory = projectPath + "/Files/Report/";
        String fileName = testName + "_Report.html";

        File reportDir = new File(directory);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(directory + fileName);
        spark.config().setReportName(testName + " Execution Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(spark);
        return extent;
    }
}