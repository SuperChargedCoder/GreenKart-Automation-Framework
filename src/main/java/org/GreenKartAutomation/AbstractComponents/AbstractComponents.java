package org.GreenKartAutomation.AbstractComponents;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents (WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement WaitToAppear (WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		WebElement visible = wait.until(ExpectedConditions.visibilityOf(element));
		return visible;
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public WebDriver SwitchWindowDriver () {
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();
		String greenKart = it.next();
		String topDeals = it.next();
		driver.switchTo().window(topDeals);
		return driver;
	}
}
