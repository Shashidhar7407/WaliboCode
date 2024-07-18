package TestScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import ReportUtility.ExtentReportUtil;

public class TC_Woliba_Events extends TC_Walibo_Login_and_HomePage {

    @Test(dependsOnMethods = "Login_With_User")
    public void WolibaEventsTest() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Verify and log if the event icon is displayed
            boolean isIconDisplayed = eventPage.eventIcon().isDisplayed();
            if (isIconDisplayed) {
                ExtentReportUtil.log(Status.PASS, "Event icon is displayed");
                Assert.assertTrue(isIconDisplayed);
            } else {
                ExtentReportUtil.log(Status.FAIL, "Event icon is not displayed");
                Assert.fail("Event icon is not displayed");
            }

            wait.until(ExpectedConditions.elementToBeClickable(eventPage.eventIcon())).click();
            ExtentReportUtil.log(Status.INFO, "Clicked on Event button");

            Thread.sleep(5000);
            ExtentReportUtil.log(Status.INFO, "Current URL: " + driver.getCurrentUrl());
            Thread.sleep(5000);

            List<WebElement> benefitContainers = eventPage.getBenefitContainers();

            // Iterate through each benefit container and print the title and description
            for (WebElement container : benefitContainers) {
                // Find the title element within the container
                WebElement titleElement = eventPage.getTitleElement(container);
                String title = titleElement.getText();

                // Find the description element within the container
                WebElement descriptionElement = eventPage.getDescriptionElement(container);
                String description = descriptionElement.getText();

                // Log the title and description
                ExtentReportUtil.log(Status.INFO, "Title: " + title);
                ExtentReportUtil.log(Status.INFO, "Description: " + description);
                System.out.println("Title: " + title);
                System.out.println("Description: " + description);
                System.out.println("-----------------------------");
            }

            WebElement healthIcon = driver.findElement(By.xpath("//div[@title='Health']"));

            boolean isIconDisplayedHealth = healthIcon.isDisplayed();
            if (isIconDisplayedHealth) {
                ExtentReportUtil.log(Status.PASS, "Health icon is displayed");
                Assert.assertTrue(isIconDisplayedHealth);
                System.out.println("Health icon is displayed");
            } else {
                ExtentReportUtil.log(Status.FAIL, "Health icon is not displayed");
                Assert.fail("Health icon is not displayed");
            }

            // Scroll to the health icon and click it
            js.executeScript("arguments[0].scrollIntoView(true);", healthIcon);
            Thread.sleep(5000);
            wait.until(ExpectedConditions.elementToBeClickable(healthIcon));
            js.executeScript("arguments[0].click();", healthIcon);

            Thread.sleep(10000);

            List<WebElement> benefitContainers1 = eventPage.getBenefitContainers1();
            int containerCount = benefitContainers1.size();
            ExtentReportUtil.log(Status.INFO, "Total number of benefit containers: " + containerCount);
            System.out.println("Total number of benefit containers: " + containerCount);

            // Iterate through each benefit container and print the description
            for (WebElement container : benefitContainers1) {
                // Find the description element within the container
                WebElement descriptionElements = eventPage.getDescriptionElements(container);
                String description = descriptionElements.getText();

                // Log the description
                ExtentReportUtil.log(Status.INFO, "Description: " + description);
                System.out.println("Description: " + description);
                System.out.println("-----------------------------");
            }

            // Click the completed button
            WebElement completedButton = eventPage.completedButton();
            js.executeScript("arguments[0].scrollIntoView(true);", completedButton);
            wait.until(ExpectedConditions.elementToBeClickable(completedButton));
            js.executeScript("arguments[0].click();", completedButton);

            Thread.sleep(5000);
            ExtentReportUtil.log(Status.INFO, "Launched in Completed Events Page: " + driver.getCurrentUrl());
            Thread.sleep(5000);

        } catch (Exception e) {
            // Log any exceptions encountered during the test
            ExtentReportUtil.log(Status.FAIL, "Exception during test execution: " + e.getMessage());
            throw e;
        } finally {
            // Log the completion of the test steps
            ExtentReportUtil.log(Status.INFO, "Finished executing test steps for WolibaEvents");
        }
    }
}
