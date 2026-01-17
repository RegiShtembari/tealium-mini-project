package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class TestListener implements ITestListener {
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        // Get the name of the test class being run (e.g., CartTest)
        String className = result.getTestClass().getRealClass().getSimpleName();

        // Create a NEW report file for this specific run
        extent = ExtentManager.createInstance(className);

        // Create the test entry
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed: " + result.getName());
        extent.flush(); // Saves the report for this class
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        }

        // This 'saves' the report to Files/Report/
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getName());
        extent.flush();
    }

    @Override
    public void onFinish(ITestContext context) {
        // Final safety flush
        if (extent != null) {
            extent.flush();
        }
    }
}