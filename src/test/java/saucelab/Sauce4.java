package saucelab;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.TestException;

public class Sauce4 {
    protected RemoteWebDriver driver;

	 @BeforeMethod
	    public <TestInfo> void setup(TestInfo testInfo) throws MalformedURLException  {
		 ChromeOptions browserOptions = new ChromeOptions();
	//	 browserOptions.setPlatformName("Windows 10");
	// browserOptions.setBrowserVersion("latest");
		 Map<String, Object> sauceOptions = new HashMap<String, Object>();
		 browserOptions.setCapability("sauce:options", sauceOptions);


	        sauceOptions.put("username", System.getenv("oauth-srikanish03-0fd77"));
	        sauceOptions.put("accessKey", System.getenv("0438615e-dd99-46fd-81b3-7cb570981c1d"));
	      //  sauceOptions.put("name", testInfo.getDisplayName());

	        URL url = new URL("ondemand.us-west-1.saucelabs.com:443/wd/hub");

	        driver = new RemoteWebDriver(url, browserOptions);
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
