package seleniumFrameworkDesign.testCases;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkDesign.pageObjects.LoginPage;

public class StandaloneTest {

	
	public static void main(String[] args) {
		
		//we need to add dependency of WebDriverManager to pom.xml file
		//By using WebDriverManager it will automatically download latest
		//driver on sysytem
		WebDriverManager.chromedriver().setup();
		WebDriver d1 = new ChromeDriver();
		d1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait w = new WebDriverWait(d1, Duration.ofSeconds(5));
		
		d1.get("https://rahulshettyacademy.com/client/");
		d1.manage().window().maximize();
		
		LoginPage lp = new LoginPage(d1);
		
		d1.findElement(By.id("userEmail")).sendKeys("pratikkuveskar@gmail.com");
		d1.findElement(By.id("userPassword")).sendKeys("Pratik@123");
		d1.findElement(By.id("login")).click();
		
		String desired_product = "ZARA COAT 3";
		
		List<WebElement> products = d1.findElements(By.cssSelector(".mb-3"));
		
		WebElement selected_product = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(desired_product)).findFirst().orElse(null);
		
		selected_product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//as rahul has developed this app he was knowing locator for this animation
		//we can ask to developer for locator or html code of this animation
		w.until(ExpectedConditions.invisibilityOf(d1.findElement(By.cssSelector(".ng-animating"))));
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		d1.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cart_items = d1.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean is_cart_item_present = cart_items.stream().anyMatch(cart_item -> cart_item.getText().equalsIgnoreCase(desired_product));
//		System.out.println(is_cart_item_present);
		Assert.assertTrue(is_cart_item_present, desired_product + "does not present in cart");
		
		d1.findElement(By.cssSelector(".subtotal button")).click();
		
		String desired_country="India";
		d1.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("in");
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section/button/span")));
		List<WebElement> matched_countries = d1.findElements(By.xpath("//section/button/span"));
		
		List<WebElement> entered_country = matched_countries.stream().filter(country -> country.getText().equalsIgnoreCase(desired_country)).collect(Collectors.toList());
		entered_country.get(0).click();
		//		matched_countries.stream().forEach(s-> System.out.println(s.getText()));
		
		d1.findElement(By.cssSelector(".actions a")).click();
		
		
	}
	
}
