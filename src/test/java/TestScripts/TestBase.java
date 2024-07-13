package TestScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.Status;

import PageObjectModel.LeaderBoardPage;
import PageObjectModel.LoginPage;
import ReportUtility.ExtentReportUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
    protected WebDriver driver;
    protected JavascriptExecutor jsExecutor;
    protected LoginPage login_object1;
    protected Properties prop;
    protected LeaderBoardPage Leaderboard_object1;

    @BeforeTest
    public void setUp(ITestContext context) throws IOException {
        // Load the URL and Browser parameters from the test context
        String Url = context.getCurrentXmlTest().getParameter("Url");
        String Browser = context.getCurrentXmlTest().getParameter("Browser");

        // Setup WebDriver based on the browser type
        if (Browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (Browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        // Initialize ExtentReports
        ExtentReportUtil.setDriver(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Load properties file
        try (FileInputStream fileLoc = new FileInputStream("C:\\Users\\shash\\OneDrive\\Desktop\\Mobile Automation\\Leaderboard\\src\\test\\java\\TestData\\Walibo.properties")) {
            prop = new Properties();
            prop.load(fileLoc);
        }

        // Initialize page objects
        jsExecutor = (JavascriptExecutor) driver;
        login_object1 = new LoginPage(driver);
        Leaderboard_object1 = new LeaderBoardPage(driver);

        // Navigate to the specified URL
        driver.get(Url);
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        try {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            ExtentReportUtil.log(Status.INFO, "Scrolled to element using JavaScript: " + element);
        } catch (Exception e) {
            ExtentReportUtil.log(Status.ERROR, "Failed to scroll to element: " + element + " due to: " + e.getMessage());
        }
    }

    public static void takeScreenshot(WebDriver driver, String filePath) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(filePath));
    }

    public String displayGreetingMessage() {
        LocalTime currentTime = LocalTime.now();
        String greetingMessage;
        if (currentTime.isAfter(LocalTime.of(0, 0)) && currentTime.isBefore(LocalTime.of(12, 0))) {
            greetingMessage = "Good Morning,";
        } else if (currentTime.isAfter(LocalTime.of(12, 0)) && currentTime.isBefore(LocalTime.of(18, 0))) {
            greetingMessage = "Good Afternoon,";
        } else {
            greetingMessage = "Good Evening,";
        }
        System.out.println(greetingMessage);
        try {
            Thread.sleep(5000); // Pause for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return greetingMessage;
    }

    public static String getTodaysWeekday() {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    }

    public static String getTodaysDate() {
        LocalDate today = LocalDate.now();
        return String.valueOf(today.getDayOfMonth());
    }

    public static String getCurrentMonthAndYear() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM | yyyy", Locale.ENGLISH);
        return today.format(formatter);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
