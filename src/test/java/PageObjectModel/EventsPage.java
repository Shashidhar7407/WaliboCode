package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EventsPage {
    WebDriver driver;

    public EventsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//a[@class='sc-kBuKLX kyKfdr'])[4]")
    WebElement event_Icon;

    @FindBy(xpath = "//div[@class='heading']")
    WebElement eventPageHeading;

    @FindBy(xpath = "//div[@class='sc-lgUZRr eapUXA']")
    WebElement event_Button;

    @FindBy(xpath = "(//div[@class='sc-coJghx fiunTb'])[1]")
    WebElement completed_Button;

    @FindBy(xpath = "//div[@class='cardWrapper']")
    WebElement live_Events_Cards;

    @FindBy(xpath = "//div[contains(@class, 'sc-bBAoNe eOfmES')]")
    List<WebElement> benefitContainers;

    @FindBy(xpath = "//div[@class='sc-bGXeph ioYlQo']")
    List<WebElement> benefitContainers1;

    // Locators for elements within each container
    private By titleLocator = By.xpath(".//div[contains(@class, 'titleCard')]");
    private By descriptionLocator = By.xpath(".//div[contains(@class, 'detailsCard')]");
    private By descriptionElementsLocator = By.xpath(".//div[contains(@class, 'sc-jYIdPM gdHLc')]");

    public WebElement eventIcon() {
        return event_Icon;
    }

    public WebElement EventPageHeading() {
        return eventPageHeading;
    }

    public WebElement EventButton() {
        return event_Button;
    }

    public WebElement completedButton() {
        return completed_Button;
    }

    public WebElement liveEventCards() {
        return live_Events_Cards;
    }

    public List<WebElement> getBenefitContainers() {
        return benefitContainers;
    }

    public List<WebElement> getBenefitContainers1() {
        return benefitContainers1;
    }

    public WebElement getTitleElement(WebElement container) {
        return container.findElement(titleLocator);
    }

    public WebElement getDescriptionElement(WebElement container) {
        return container.findElement(descriptionLocator);
    }

    public WebElement getDescriptionElements(WebElement container) {
        return container.findElement(descriptionElementsLocator);
    }
}
