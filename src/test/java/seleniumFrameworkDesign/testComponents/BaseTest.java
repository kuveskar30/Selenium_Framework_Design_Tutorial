package seleniumFrameworkDesign.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumFrameworkDesign.pageObjects.LoginPage;

public class BaseTest {
	public WebDriver d1;
	public LoginPage lp;

	public WebDriver initializeDriver() throws IOException {

		// we are setting global property for browser name in .properties file
		// from that file we are setting brower name, and accessing here
		// through Properties class
		Properties prop = new Properties();
//		System.getProperty("user.dir") gives path of current project in our system
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\seleniumFrameworkDesign\\resources\\GlobalData.properties");
		prop.load(fis);
		
		//we are 1st checking that if any browser property coming from maven terminal
		//if not then we will use browser from .properties file
		//below I have use ternary operator
		String browser_name = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

		if (browser_name.contains("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			// we need to add dependency of WebDriverManager to pom.xml file
			// By using WebDriverManager it will automatically download latest
			// driver on system
			WebDriverManager.chromedriver().setup();
			
			if(browser_name.contains("headless")) {
				options.addArguments("headless");
			}
			
			d1 = new ChromeDriver(options);
			//full screen mode
			d1.manage().window().setSize(new Dimension(1440,900));
			
		} else if (browser_name.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			d1 = new FirefoxDriver();
		} else if (browser_name.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			d1 = new EdgeDriver();
		}

		d1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		d1.manage().window().maximize();
		
		return d1;

	}

	//when we run test by testNG helper attribute group, it will run only test method having
	//group attribute, it will not run @BeforeMethod, so for running this we are using (alwaysRun = true)
	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		d1 = initializeDriver();
		d1.get("https://rahulshettyacademy.com/client/");
		lp = new LoginPage(d1);
		// I think below commented step should not be part of login page object
		// lp.goToLoginAppURL();
		return lp;//this line not required in testNG in this case
		//but required in cucumber step definition
	}

	@AfterMethod(alwaysRun = true)
	public void quitApplication() throws InterruptedException {
		d1.quit();
	}

	public String getScreenshot(String testCaseName, WebDriver d1) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) d1;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String file_path = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File destination = new File(file_path);
		FileUtils.copyFile(source, destination);

		return file_path;
	}

	public List<HashMap<String, String>> getjsonDataToMap(String filePath) throws IOException {
		String json_content = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// for converting String to HashMap --> add jackson databind dependency to
		// pom.xml file
		ObjectMapper mapper = new ObjectMapper();

		TypeReference<List<HashMap<String, String>>> ref = new TypeReference<List<HashMap<String, String>>>() {
		};
		List<HashMap<String, String>> data = mapper.readValue(json_content, ref);

		return data;
	}
}
