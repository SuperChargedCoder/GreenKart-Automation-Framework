package org.GreenKartAutomation.TestClasses;

import org.GreenKartAutomation.PageObject.TopDeals;
import org.GreenKartAutomation.TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TopDealsTests extends BaseTest {

	@Test(description = "Test to verify actual price is less then discount price")
	public void Discounting() {
		TopDeals topDeals = homePage.NevigateTopDeal();
		homePage.SwitchWindowDriver();
		topDeals.SelectPageSize(20);
		Object[][] itemDetails = topDeals.ItemDetails();
		SoftAssert a = new SoftAssert();
		for (int i = 0; i < itemDetails.length; i++) {
			String veg = (String) itemDetails[i][0];
			int price = (int) itemDetails[i][1];
			int discountedPrice = (int) itemDetails[i][2];
			a.assertTrue(ComparePrice(price, discountedPrice), veg.concat(" have discounting issue..."));
		}
		a.assertAll();
	}

	public boolean ComparePrice(int price, int discountedPrice) {
		if (discountedPrice < price) {
			return true;
		} else {
			return false;
		}
	}

	@Test(description = "Test to verify dynamic page size")
	public void PageSize() {
		TopDeals topDeals = homePage.NevigateTopDeal();
		homePage.SwitchWindowDriver();
		topDeals.SelectPageSize(5);
		Assert.assertTrue(topDeals.DisplayVegCount() <= 5);
		topDeals.SelectPageSize(10);
		Assert.assertTrue(topDeals.DisplayVegCount() <= 10);
		topDeals.SelectPageSize(20);
		Assert.assertTrue(topDeals.DisplayVegCount() <= 20);
	}
}
