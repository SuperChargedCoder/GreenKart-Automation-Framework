package org.GreenKartAutomation.PageObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.GreenKartAutomation.AbstractComponents.AbstractComponents;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (className = "product-name")
	List<WebElement> availableProductsWebElement;
	
	@FindBy (css = "div[class='product-action'] button")
	List<WebElement> addToCartButtons;
	
	@FindBy(xpath = "//img[@alt='Cart']")
	public WebElement cartIcon;
	
	@FindBy(xpath = "//div[@class='cart-preview active']//div//div//h2[contains(text(),'You cart is empty!')]")
	WebElement emptyCartText;
	
	@FindBy(xpath = "//button[normalize-space()='PROCEED TO CHECKOUT']")
	WebElement checkOutButton;
	
	@FindBy(xpath = "//tbody/tr[1]/td[3]/strong[1]")
	WebElement cartItemCount;
	
	@FindBy(xpath = "//a[normalize-space()='Top Deals']")
	WebElement topDeal;
	
	@FindBy(xpath = "//footer/p")
	WebElement footer;
	
	@FindBy(xpath = "//input[@class='search-keyword']")
	WebElement searchBox;
	
	@FindBy(css = "div[class='no-results'] h2")
	WebElement errorProduct;
	
	public void AddToCart(String[] cart) {
		List<String> vegDemand = new ArrayList<>(Arrays.asList(cart));
		ArrayList<String> availableProducs = GetAvailavbleProductName(driver);
		for (int i = 0; i < vegDemand.size(); i++) {
			if (availableProducs.contains(vegDemand.get(i))) {
				int j = availableProducs.indexOf(vegDemand.get(i));
				try {
					WebElement elementToClick = addToCartButtons.get(j);
					scrollIntoView(elementToClick);
					WebElement clickableElement = WaitToAppear(elementToClick);
					clickableElement.click();
				} catch (Exception e) {
					// If the regular click fails, use JavaScript to click the element
					System.out.println("Click intercepted, trying JavaScript click for " + vegDemand.get(i));
					WebElement elementToClick = addToCartButtons.get(j);
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].click();", elementToClick);
				}
			}
		}
	}

	public ArrayList<String> GetAvailavbleProductName(WebDriver driver) {
		ArrayList<String> productList = new ArrayList<>();
		for (WebElement product : availableProductsWebElement) {
			String name = product.getText().replace(" ", "").split("-")[0];
			productList.add(name);
		}
		return productList;
	}
	
	public Cart CheckOut() throws InterruptedException {
		cartIcon.click();
		WebElement checkout = WaitToAppear(checkOutButton);
		Thread.sleep(1500);
		checkout.click();
		Cart c = new Cart(driver);
		return c;
	}
	
	public WebElement CheckOutWithEmptyCart() {
		cartIcon.click();
		return checkOutButton;
	}
	
	public int CartItemCount () {
		return Integer.valueOf(cartItemCount.getText().toString());
	}
	
	public String PageTitle() {
		return driver.getTitle().toString();
	}
	
	public TopDeals NevigateTopDeal() {
		TopDeals t = new TopDeals(driver);
		topDeal.click();
		return t;
	}
	
	public String EmptyCartText () throws InterruptedException {
		cartIcon.click();
		WaitToAppear(checkOutButton);
		Thread.sleep(1500);
		return emptyCartText.getText().toString();
	}
	
	public void RefreshPage () {
		driver.navigate().refresh();
	}
	
	public String FooterText () {
		return footer.getText().toString();
	}
	
	public String ErrorProductSearchText () {
		searchBox.sendKeys("random");
		WebElement a = WaitToAppear(errorProduct);
		return a.getText().toString();
	}
}
