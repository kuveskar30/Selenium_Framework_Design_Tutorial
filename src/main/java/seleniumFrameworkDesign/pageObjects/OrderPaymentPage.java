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

public class OrderPaymentPage extends AbstractComponent {

	WebDriver d1;

	public OrderPaymentPage(WebDriver d1) {
		// sending driver to parent AbstractComponent
		super(d1);
		this.d1 = d1;
		PageFactory.initElements(d1, this);
	}

	// WebElements
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement select_country_textbox;
	// it will show available countries with given input
	@FindBy(xpath = "//section/button/span")
	List<WebElement> matched_countries;
	
	@FindBy(css = ".actions a")
	WebElement place_order;

	// By locators
	By matched_countries_locator = By.xpath("//section/button/span");

	public void selectCountry(String desired_country, String keys_to_enter_in_textbox) {
		select_country_textbox.sendKeys(keys_to_enter_in_textbox);
		waitForElementToAppear(matched_countries_locator, 5);
		List<WebElement> entered_country = matched_countries.stream().filter(country -> country.getText().equalsIgnoreCase(desired_country))
				.collect(Collectors.toList());
		entered_country.get(0).click();
	}
	
	public OrderConfirmationPage placeOrder() {
		place_order.click();
		OrderConfirmationPage ocp = new OrderConfirmationPage(d1);
		return ocp;
	}

}
