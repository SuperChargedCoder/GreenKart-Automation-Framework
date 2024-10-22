package org.GreenKartAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.GreenKartAutomation.PageObject.LandingPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage homePage;
	
	public WebDriver LaunchBrowser () throws IOException {
		String automationBrowser = getGlobalProperties("browser");
		if (automationBrowser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (automationBrowser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	public String getGlobalProperties(String key) throws IOException {
		Properties prop = new Properties();
		String globalPropPath = System.getProperty("user.dir") + "\\src\\main\\java\\org\\GreenKartAutomation\\Resources\\GlobaData.properties";
		try {
			FileInputStream file = new FileInputStream(globalPropPath);
			prop.load(file);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find global properties directory location");
		}
		return prop.getProperty(key);
	}
	
	public String TakeScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File screenshotSource = ts.getScreenshotAs(OutputType.FILE);
		File screenshotDestination = new File(System.getProperty("user.dir") + "\\ScreenCapture\\" + testCaseName + ".png");
		FileUtils.copyFile(screenshotSource,screenshotDestination);
		return System.getProperty("user.dir") + "\\ScreenCapture\\" + testCaseName + ".png";
	}
	
	public WebElement WaitToAppear (WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		WebElement visible = wait.until(ExpectedConditions.visibilityOf(element));
		return visible;
	}
	
	@BeforeMethod (alwaysRun = true)
	public void LaunchGreenkart() throws IOException {
		driver = LaunchBrowser();
		driver.get(getGlobalProperties("url").toString());
		homePage = new LandingPage(driver);
	}
	
	
	@AfterMethod (alwaysRun = true)
	public void CloseBrowser() {
		driver.quit();
	}
}
