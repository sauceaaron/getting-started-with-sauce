import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.*;

public class LocalTest
{
	RemoteWebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setup() throws MalformedURLException
	{
		URL url = new URL("http://localhost:4444/wd/hub");

		MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability("browserName", "chrome");

		driver = new RemoteWebDriver(url, capabilities);
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void testSauceDemo()
	{
		// OPEN HOME PAGE
		driver.get("https://www.saucedemo.com/");
		assertEquals(driver.getTitle(), "Swag Labs");

		// LOGIN
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("[value=LOGIN]")).click();

		// VERIFY ON PRODUCTS PAGE
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement productsHeading = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product_label")));

		assertTrue(productsHeading.isDisplayed());

		// CHECK FIRST PRODUCT
		WebElement firstItem = driver.findElement(By.cssSelector(".inventory_item:first-of-type"));
		String name = firstItem.findElement(By.cssSelector(".inventory_item_name")).getText();
		String price = firstItem.findElement(By.cssSelector(".inventory_item_price")).getText();

		assertTrue(name.contains("Backpack"));
		assertEquals(price, "$29.99");

		// CHECK LAST PRODUCT
		WebElement lastItem = driver.findElement(By.cssSelector(".inventory_item:last-of-type"));
		assertTrue(lastItem.getText().contains("Test.allTheThings()"));
	}

	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
}