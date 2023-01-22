package seleniumFrameworkDesign.testCases;

import java.io.IOException;
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
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.LoginPage;
import seleniumFrameworkDesign.pageObjects.OrderPaymentPage;
import seleniumFrameworkDesign.pageObjects.ProductCatalogue;
import seleniumFrameworkDesign.testComponents.BaseTest;
import seleniumFrameworkDesign.testComponents.Retry;

public class ErrorValidationsCopy extends BaseTest {
	//grouping tests according to functionality or some category is important
	@Test(groups= {"errorHandeling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {

		lp.loginToApplication("pratikkuveskar@gmail.com", "Pratik@123");
//		Assert.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");
	}

	@Test(groups= {"errorHandeling"})
	public void productErrorValidation() throws InterruptedException, IOException {

		ProductCatalogue product_catalouge = lp.loginToApplication("pratikkuveskar@gmail.com", "Pratik@123");

		// add desired product to cart by providing valid product name
		// visit cart page
		String desired_product_name = "ZARA COAT 3";
		product_catalouge.addProductToCart(desired_product_name);
		CartPage cp = product_catalouge.goToCartPage();

		// verify whether product added in cart
		Boolean is_cart_item_present = cp.verifyProductAddedInCart(desired_product_name);
		// Assertions should be written in test page only and not in page object class
		Assert.assertTrue(is_cart_item_present, desired_product_name + "does not present in cart");
	}

}
