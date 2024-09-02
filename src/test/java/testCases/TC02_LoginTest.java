package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC02_LoginTest extends BaseClass{
	@Test(groups= {"Sanity","Master"})
	public void verifyLogin() throws IOException {
		logger.info("************Starting TC02_LoginTest**************");
		try {
			//Home Page  
		logger.info("*************Application Home Page Accessed  ****************");

		HomePage hp = new HomePage(super.driver);
		hp.clickMyAccount();
		logger.info("*************Click MyAccount link  ****************");

		hp.clickLogin();
		logger.info("*************Click on Login link  ****************");

		//Login Page 
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		logger.info("*************Email entered on Login page   ****************");

		lp.setPwd(p.getProperty("pwd"));

		logger.info("*************password entered on Login page   ****************");

		lp.clickLogin();
		//My Account Page 
		MyAccountPage mp = new MyAccountPage(driver);
		boolean msg = mp.isMsgDisplayed();

			Assert.assertEquals(msg, true,"Login TC failed");
		
		}
		catch(Exception e ) {
			Assert.fail();
		}
		
		logger.info("************End  TC02_LoginTest**************");

	}

}
