package seleniumFrameworkDesign.testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.OrderConfirmationPage;
import seleniumFrameworkDesign.pageObjects.OrderPaymentPage;
import seleniumFrameworkDesign.pageObjects.OrdersPage;
import seleniumFrameworkDesign.pageObjects.ProductCatalogue;
import seleniumFrameworkDesign.testComponents.BaseTest;
import seleniumFrameworkDesign.testComponents.Retry;

public class ModifiedStandaloneTest extends BaseTest {
	//for creating maven project select maven-archetype-quickstart
	
	
	
//	String desired_product_name = "ZARA COAT 3";

	@Test(dataProvider = "getData",retryAnalyzer=Retry.class)
	public void submitOrderTest(HashMap<String, String> hashmap_test_data)
			throws InterruptedException, IOException {
		String desired_product_name = hashmap_test_data.get("desired_product_name");
		ProductCatalogue product_catalouge = lp.loginToApplication(hashmap_test_data.get("email"), hashmap_test_data.get("password"));

		// add desired product to cart by providing valid product name
		// visit cart page
		product_catalouge.addProductToCart(desired_product_name);
		CartPage cp = product_catalouge.goToCartPage();
//		Thread.sleep(1000);

		// verify whether product added in cart
		Boolean is_cart_item_present = cp.verifyProductAddedInCart(desired_product_name);
		// Assertions should be written in test page only and not in page object class
		Assert.assertTrue(is_cart_item_present, desired_product_name + "does not present in cart");
		// proceed for checkout(order payment page will come)
		OrderPaymentPage opp = cp.checkOut();

		// enter desired country
		String desired_country = "India";
		opp.selectCountry(desired_country, "ind");
		// place order
		OrderConfirmationPage ocp = opp.placeOrder();
		//verify confirmation message
//		System.out.println(ocp.get_confirmation_message());
		Assert.assertEquals(ocp.get_confirmation_message(), "THANKYOU FOR THE ORDER.");
	}

//	@Test(dependsOnMethods= {"submitOrderTest"})
//	public void verifyOrdersTest() {
//		ProductCatalogue product_catalouge = lp.loginToApplication("pratikkuveskar@gmail.com", "Pratik@123");
//		OrdersPage op = product_catalouge.goToOrdersPage();
//		Assert.assertTrue(op.verifyOrderPresent(desired_product_name));
//	}
	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getjsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\seleniumFrameworkDesign\\data\\ModifiedTestData.json");
//		System.out.println(data.get(0));
//		System.out.println(data.get(1));
		return new Object[][] { {data.get(0)}};
	}
	
	


}
