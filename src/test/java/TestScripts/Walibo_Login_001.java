package TestScripts;

import org.testng.annotations.Test;
import java.time.Duration;
import java.time.LocalTime;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Walibo_Login_001 extends TestBase {

	@Test
	public void walibo_User_Login() throws InterruptedException {
		Thread.sleep(5000);
		WebElement usernameElement = driver.findElement(By.name("emailOrUsername"));
		usernameElement.click();
		usernameElement.sendKeys("chaitra.malli@woliba.io");
		Thread.sleep(2000);
		WebElement passwordElement = driver.findElement(By.name("loginPassword"));
		passwordElement.click();
		passwordElement.sendKeys("Chaitra@123");
		Thread.sleep(5000);
		WebElement loginButtonElement = driver.findElement(By.xpath("//button[contains(text(),'login')]"));
		jsExecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth',block: 'center'});",
				loginButtonElement);
		Thread.sleep(5000);
		loginButtonElement.click();
		Thread.sleep(5000);
		System.out.println(driver.getCurrentUrl());
		Thread.sleep(5000);
		String pageSource = driver.getPageSource();
		Thread.sleep(5000);
		LocalTime currentTime = LocalTime.now();
		String greetingMessage;
		if (currentTime.isAfter(LocalTime.of(0, 0)) && currentTime.isBefore(LocalTime.of(12, 0))) {
			greetingMessage = "Good Morning";
			System.out.println(greetingMessage);
		} else if (currentTime.isAfter(LocalTime.of(12, 0)) && currentTime.isBefore(LocalTime.of(18, 0))) {
			greetingMessage = "Good Afternoon";
			System.out.println(greetingMessage);
		} else {
			greetingMessage = "Good Evening";
			System.out.println(greetingMessage);
		}
		Thread.sleep(5000);

		if (pageSource.contains(greetingMessage)) {
			System.out.println("Text '" + greetingMessage + "' is present on the page.");
		} else {
			System.out.println("Text '" + greetingMessage + "' is not present on the page.");
		}

		WebElement greetingmessagElement = driver.findElement(By.xpath("//span[contains(text(),'Good,')]"));
		String messageString = greetingmessagElement.getText();
		if (greetingMessage == messageString) {
			System.out.println("Same");

		} else {
			System.out.println("False");
		}

		Thread.sleep(5000);
		System.out.println("Successfully done");
		driver.quit();
	}
}
