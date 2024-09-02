package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC01_AccRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression","Master"})
 	public void Verify_AccRegistration() throws InterruptedException {
	 logger.info("*********TC01_AccRegistrationTest***********");
     try {
	 HomePage hp = new HomePage(super.driver);
	logger.info("*************Application Home Page Accessed  ****************");

	 hp.clickMyAccount();
	 logger.info("----My Account Clicked---------");
	 hp.clickRegister();
	 logger.info("----Register Clicked---------");

	 AccountRegistrationPage accreg = new AccountRegistrationPage(driver);
	 accreg.setFirstName(randomString().toUpperCase());
	 accreg.setLastName(randomString().toUpperCase());
	 accreg.setEmail(randomString()+"@gmail.com");
	 accreg.setTelephone(randomNumber());
	 String pwd = randomAlphanumeric();
	 accreg.setPassword(pwd);
	 accreg.setConfirmPwd(pwd);
	 Thread.sleep(3000);
	 accreg.clickCheckBox();
	 accreg.clickContinueBtn();
	 String msg =  accreg.msgReceived();
	 logger.info("----Verifying Expected message ---------");

	 Assert.assertEquals(msg, "Your Account Has Been Created!");
	}
	catch(Exception e) {
		logger.error("Test failed");
		logger.debug("Debug logs ...");
 }
	}
}