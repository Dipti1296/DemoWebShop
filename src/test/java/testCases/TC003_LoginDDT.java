package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseCase;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseCase
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")  //getting dataProvider from different class
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("---Starting TC003_LoinDDT---");
		try 
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickLogin();
		logger.info("...Clicked on Login link..");
				
		//LogInPage
		LoginPage  lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clkLogIn();
		logger.info("...LogIn Successful..");
				
		//MyAccountPage
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		/*data is valid=login success-test pass-logout
				     =login failed-test fail
		data is invalid=login success-testfail-logout
		             =login failed-test pass*/
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				Assert.assertTrue(true);  //valid data-test pass-logout
				macc.clickLogout();
			}
			else
			{
				Assert.assertTrue(false);  //valid data-test fail-test failed
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);  //invalid data-test pass-test failed-logout
			}
			else
			{
				Assert.assertTrue(true);   //invalid data - test fail-test passed
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail(e.getMessage());
			//Assert.fail();
		}
		logger.info("---Finished TC003_LoginDDT---");
	}
}
		
	



		
				

