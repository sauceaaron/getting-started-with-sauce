import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class SauceDemoTests
{
	RemoteWebDriver driver;

	@Test
	public void openHomePage() throws MalformedURLException
	{
		URL url = new URL("https://aaron-evans:0208c2f6-e0f8-4497-93f0-8707e2a6abad@ondemand.saucelabs.com:443/wd/hub");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");

		driver = new RemoteWebDriver(url, capabilities);
		driver.get("https://www.saucedemo.com/");

		assertEquals(driver.getTitle(), "Swag Labs");

		driver.quit();
	}
}
