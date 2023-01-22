package seleniumFrameworkDesign.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumFrameworkDesign.abstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	WebDriver d1;

	public OrdersPage(WebDriver d1) {
		// sending driver to parent AbstractComponent
		super(d1);
		this.d1 = d1;
		PageFactory.initElements(d1, this);
	}

	// WebElements
	@FindBy(css = "table tr td:nth-child(3)")
	List<WebElement> orders_name_list;
	
	public boolean verifyOrderPresent(String desired_product_name) {
		boolean is_order_present = orders_name_list.stream().anyMatch(o -> o.getText().equalsIgnoreCase(desired_product_name));
		CartPage fg = new CartPage(d1);
		return is_order_present;
		
	}


}
