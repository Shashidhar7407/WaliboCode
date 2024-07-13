package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "emailOrUsername")
    WebElement login_UserName;

    @FindBy(name = "loginPassword")
    WebElement login_Password;

    @FindBy(xpath = "//button[contains(text(),'login')]")
    WebElement login_Button;

    @FindBy(className = "time")
    WebElement greeting_Message_Homepage;

    @FindBy(className = "name")
    WebElement greeting_UserName_HomePage;

    @FindBy(xpath = "//img[@alt='logo']")
    WebElement walibo_Logo_Image;

    public WebElement Login_UserName() {
        return login_UserName;
    }

    public WebElement Login_Password() {
        return login_Password;
    }

    public WebElement Login_Button() {
        return login_Button;
    }

    public WebElement GreetingMessageHomepage() {
        return greeting_Message_Homepage;
    }

    public WebElement GreetingUserNameHomepage() {
        return greeting_UserName_HomePage;
    }

    public WebElement walibologoImage() {
        return walibo_Logo_Image;
    }
}
