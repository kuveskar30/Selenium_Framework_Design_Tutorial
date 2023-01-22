package seleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumFrameworkDesign.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
// locators belong to login page will come here

	WebDriver d1;

	public ProductCatalogue(WebDriver d1) {
		// sending driver to parent AbstractComponent
		super(d1);
		// initialization
		this.d1 = d1;
		// it takes searchContext and page in which WbElements to be used as argument
		PageFactory.initElements(d1, this);
	}

	// WebElement user_email = d1.findElement(By.id("userEmail"));

	// PageFactory : alternate way of defining WebElement

	// List<WebElement> products = d1.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	// as rahul has developed this app he was knowing locator for this animation
	// we can ask to developer for locator or html code of this animation
	@FindBy(css = ".ngx-spinner-overlay")
	WebElement spinner1;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner2;

	// locators list
	By add_to_cart_locator = By.cssSelector(".card-body button:last-of-type");
	By toast_msg_after_add_to_cart_locator = By.cssSelector("#toast-container");
	By products_locator = By.cssSelector(".mb-3");

	// action for getting products list
	public List<WebElement> getProductList() {
		waitForElementToAppear(products_locator, 5);
		return products;
	}

	// action for selecting desired product by name
	public WebElement getProductByName(String desired_product_name) {
		WebElement selected_product = products.stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(desired_product_name))
				.findFirst().orElse(null);
		return selected_product;
		
	}

	// add product to cart
	public void addProductToCart(String desired_product_name) throws InterruptedException {
		WebElement selected_product = getProductByName(desired_product_name);
		selected_product.findElement(add_to_cart_locator).click();
		//because of heavy load on
		//App spinner taking time to invisible, 
		//internally spinner also waiting for other services to load
		Thread.sleep(5000);
//		waitForElementToAppear(spinner, 5);
//		waitForElementToDisappear(spinner1, 20);
//		waitForElementToDisappear(spinner2, 20);
		
	}

}
