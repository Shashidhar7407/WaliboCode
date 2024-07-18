package PageObjectModel;

import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

public class Challengepage {

	static WebDriver driver;

	public Challengepage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//span[@class='iconLinkContainer'])[2]")

	private WebElement challengeIcon;

	@FindBy(xpath = "//div[@class='sc-fPEBxH iwhvHX']")

	private WebElement dashboard;

	@FindBy(xpath = "//div[@class='sc-dxcOqk hayBYf'][normalize-space()='Featured Challenge']")

	private WebElement featured_challenge;

	public WebElement getChallengeIcon() {

		return challengeIcon;

	}

	public WebElement getChallengedashboard() {

		return dashboard;

	}

	public WebElement getChallengedashboardfeature() {

		return featured_challenge;

	}

	public static boolean isActiveChallengesPresent() {

		List<WebElement> activeChallenges = driver.findElements(By.xpath("/div[text()='Featured Challenge']"));

		int actChallenges = activeChallenges.size();

		if (actChallenges > 0)

			return true;

		else

			return false;

	}

}
