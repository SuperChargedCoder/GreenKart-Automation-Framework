package org.GreenKartAutomation.PageObject;

import org.GreenKartAutomation.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckOut extends AbstractComponents {
	WebDriver driver;

	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement checkBox;
	
	@FindBy(xpath = "//button[normalize-space()='Proceed']")
	WebElement proceedButton;
	
	@FindBy(xpath = "//div[@class='wrapperTwo']//div//select")
	WebElement country;
	
	@FindBy(css  = "span[class='errorAlert'] b")
	WebElement checkBox_Error;
	
	@FindBy(xpath = "//span[contains(text(),'Thank you, your order has been placed successfully')]")
	WebElement submitResponse;
	
	public void SelectTermCondition (){
		checkBox.click();
	}
	
	public void Country(String Nation) {
		Select s = new Select(country);
		s.selectByVisibleText(Nation);
	}
	
	public String CheckBoxError() {
		WebElement boxError = WaitToAppear(checkBox_Error);
		return boxError.getText();
	}
	
	public WebElement BuyWebElement () {
		return proceedButton;
	}
	
	public void BuyWithoutTermCondition () {
		proceedButton.click();
	}
	
	public String Buy() {
		proceedButton.click();
		WebElement message = WaitToAppear(submitResponse);
		return message.getText();
	}
}
