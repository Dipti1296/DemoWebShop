package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseCase;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseCase
{
	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("...Starting TC002 LoginTest...");
		
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickLogin();
		logger.info("...Clicked on Login link..");
		
		//LogInPage
		LoginPage  lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clkLogIn();
		logger.info("...Clicked on login..");
		
		//MyAccountPage
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true,"LogIn failed");
		Assert.assertTrue(targetPage);
		logger.info("...My Account Page Exists...");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("...Finished TC002 LoginTest...");
	}
	
}
		//Run testng.xml