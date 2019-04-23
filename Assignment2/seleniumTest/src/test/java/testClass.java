import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class testClass {
	private WebDriver webDriver;
	private final static String WEB_DRIVER = "webdriver.chrome.driver";
	private final static String WEB_DRIVER_PATH = "c:\\Users\\mokro\\Downloads\\chromedriver.exe";

	@Before
	public void setup() {
		System.setProperty(WEB_DRIVER, WEB_DRIVER_PATH);
		webDriver = new ChromeDriver();
		webDriver.get("http://en.wikipedia.org");
	}

	@After
	public void close() {
		webDriver.close();
	}

	@Test
	public void testSeleniumNegativeWikipediaLogin() {
		webDriver.findElement(By.id("pt-login")).click();
		WebElement usernameField = webDriver.findElement(By.name("wpName"));
		WebElement passwordField = webDriver.findElement(By.name("wpPassword"));
		usernameField.sendKeys("*user");
		passwordField.sendKeys("1234");
		assertTrue(webDriver.findElements(By.className("error")).isEmpty());
		webDriver.findElement(By.id("wpLoginAttempt")).click();
		assertTrue(webDriver.findElement(By.className("error")).isDisplayed());
	}

	@Test
	public void testSeleniumPositiveWikipediaHelpPage() {
		WebElement helpField = webDriver.findElement(By.id("n-help"));
		helpField.findElement(By.tagName("a")).click();
		assertTrue(webDriver.getTitle().startsWith("Help:Contents"));
	}

	@Test
	public void testSeleniumPositiveWikipediaLanguageChange() {
		List<WebElement> languageFields = webDriver.findElements(By.className("interlanguage-link-target"));

		for (WebElement field : languageFields) {
			if (field.getAttribute("lang").equals("cs")) {
				field.click();
				break;
			}
		}

		assertTrue(webDriver.getCurrentUrl().equals("https://cs.wikipedia.org/wiki/Hlavn%C3%AD_strana"));
	}
}
