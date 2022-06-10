package saucelab;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebDriver.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Base2 {

	public static WebDriver driver;
	
	@Parameters({"browser","platform","version"})
	@BeforeMethod
	public void setup(String browserName, String platformName, String versionName, Method name )  {
		String methodName=name.getName();
		System.out.println("Browser name: " +browserName);
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
		cap.setCapability("platformName", "platformName");
		cap.setCapability("browserVersion", "versionName");

		
		//cap.setCapability("browserName", "chrome");
		
		if(browserName.equalsIgnoreCase("chrome")) {
		//WebDriver driver=WebDriverManager.chromedriver().capabilities(cap).create();
			WebDriverManager.chromedriver().setup();
			cap.setCapability(browserName, "chrome");
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
			//WebDriver driver=WebDriverManager.firefoxdriver().capabilities(cap).create();
			WebDriverManager.firefoxdriver().setup();
			cap.setCapability(browserName, "Firefox");
		}	
		//https://oauth-srikanish03-0fd77:0438615e-dd99-46fd-81b3-7cb570981c1d@ondemand.us-west-1.saucelabs.com:443/wd/hub
		try {
			//driver= new RemoteWebDriver(new URL("https://oauth-srikanish03-0fd77:0438615e-dd99-46fd-81b3-7cb570981c1d@ondemand.us-west-1.saucelabs.com:443/wd/hub"),cap);

			driver= new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"),cap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	@Test
	public void doLogin() {
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
	}
	
	@AfterMethod(alwaysRun=true)
    public void tearDown() {
		driver.quit();
	}
	}

