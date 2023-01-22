package seleniumFrameworkDesign.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumFrameworkDesign.resources.ExtentReporterNG;

//ITestListener has methods for listening to test results
public class TestNGListeners extends BaseTest implements ITestListener {
	ExtentTest extent_test;
	ExtentReports extent_reports = ExtentReporterNG.getReportObject();
	//it helps to maintain thread safety
	//unique thread id for each test is created
	//each java class instance have unique id
	ThreadLocal<ExtentTest> thread_local_extent_test = new ThreadLocal<ExtentTest>();

	//@BeforeMethod gets executed before this
	public void onTestStart(ITestResult result) {
		// ExtentTest hold info about test case
		extent_test = extent_reports.createTest(result.getMethod().getMethodName());
		thread_local_extent_test.set(extent_test);
	}

	public void onTestSuccess(ITestResult result) {
		thread_local_extent_test.get().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		thread_local_extent_test.get().log(Status.FAIL, result.getThrowable());
		try {
			d1 = (WebDriver) result.getTestClass().getRealClass().getField("d1").get(result.getInstance());
		} catch (Throwable e1) {
			e1.printStackTrace();
		}

		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), d1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		thread_local_extent_test.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}

	public void onFinish(ITestContext context) {
		extent_reports.flush();
	}

}
