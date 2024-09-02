package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) { 
		super(driver);
	}
	
	//Locators 
	
	@FindBy (xpath="//h2[normalize-space()='My Account']") WebElement msgHeading;
	@FindBy (xpath="//div[@class='list-group']//a[text()='Logout']") WebElement lnkLogout;
	
	//Actions 
	public boolean isMsgDisplayed() {
		try {
			return(msgHeading.isDisplayed());
		}
		catch(Exception e) {
				return false ;
			
		}
	}
	public void logoutclicked() {
		lnkLogout.click();
	}
}
