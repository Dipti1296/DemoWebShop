package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//input[@id='Email']")
	WebElement txtEmail;
	@FindBy(xpath="//input[@id='Password']")
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='RememberMe']")
	WebElement btnRememberMe;
	@FindBy(xpath="//input[@class='button-1 login-button']")
	WebElement btnLogIn;
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void clkRememberMe()
	{
		btnRememberMe.click();
	}
	public void clkLogIn()
	{
		btnLogIn.click();
	}

}
