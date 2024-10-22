package org.GreenKartAutomation.TestClasses;

import org.GreenKartAutomation.PageObject.Cart;
import org.GreenKartAutomation.PageObject.CheckOut;
import org.GreenKartAutomation.TestComponents.BaseTest;
import org.GreenKartAutomation.TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseOrderTests extends BaseTest {
	
	@Test(description = "Test to verify ordering without T&C")
	public void PurchaseWithoutTermCondition () throws InterruptedException{
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews", "Pumpkin", "Orange", "Strawberry"};
		homePage.AddToCart(OrderItems);
		Cart handBag = homePage.CheckOut();
		handBag.EnterPromoCode("rahulshettyacademy");
		handBag.ApplyCode();
		CheckOut placeOrder = handBag.PlaceOrder();
		placeOrder.BuyWithoutTermCondition();
		String error = placeOrder.CheckBoxError();
		Assert.assertEquals(error, "Please accept Terms & Conditions - Required");
	}
	
	@Test(description = "Test to verify ordering without address")
	public void PurchaseWithoutCountry () throws InterruptedException {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews", "Pumpkin", "Orange", "Strawberry"};
		homePage.AddToCart(OrderItems);
		Cart handBag = homePage.CheckOut();
		handBag.EnterPromoCode("rahulshettyacademy");
		handBag.ApplyCode();
		CheckOut placeOrder = handBag.PlaceOrder();
		Assert.assertFalse(placeOrder.BuyWebElement().isEnabled());
	}
	
	@Test(description = "Test to verify empty order")
	public void EmptyCartPurchase () throws InterruptedException {
		Cart handBag = homePage.CheckOut();
		handBag.EnterPromoCode("rahulshettyacademy");
		handBag.ApplyCode();
		CheckOut placeOrder = handBag.PlaceOrder();
		placeOrder.Country("India");
		placeOrder.SelectTermCondition();
		Assert.assertFalse(placeOrder.BuyWebElement().isEnabled());
	}
	
	@Test(description = "Test to verify auto return to home page after successful order", retryAnalyzer = Retry.class)
	public void AutoReturnHome () throws InterruptedException {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews", "Pumpkin", "Orange", "Strawberry"};
		homePage.AddToCart(OrderItems);
		Cart handBag = homePage.CheckOut();
		handBag.EnterPromoCode("rahulshettyacademy");
		handBag.ApplyCode();
		CheckOut placeOrder = handBag.PlaceOrder();
		placeOrder.Country("India");
		placeOrder.SelectTermCondition();
		placeOrder.Buy();
		Thread.sleep(2000);
		Assert.assertTrue(homePage.cartIcon.isDisplayed());
	}
	
	@Test(description = "Test to verify plcing order with valid details")
	public void PositivePurchase () throws InterruptedException {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews", "Pumpkin", "Orange", "Strawberry"};
		homePage.AddToCart(OrderItems);
		Cart handBag = homePage.CheckOut();
		handBag.EnterPromoCode("rahulshettyacademy");
		handBag.ApplyCode();
		CheckOut placeOrder = handBag.PlaceOrder();
		placeOrder.Country("India");
		placeOrder.SelectTermCondition();
		String checkOutText = placeOrder.Buy();
		String expectedText = "Thank you, your order has been placed successfully\r\n"
				+ "You'll be redirected to Home page shortly!!";
		Assert.assertEquals(checkOutText.replaceAll("\\s+", ""), expectedText.replaceAll("\\s+", ""));
	}
}
