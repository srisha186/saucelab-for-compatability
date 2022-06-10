package saucelab;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebDriver.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Base {
//execute from testng.xml in runners folder for single browser
	public static WebDriver driver;
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser, Method name)  {
		String methodName=name.getName();
		System.out.println("Browser name: " +browser);
		MutableCapabilities sauceOptions = new MutableCapabilities();
	    sauceOptions.setCapability("name", methodName);
		sauceOptions.setCapability("build", "Java-w3c-Examples");
		sauceOptions.setCapability("seleniumVersion", "3.141.59");

		sauceOptions.setCapability("username", "oauth-srikanish03-0fd77");
		sauceOptions.setCapability("accessKey", "0438615e-dd99-46fd-81b3-7cb570981c1d");
		sauceOptions.setCapability("tags", "w3c-chrome-tests");
		//sauceOptions.setCapability("sauce:options", sauceOptions);


		//caps.setCapability("sauce:options", sauceOptions);
		
		DesiredCapabilities cap =  new DesiredCapabilities();
		cap.setCapability("sauce:options", sauceOptions);
		cap.setCapability("platformName", "Windows 10");
		cap.setCapability("browserVersion", "latest");
	//	Map<String, Object> sauceOptions = new HashMap<>();

		cap.setCapability("browserName", "chrome");
		
		/*if(browser.equalsIgnoreCase("chrome")) {
		//WebDriver driver=WebDriverManager.chromedriver().capabilities(cap).create();
			WebDriverManager.chromedriver().setup();
			cap.setCapability(browser, "chrome");
		}
		else if(browser.equalsIgnoreCase("Firefox")) {
			//FirefoxOptions browserOptions = new FirefoxOptions();

			//WebDriver driver=WebDriverManager.firefoxdriver().capabilities(cap).create();
			WebDriverManager.firefoxdriver().setup();
			cap.setCapability(browser, "Firefox");
			//cap.setCapability(browserOptions, "Firefox");

		}	*/
		//https://oauth-srikanish03-0fd77:0438615e-dd99-46fd-81b3-7cb570981c1d@ondemand.us-west-1.saucelabs.com:443/wd/hub
		try {
		//	driver= new RemoteWebDriver(new URL("https://oauth-srikanish03-0fd77:0438615e-dd99-46fd-81b3-7cb570981c1d@ondemand.us-west-1.saucelabs.com:443/wd/hub"),cap);

		driver= new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	@AfterMethod(alwaysRun=true)
    public void tearDown() {
		driver.quit();
	}
	}

