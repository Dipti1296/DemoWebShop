package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//a[normalize-space()='Register']")    //Register
	WebElement lnkRegister;
	@FindBy(xpath="//a[normalize-space()='Log in']")     //Login
	WebElement lnkLogin;
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
	
}

