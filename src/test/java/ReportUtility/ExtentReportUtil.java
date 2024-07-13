package ReportUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil extends TestListenerAdapter {
    private ExtentHtmlReporter htmlReport;
    public static ExtentReports extentReports;
    private static ExtentTest xtest;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver driver) {
        ExtentReportUtil.driver.set(driver);
    }

    @Override
    public void onStart(ITestContext testContext) {
        String dateStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportName = "TestWebAutomationReport-" + dateStamp + ".html";
        String reportDirPath = System.getProperty("user.dir") + File.separator + "Reporter";

        // Ensure the Reporter directory exists
        File reportDir = new File(reportDirPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        htmlReport = new ExtentHtmlReporter(reportDirPath + File.separator + reportName);
        htmlReport.config().setDocumentTitle("Test Automation Execution Summary");
        htmlReport.config().setReportName("Walibo Functional Testing");
        htmlReport.config().setTheme(Theme.STANDARD);
        htmlReport.config().setAutoCreateRelativePathMedia(true);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReport);
        extentReports.setSystemInfo("QA Name", "Engineer");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("OS", "Windows 11");
        extentReports.setSystemInfo("hostname", "localhost");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extentReports.flush();
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        ExtentTest test = extentReports.createTest(tr.getName());
        extentTest.set(test);
        test.log(Status.PASS, "The Test is Passed Successfully");
        test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        ExtentTest test = extentReports.createTest(tr.getName());
        extentTest.set(test);
        test.log(Status.FAIL, "The Test is Failed");
        test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
        test.log(Status.FAIL, tr.getThrowable());

        // Capture screenshot
        String screenshotPath = captureScreenshot(tr.getName());

        if (screenshotPath != null) {
            try {
                test.fail("Screenshot of failed test is below:" + test.addScreenCaptureFromPath(screenshotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        ExtentTest test = extentReports.createTest(tr.getName());
        extentTest.set(test);
        test.log(Status.SKIP, "The Test is Skipped");
        test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.YELLOW));
        test.log(Status.SKIP, tr.getThrowable());
    }

    private String captureScreenshot(String screenshotName) {
        WebDriver driver = ExtentReportUtil.driver.get(); // Get the WebDriver instance for the current thread
        if (driver == null) {
            return null;
        }

        String dateStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String screenshotDirPath = System.getProperty("user.dir") + File.separator + "Screenshots";
        String screenshotPath = screenshotDirPath + File.separator + screenshotName + "-" + dateStamp + ".png";

        // Ensure the ScreenShots directory exists
        File screenshotDir = new File(screenshotDirPath);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);

        try {
            org.openqa.selenium.io.FileHandler.copy(screenshotFile, destFile);
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to log messages to the extent report
    public static void log(Status status, String details) {
        if (xtest != null) {
            xtest.log(status, details);
        } else {
            System.out.println("ExtentTest is not initialized: " + details);
        }
    }

    public static void setTest(ExtentTest test) {
        xtest = test;
    }
}
