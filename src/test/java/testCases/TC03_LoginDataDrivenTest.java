package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;



public class TC03_LoginDataDrivenTest extends BaseClass {
	//here we are adding dataprovider parameter and the class where this method is present
		@Test(dataProvider="Logindata", dataProviderClass=DataProviders.class, groups="Datadriven")
		public void verifyLoginDDT(String email,String pwd,String expresult) throws IOException {
			logger.info("************Starting TC03_LoginDataDrivenTest**************");
try {
			//Home Page  
			HomePage hp = new HomePage(super.driver);
			hp.clickMyAccount();
			hp.clickLogin();

			//Login Page 
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			logger.info("*************Email entered on Login page   ****************");

			lp.setPwd(pwd);
			logger.info("*************password entered on Login page   ****************");
			lp.clickLogin();
			//My Account Page 
			MyAccountPage mp = new MyAccountPage(driver);
			boolean msg = mp.isMsgDisplayed();
			
			/* login data is valid - login success - TC pass  -logout 
			 * login data is valid - login fail - TC fail

			 * login data is invalid - login success - TC fail  -logout 
			 * login data is invalid - login fail - TC pass 
			 */
			
			if(expresult.equalsIgnoreCase("valid")) { 
				if(msg==true) {
					mp.logoutclicked();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
			if(expresult.equalsIgnoreCase("invalid")) {
				if(msg==true) {
					mp.logoutclicked();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e ) {
		Assert.fail();
		}
			logger.info("************Finished TC03_LoginDataDrivenTest**************");
			
	}
}
