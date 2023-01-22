package seleniumFrameworkDesign.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.OrdersPage;

public class AbstractComponent {

	//utilities which can be used in any page are listed here
	private WebDriver d1;
	
	//constructor for AbstractComponent
	public AbstractComponent(WebDriver d1) {
		this.d1 = d1;
		PageFactory.initElements(d1, this);
	}
	//WebElements
	@FindBy(css = "[routerlink*='cart']")
	private WebElement cart_header_button;
	
	//WebElements
	@FindBy(css = "[routerlink*='myorders']")
	private WebElement orders_header_button;

	public WebDriverWait webDriverWaitCustom(int durationInSeconds) {
		WebDriverWait w = new WebDriverWait(d1, Duration.ofSeconds(durationInSeconds));
		return w;
	}
	
	public void waitForElementToAppear(By locator, int durationInSeconds) {
		WebDriverWait w = webDriverWaitCustom(durationInSeconds);
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementToAppear(WebElement element, int durationInSeconds) {
		WebDriverWait w = webDriverWaitCustom(durationInSeconds);
		w.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToDisappear(WebElement element, int durationInSeconds) {
		WebDriverWait w = webDriverWaitCustom(durationInSeconds);
		w.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementToBeClickable(WebElement element, int durationInSeconds) {
		WebDriverWait w = webDriverWaitCustom(durationInSeconds);
		w.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public CartPage goToCartPage() {
		waitForElementToAppear(cart_header_button,30);
		cart_header_button.click();
		CartPage cp = new CartPage(d1);
		return cp;
	}
	
	public OrdersPage goToOrdersPage() {
		orders_header_button.click();
		OrdersPage op = new OrdersPage(d1);
		return op;
	}
	
}
