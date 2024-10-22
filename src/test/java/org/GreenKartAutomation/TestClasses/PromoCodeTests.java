package org.GreenKartAutomation.TestClasses;

import org.GreenKartAutomation.PageObject.Cart;
import org.GreenKartAutomation.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PromoCodeTests extends BaseTest {
	
	@Test(description = "Test to verify dicount for valid promo-code")
	public void ValidPromoCodeDiscount () throws InterruptedException {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews"};
		homePage.AddToCart(OrderItems);
		Cart handBag = homePage.CheckOut();
		handBag.EnterPromoCode("rahulshettyacademy");
		String promoResponse = handBag.ApplyCode();
		Assert.assertEquals(promoResponse, "Code applied ..!");
		Assert.assertEquals(handBag.DiscountOnPromoCode(), 10);
		Assert.assertEquals(handBag.AmountAfterDiscount(), handBag.TotalAmount()*0.9);
	}
	
	@Test(description = "Test to verify dicount for invalid promo-code")
	public void InvalidPromoCodeDiscount () throws InterruptedException {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews"};
		homePage.AddToCart(OrderItems);
		Cart handBag = homePage.CheckOut();
		handBag.EnterPromoCode("InvalidPromoCode");
		String promoResponse = handBag.ApplyCode();
		Assert.assertEquals(promoResponse, "Invalid code ..!");
		Assert.assertEquals(handBag.DiscountOnPromoCode(), 0);
		Assert.assertEquals(handBag.AmountAfterDiscount(), handBag.TotalAmount());
	}
	
	@Test(description = "Test to verify dicount for no promo-code")
	public void EmptyPromoCodeDiscount () throws InterruptedException {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews"};
		homePage.AddToCart(OrderItems);
		Cart handBag = homePage.CheckOut();
		String promoResponse = handBag.ApplyCode();
		Assert.assertEquals(promoResponse, "Empty code ..!");
		Assert.assertEquals(handBag.DiscountOnPromoCode(), 0);
		Assert.assertEquals(handBag.AmountAfterDiscount(), handBag.TotalAmount());
	}
	
	@Test(description = "Test to verify dicount for empty string promo-code")
	public void EmptyStringPromoCode () throws InterruptedException {
		String[] OrderItems = {"Tomato", "Brocolli", "Pears", "Cashews"};
		homePage.AddToCart(OrderItems);
		Cart handBag = homePage.CheckOut();
		handBag.EnterPromoCode("");
		String promoResponse = handBag.ApplyCode();
		Assert.assertEquals(promoResponse, "Empty code ..!");
		Assert.assertEquals(handBag.DiscountOnPromoCode(), 0);
		Assert.assertEquals(handBag.AmountAfterDiscount(), handBag.TotalAmount());
	}
}
