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

public class OrderConfirmationPage extends AbstractComponent {

	WebDriver d1;

	public OrderConfirmationPage(WebDriver d1) {
		// sending driver to parent AbstractComponent
		super(d1);
		this.d1 = d1;
		PageFactory.initElements(d1, this);
	}

	// WebElements
	@FindBy(css = "h1")
	WebElement thankyou_message;
	
	public String get_confirmation_message() {
		return thankyou_message.getText();
	}


}
