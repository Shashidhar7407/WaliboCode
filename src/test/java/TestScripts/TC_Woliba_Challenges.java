package TestScripts;

import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import ReportUtility.ExtentReportUtil;

public class TC_Woliba_Challenges extends TC_Walibo_Login_and_HomePage {

    @Test(dependsOnMethods = "Login_With_User")
    public void InitializeChallengePage() {
        ExtentReportUtil.extentReports.createTest("InitializeChallengePage");
        ExtentReportUtil.log(Status.INFO, "Initialized ChallengePage object.");
    }

    @Test(dependsOnMethods = "InitializeChallengePage")
    public void VerifyChallengeIconIsDisplayed() {
        ExtentReportUtil.extentReports.createTest("VerifyChallengeIconIsDisplayed");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement challengeIcon = challengepage.getChallengeIcon();
            boolean isIconDisplayed = challengeIcon.isDisplayed();
            assertTrue(isIconDisplayed, "Challenge icon is not displayed.");
            ExtentReportUtil.log(Status.PASS, "Challenge icon is displayed");

        } catch (Exception e) {
            ExtentReportUtil.log(Status.FAIL, "Exception during challenge icon verification: " + e.getMessage());
            throw e;
        } finally {
            ExtentReportUtil.log(Status.INFO, "Finished verifying the challenge icon.");
        }
    }

    @Test(dependsOnMethods = "VerifyChallengeIconIsDisplayed")
    public void ClickOnChallengeIcon() throws Exception {
        ExtentReportUtil.extentReports.createTest("ClickOnChallengeIcon");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement challengeIcon = challengepage.getChallengeIcon();
            wait.until(ExpectedConditions.elementToBeClickable(challengeIcon)).click();
            ExtentReportUtil.log(Status.INFO, "Clicked on the challenge icon.");
            
            // Wait for the page to load
            Thread.sleep(5000);
            ExtentReportUtil.log(Status.INFO, "Current URL: " + driver.getCurrentUrl());

        } catch (Exception e) {
            ExtentReportUtil.log(Status.FAIL, "Exception during challenge icon click: " + e.getMessage());
            throw e;
        } finally {
            ExtentReportUtil.log(Status.INFO, "Finished clicking on the challenge icon.");
        }
    }

    @Test(dependsOnMethods = "ClickOnChallengeIcon")
    public void VerifyChallengeDashboardIsDisplayed() {
        ExtentReportUtil.extentReports.createTest("VerifyChallengeDashboardIsDisplayed");

        try {
            WebElement challengeIconDashboard = challengepage.getChallengedashboard();
            boolean isIconDisplayed = challengeIconDashboard.isDisplayed();
            assertTrue(isIconDisplayed, "Challenge dashboard icon is not displayed.");
            ExtentReportUtil.log(Status.PASS, "Challenge dashboard icon is displayed");

        } catch (Exception e) {
            ExtentReportUtil.log(Status.FAIL, "Exception during challenge dashboard verification: " + e.getMessage());
            throw e;
        } finally {
            ExtentReportUtil.log(Status.INFO, "Finished verifying the challenge dashboard icon.");
        }
    }

    @Test(dependsOnMethods = "VerifyChallengeDashboardIsDisplayed")
    public void VerifyChallengeDashboardFeatureIsDisplayed() {
        ExtentReportUtil.extentReports.createTest("VerifyChallengeDashboardFeatureIsDisplayed");

        try {
            WebElement challengeDashboardFeature = challengepage.getChallengedashboardfeature();
            boolean isFeatureDisplayed = challengeDashboardFeature.isDisplayed();
            assertTrue(isFeatureDisplayed, "Challenge dashboard feature icon is not displayed.");
            ExtentReportUtil.log(Status.PASS, "Challenge dashboard feature icon is displayed");

        } catch (Exception e) {
            ExtentReportUtil.log(Status.FAIL, "Exception during challenge dashboard feature verification: " + e.getMessage());
            throw e;
        } finally {
            ExtentReportUtil.log(Status.INFO, "Finished verifying the challenge dashboard feature icon.");
        }
    }

    @Test(dependsOnMethods = "VerifyChallengeDashboardFeatureIsDisplayed")
    public void verifyActivityChallenges() {
        ExtentReportUtil.extentReports.createTest("VerifyActivityChallenges");

        try {
            boolean isActiveChallengesPresent = challengepage.isActiveChallengesPresent();
            assertTrue(isActiveChallengesPresent, "Active challenges are not present.");
            ExtentReportUtil.log(Status.PASS, "Active challenges are present");

        } catch (Exception e) {
            ExtentReportUtil.log(Status.FAIL, "Exception during activity challenges verification: " + e.getMessage());
            throw e;
        } finally {
            ExtentReportUtil.log(Status.INFO, "Finished verifying activity challenges.");
        }
    }
}
