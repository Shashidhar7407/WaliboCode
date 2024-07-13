package PageObjectModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeaderBoardPage {
    WebDriver driver;

    public LeaderBoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//span[@class='iconLinkContainer'])[6]")
    WebElement leaderBoard_Icon_image;

    @FindBy(xpath = "//p[@class='last']")
    WebElement current_Date;

    @FindBy(xpath = "//p[@class='first']")
    WebElement current_Day;

    @FindBy(xpath = "//p[@class='one']")
    WebElement current_Month_Year;

    public WebElement leaderboardIconImage() {
        return leaderBoard_Icon_image;
    }

    public WebElement currenDate() {
        return current_Date;
    }

    public WebElement currentDay() {
        return current_Day;
    }

    public WebElement currentMonthyear() {
        return current_Month_Year;
    }

    public static String getCurrentMonth() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
        return today.format(formatter);
    }

    public static String generateDynamicXPathForMonth(String month) {
        return String.format("//div[normalize-space()='%s Points']", month);
    }
}
