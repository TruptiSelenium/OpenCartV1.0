package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver){
		super(driver);
	}
	
	//Locators 
	
	@FindBy (xpath="//input[@id='input-firstname']") WebElement txtFirstname;
	@FindBy (xpath="//input[@id='input-lastname']") WebElement txtLastname;
	@FindBy (xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy (xpath="//input[@id='input-telephone']") WebElement txtTele;
	@FindBy (xpath="//input[@id='input-password']") WebElement txtPwd;
	@FindBy (xpath="//input[@id='input-confirm']") WebElement  txtConfirmPwd;

	@FindBy (xpath="//input[@name='agree']") WebElement chkConfirmPolicy;
	@FindBy (xpath="//input[@value='Continue']") WebElement btnContinue;
	@FindBy (xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msg;
	//Actions
	public void setFirstName(String fname) {
		txtFirstname.sendKeys(fname);
	}
	public void setLastName(String lname) {
		txtLastname.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String tele) {
		txtTele.sendKeys(tele);
	}
	public void setPassword(String pwd) {
		txtPwd.sendKeys(pwd);
	}
	public void setConfirmPwd(String repwd) {
		txtConfirmPwd.sendKeys(repwd);
	}
	public void clickContinueBtn() {
		btnContinue.click();
		}
	
	public void clickCheckBox() {
		chkConfirmPolicy.click();
		}
	public String  msgReceived() {
		try {
			return( msg.getText());
		}
		catch(Exception e ){
			return (e.getMessage());
			
		}
		}
	
}
