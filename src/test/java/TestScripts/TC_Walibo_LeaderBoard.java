package TestScripts;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageObjectModel.LeaderBoardPage;
import ReportUtility.ExtentReportUtil;

public class TC_Walibo_LeaderBoard extends TC_Walibo_Login_and_HomePage {
    
    @Test(dependsOnMethods = "Login_With_User")
    public void WaliboLeaderBoard() throws InterruptedException {
        // Create a new test in the Extent Report
        ExtentReportUtil.extentReports.createTest("WaliboLeaderBoard");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Verify and log if the leaderboard icon is displayed
            boolean isIconDisplayed = Leaderboard_object1.leaderboardIconImage().isDisplayed();
            if (isIconDisplayed) {
                ExtentReportUtil.log(Status.PASS, "Leaderboard icon is displayed");
                Assert.assertTrue(isIconDisplayed);
            } else {
                ExtentReportUtil.log(Status.FAIL, "Leaderboard icon is not displayed");
                Assert.fail("Leaderboard icon is not displayed");
            }

            // Click on the leaderboard icon
            wait.until(ExpectedConditions.elementToBeClickable(Leaderboard_object1.leaderboardIconImage())).click();
            ExtentReportUtil.log(Status.INFO, "Clicked on leaderboard button");

            // Wait for the page to load
            Thread.sleep(5000);
            ExtentReportUtil.log(Status.INFO, "Current URL: " + driver.getCurrentUrl());

            // Verify the date, day, and month-year displayed
            assertEquals(getTodaysDate(), Leaderboard_object1.currenDate().getText());
            assertEquals(getTodaysWeekday(), Leaderboard_object1.currentDay().getText());
            assertEquals(getCurrentMonthAndYear(), Leaderboard_object1.currentMonthyear().getText());

            String currentDateString = getTodaysDate() + getTodaysWeekday() + " " + getCurrentMonthAndYear();
            Assert.assertEquals(currentDateString, Leaderboard_object1.currenDate().getText()
                    + Leaderboard_object1.currentDay().getText() + " " + Leaderboard_object1.currentMonthyear().getText());
            ExtentReportUtil.log(Status.INFO, "Current date matched with displayed date: " + currentDateString);

            // Wait for any additional loading
            Thread.sleep(5000);

            // Retrieve and log the points for the current month
            String currentMonth = LeaderBoardPage.getCurrentMonth();
            String xpathCurrentMonth = LeaderBoardPage.generateDynamicXPathForMonth(currentMonth);

            WebElement currentMonthPoints = driver.findElement(By.xpath(xpathCurrentMonth));
            ExtentReportUtil.log(Status.INFO, "Current month points: " + currentMonthPoints.getText());
            System.out.println(currentMonthPoints.getText());

        } catch (Exception e) {
            // Log any exceptions encountered during the test
            ExtentReportUtil.log(Status.FAIL, "Exception during test execution: " + e.getMessage());
            throw e;
        } finally {
            // Log the completion of the test steps
            ExtentReportUtil.log(Status.INFO, "Finished executing test steps for WaliboLeaderBoard");
        }
    }
}
