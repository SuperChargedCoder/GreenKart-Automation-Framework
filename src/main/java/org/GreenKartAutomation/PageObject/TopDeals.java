package org.GreenKartAutomation.PageObject;

import java.util.List;

import org.GreenKartAutomation.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TopDeals extends AbstractComponents {

	WebDriver driver;

	public TopDeals(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "select#page-menu")
	WebElement pageSize;

	@FindBy(xpath = "//tbody/tr")
	List<WebElement> vegDetails;

	public int DisplayVegCount() {
		int size = vegDetails.size();
		return size;
	}

	public Object[][] ItemDetails() {
		Object[][] details = new Object[vegDetails.size()][3];
		try {
			for (int i = 1; i <= DisplayVegCount(); i++) {
				String fruitName = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[1]")).getText();
				int price = Integer.valueOf(driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText());
				int dPrice = Integer.valueOf(driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]")).getText());
				details[i - 1][0] = fruitName;
				details[i - 1][1] = price;
				details[i - 1][2] = dPrice;
			}
		} catch (Exception e) {
			System.out.println("Error in getting Item details...");
		}
		return details;
	}

	public void SelectPageSize(int size) {
		Select s = new Select(pageSize);
		s.selectByVisibleText(String.valueOf(size));
	}
}
