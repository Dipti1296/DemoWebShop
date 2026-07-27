package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseCase;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseCase
{
	//public WebDriver driver;
	
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("***Starting TC001 AcountRegistrationTest***");
		try {
		HomePage hp=new HomePage(driver);
		
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer details");
		regpage.clickGender();
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.clickRegister();
		//Thread.sleep(5000);
		logger.info("Validating expected message..");
		String confmsg= regpage.getConfirmationmsg();
		if(confmsg.equals("Your registration completed"))
		{
			logger.debug("Debug logs");
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed..");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg,"Your registration completed!!");
		}
		catch(Exception e)
		{
			
			Assert.fail();
//			logger.info("***Finished TC001 AcountRegistrationTest***");
		}
		logger.info("***Finished TC001 AcountRegistrationTest***");
	}
	
}
