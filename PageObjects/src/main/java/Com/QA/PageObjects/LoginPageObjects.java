package Com.QA.PageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Com.QA.Base.TestBase;

public class LoginPageObjects extends TestBase  {

	@FindBy(id="email")
	WebElement Username;
	
	@FindBy(id="pass")
	WebElement Password;
	
	@FindBy(xpath="//input[@value='Log In']")
	WebElement LoginButton;

	public LoginPageObjects() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public String VerifyPageTitle()
	{
		return driver.getTitle();
	}
	
	public HomePageObjects Login(String UN,String PW) throws IOException
	{
		Username.sendKeys(UN);
		Password.sendKeys(PW);
		LoginButton.click();
		
		return new HomePageObjects();
	}
	
}
