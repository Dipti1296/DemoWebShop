package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[@class='ico-logout']")
	WebElement logOutBtn;
	@FindBy(xpath="//div[normalize-space()='My account']")
	WebElement msgHeading;
	@FindBy(xpath="//a[@class='ico-logout']")
	WebElement btnLogout;

	public boolean isMyAccountPageExists()
	{
		try
		{
//			logOutBtn.click();
			return(logOutBtn.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		btnLogout.click();
	}
	
}
	
	
	
