package Com.QA.TestCases;
import Com.QA.TestUtils.CaptureLogs;


import org.apache.log4j.*;
import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Com.QA.Base.TestBase;
import Com.QA.PageObjects.HomePageObjects;
import Com.QA.PageObjects.LoginPageObjects;

public class LoginTestCase extends TestBase{
	LoginPageObjects loginpage;
	HomePageObjects  homepage;

	public LoginTestCase() throws IOException {
		super();
	}
	
	@BeforeTest
	public void setUp() throws IOException
	{
		Initialize();
		CaptureLogs.autoLogs("LoginTestCase").info("Opened a Browser");	
		loginpage = new LoginPageObjects();
	}
	
	@Test(priority=1)
	public void VerifyPageTitleTest(){
		String PageTitle=loginpage.VerifyPageTitle();
		CaptureLogs.autoLogs("VerifyPageTitleTest").info("Verifying Browser");	
		Assert.assertEquals(PageTitle,"Facebook – log in or sign up"); 
	}
	
	@Test(priority=2)
	public void LoginTest() throws Exception
	{
		homepage = loginpage.Login(Pro.getProperty("UN"), Pro.getProperty("PW"));
		String PageTitle=loginpage.VerifyPageTitle();
		CaptureLogs.autoLogs("VerifyPageTitleTest").info("Verifying Browser");	
		Assert.assertEquals(PageTitle,"Facbook – log in or sign up");
		Thread.sleep(10);
	}
	
	@AfterTest
	public void CloseMethod() throws IOException, InterruptedException
	{
	Thread.sleep(10);
	driver.close();
	System.out.println("after test");
	}
	

}
