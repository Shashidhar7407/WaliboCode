package TestScripts;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import ReportUtility.ExtentReportUtil;

public class TC_Walibo_Login_and_HomePage extends TestBase {

    @Test
    public void Login_With_User() throws Exception {
        // Create a new test in the Extent Report
        ExtentTest test = ExtentReportUtil.extentReports.createTest("Results of Steps Execution");
        ExtentReportUtil.setTest(test);

        try {
            // Log the navigation to the login page
            ExtentReportUtil.log(Status.INFO, "Navigating to login page");
            ExtentReportUtil.log(Status.INFO, "Current URL: " + driver.getCurrentUrl());

            // Enter the username
            login_object1.Login_UserName().click();
            ExtentReportUtil.log(Status.INFO, "Clicked on username field");
            login_object1.Login_UserName().sendKeys(prop.getProperty("LoginUserName"));
            ExtentReportUtil.log(Status.INFO, "Entered username");

            // Wait for the password field to be clickable and enter the password
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(login_object1.Login_Password())).click();
            ExtentReportUtil.log(Status.INFO, "Clicked on password field");
            login_object1.Login_Password().sendKeys(prop.getProperty("LoginPassword"));
            ExtentReportUtil.log(Status.INFO, "Entered password");

            // Scroll to and click the login button
            scrollToElement(driver, login_object1.Login_Button());
            ExtentReportUtil.log(Status.INFO, "Scrolled to login button");
            wait.until(ExpectedConditions.elementToBeClickable(login_object1.Login_Button())).click();
            ExtentReportUtil.log(Status.INFO, "Clicked on login button");
            Thread.sleep(5000);
            
            // Wait for the login process to complete
            Thread.sleep(5000);
            
            // Verify the URL after login
            Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("HomePageUrl"));
            ExtentReportUtil.log(Status.PASS, "Successfully Logged in");
            ExtentReportUtil.log(Status.INFO, "Current URL after login: " + driver.getCurrentUrl());
            
            // Verify the display of the Walibo logo
            boolean isLogoDisplayed = login_object1.walibologoImage().isDisplayed();
            if (isLogoDisplayed) {
                ExtentReportUtil.log(Status.PASS, "Logo image is displayed");
            } else {
                ExtentReportUtil.log(Status.FAIL, "Logo image is not displayed");
            }

            // Retrieve and log the greeting message
            String greetingMessage = displayGreetingMessage();
            ExtentReportUtil.log(Status.INFO, "Greeting message: " + greetingMessage);

            // Verify the greeting message displayed on the homepage
            WebElement greetingMessageElement = wait.until(ExpectedConditions.visibilityOf(login_object1.GreetingMessageHomepage()));
            ExtentReportUtil.log(Status.INFO, "Greeting message element is visible");
            Assert.assertEquals(greetingMessage, greetingMessageElement.getText(), "Greeting message mismatch");
            ExtentReportUtil.log(Status.PASS, "Greeting Message is displayed");
            
            // Verify the greeting username displayed on the homepage
            WebElement greetingUserName = wait.until(ExpectedConditions.visibilityOf(login_object1.GreetingUserNameHomepage()));
            ExtentReportUtil.log(Status.INFO, "Greeting UserName: " + greetingUserName.getText());
            Assert.assertEquals(greetingUserName.getText(), prop.getProperty("UserName") + "!");
            ExtentReportUtil.log(Status.INFO, "Greeting UserName element is visible");

        } catch (Exception e) {
            // Log any exceptions encountered during the test
            ExtentReportUtil.log(Status.FAIL, "Exception during test execution: " + e.getMessage());
            throw e;
        } finally {
            // Log the completion of the test steps
            ExtentReportUtil.log(Status.INFO, "Finished executing test steps for Login_With_User");
        }
    }
}
