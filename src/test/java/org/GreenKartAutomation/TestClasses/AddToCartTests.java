package org.GreenKartAutomation.TestClasses;

import org.GreenKartAutomation.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTests extends BaseTest {
	
	@Test(description = "Test to verify add to cart functionality")
	public void AddingToCart () {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews"};
		homePage.AddToCart(OrderItems);
		int count = homePage.CartItemCount();
		Assert.assertEquals(count, OrderItems.length);
	}
	
	@Test(description = "Test to verify cart items count")
	public void ItemCount () {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews", "Pumpkin", "Orange", "Strawberry"};
		homePage.AddToCart(OrderItems);
		int count = homePage.CartItemCount();
		Assert.assertEquals(count, OrderItems.length);
	}
	
	@Test(description = "Test to verify emtpy order")
	public void EmptyCartCheckout () {
		WebElement checkOutButton = homePage.CheckOutWithEmptyCart();
		boolean buttonCheck = checkOutButton.isEnabled();
		Assert.assertFalse(buttonCheck);
	}
	
	@Test(description = "Test to verify text for empty cart")
	public void EmptyCartText () throws InterruptedException {
		String cartText = homePage.EmptyCartText();
		Assert.assertEquals(cartText, "You cart is empty!");
	}
	
	@Test(description = "Test to verify cart items on page refresh")
	public void RefreshCart () throws InterruptedException {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews"};
		homePage.AddToCart(OrderItems);
		homePage.RefreshPage();
		int count = homePage.CartItemCount();
		Thread.sleep(1500);
		Assert.assertEquals(count, OrderItems.length);
	}
	
	@Test(description = "Test to verify page title")
	public void PageTitle () {
		Assert.assertEquals(homePage.PageTitle(), "GreenKart - veg and fruits kart");
	}
	
	@Test(description = "Test to verify footer text")
	public void FooterText () {
		Assert.assertEquals(homePage.FooterText(), "© 2019 GreenKart - buy veg and fruits online");
	}
}
