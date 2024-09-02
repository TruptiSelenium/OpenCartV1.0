package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	//Constructor
	public HomePage(WebDriver driver){
		super(driver);
	}
//Locators  
	@FindBy (xpath="//span[normalize-space()='My Account']")WebElement lnkMyAcc ;
	@FindBy (xpath="//a[text()='Register']")WebElement lnkRegister;
	@FindBy (xpath="//a[text()='Login']") WebElement lnkLogin;
//Actions
	
	public void clickMyAccount() {
		lnkMyAcc.click();
	}
	public void clickRegister() {
		lnkRegister.click();
	}
	public void clickLogin() {
		lnkLogin.click();
		}
}
