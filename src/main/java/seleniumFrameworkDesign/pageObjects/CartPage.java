package seleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumFrameworkDesign.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver d1;

	public CartPage(WebDriver d1) {
		// sending driver to parent AbstractComponent
		super(d1);
		this.d1 = d1;
		PageFactory.initElements(d1, this);
	}
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cart_items;
	
	@FindBy (css =".subtotal button")
	private WebElement checkout_button;
	
	public Boolean verifyProductAddedInCart(String desired_product_name) {
		Boolean is_cart_item_present = cart_items.stream().anyMatch(cart_item -> cart_item.getText().equalsIgnoreCase(desired_product_name));
		return is_cart_item_present;
	}
	
	public OrderPaymentPage checkOut() {
		checkout_button.click();
		OrderPaymentPage opp = new OrderPaymentPage(d1);
		return opp;
	}

}
