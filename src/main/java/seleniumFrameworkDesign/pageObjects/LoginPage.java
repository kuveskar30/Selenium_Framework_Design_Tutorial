package seleniumFrameworkDesign.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameworkDesign.abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
// locators belong to login page will come here

	WebDriver d1;

	public LoginPage(WebDriver d1) {
		//sending driver to parent AbstractComponent
		super(d1);
		// initialization
		this.d1 = d1;
		//PageFactory helps to improve readability of code
		// it takes searchContext and page in which WebElements to be used as argument
		PageFactory.initElements(d1, this);
	}

//	WebElement user_email = d1.findElement(By.id("userEmail"));

	// PageFactory : alternate way of defining WebElement

	@FindBy(id = "userEmail")
	WebElement user_email;

	@FindBy(id = "userPassword")
	WebElement user_password;

	@FindBy(id = "login")
	WebElement login_button;
	
	@FindBy(css = "div[class*='ng-trigger-flyInOut']")
	WebElement error_message;
	
//	@FindBy(css = ".card")
//	WebElement product_cards;
	
	public ProductCatalogue loginToApplication(String email, String Password) {
		user_email.sendKeys(email);
		user_password.sendKeys(Password);
		waitForElementToBeClickable(login_button,10);
		login_button.click();
//		waitForElementToAppear(product_cards, 10);
		ProductCatalogue product_catalouge = new ProductCatalogue(d1);
		return product_catalouge;
	}

	//I think this method should not come in Login page object
	//this step is done before visiting login page, after this step login page is displayed
//	public void goToLoginAppURL() {
//		d1.get("https://rahulshettyacademy.com/client/");
//	}
	
	public String getErrorMessage() {
		waitForElementToAppear(error_message,5);
		return error_message.getText();
	}

}
