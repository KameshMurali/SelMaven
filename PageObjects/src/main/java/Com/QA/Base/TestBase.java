package Com.QA.Base;

import static Com.QA.Base.TestBase.Pro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Com.QA.TestUtils.TestUtils;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties Pro;
	
	public TestBase() throws IOException
	{
		Pro = new Properties();
		FileInputStream FI = new FileInputStream("E:\\Work\\Automation\\Selenium\\Workspace\\PageObjects\\src\\main\\java\\Com\\QA\\EnvironmentUtilities\\EnnVar.properties");
		Pro.load(FI);
		
	}
	
	@SuppressWarnings("deprecation")
	public static void Initialize()
	{
		String Browsername = Pro.getProperty("Browser");
		
		if(Browsername.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(Browsername.equalsIgnoreCase("Chrome"))
		{
			System.setProperty(Pro.getProperty("Chrome_Key"),Pro.getProperty("Chrome_Path"));
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("test-type");
	        options.addArguments("start-maximized");
	        options.setAcceptInsecureCerts(true);
	        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
	        options.addArguments("disable-infobars");
	        capabilities.setCapability("chrome.binary", Pro.getProperty("Chrome_Path"));
	        options.merge(capabilities);

			driver = new ChromeDriver(options);
		}
		else if(Browsername.equalsIgnoreCase("IE"))
		{
			System.setProperty(Pro.getProperty("IE_Key"),Pro.getProperty("IEDriver_Path"));
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			   capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
			   true); 
			   	driver = new InternetExplorerDriver(capabilities);
		}
			
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(Pro.getProperty("AppUrl"));
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PageLoadTime, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(TestUtils.ImplicityTime, TimeUnit.SECONDS);
		
	}

}
