package TestScripts;

import java.time.LocalTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Walibo_HomePage_002 extends Walibo_Login_001{
  @Test
  public void HomePage() throws InterruptedException {
//	  walibo_User_Login();
	  String pageSource = driver.getPageSource();
	  Thread.sleep(5000);
	  LocalTime currentTime=LocalTime.now();
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
	  
	WebElement greetingmessagElement=driver.findElement(By.xpath("//span[contains(text(),'Good,')]"));
	  String messageString=greetingmessagElement.getText();
	  if(greetingMessage == messageString) {
		  System.out.println("Same");
		  
	  }
	  else {
		  System.out.println("False");
	  }
	  
	  Thread.sleep(5000);
	  System.out.println("Successfully done");
  }
}
