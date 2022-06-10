package saucelab;

import org.testng.annotations.Test;

public class SauceTest extends Base {
	@Test
	public void doLogin() {
		driver.get("https://google.com");
		System.out.println(driver.getTitle());
	}

}
