package org.GreenKartAutomation.PageObject;

import java.util.List;

import org.GreenKartAutomation.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart extends AbstractComponents {
	
	WebDriver driver;
	
	public Cart (WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath = "//input[@placeholder='Enter promo code']")
	WebElement promocode_textbox;
	
	@FindBy (xpath = "//button[@class='promoBtn']")
	WebElement promocode_apply;
	
	@FindBy (xpath = "//span[@class='promoInfo']")
	WebElement promocodeResponse;
	
	@FindBy (xpath = "//b[contains(text(),'No. of Items')]")
	WebElement cart_itemsCount;
	
	@FindBy (xpath = "//span[@class='totAmt']")
	WebElement totalAmount;
	
	@FindBy (xpath = "//span[@class='discountPerc']")
	WebElement discount_percentage;
	
	@FindBy (xpath = "//span[@class='discountAmt']")
	WebElement discountedAmount;
	
	@FindBy (xpath = "//button[normalize-space()='Place Order']")
	WebElement placeOrder;
	
	@FindBy (xpath = "//p[@class='product-name']")
	List<WebElement> cartItems;
	
	public void EnterPromoCode (String code) {
		WaitToAppear(promocode_textbox);
		promocode_textbox.sendKeys(code);
	}
	
	public String ApplyCode() {
		promocode_apply.click();
		WebElement cupponResponse = WaitToAppear(promocodeResponse);
		return cupponResponse.getText().toString();
	}
	
	public int ItemCount() {
		return Integer.parseInt(cart_itemsCount.getText().toString());
	}
	
	public double TotalAmount() {
		return Double.parseDouble(totalAmount.getText().toString());
	}
	
	public int DiscountOnPromoCode() {
		int discont = Integer.parseInt(discount_percentage.getText().split("%")[0]);
		return discont;
	}
	
	public double AmountAfterDiscount() {
		return Double.parseDouble(discountedAmount.getText().toString());
	}
	
	public CheckOut PlaceOrder() {
		placeOrder.click();
		CheckOut c = new CheckOut(driver);
		return c;
	}
}
