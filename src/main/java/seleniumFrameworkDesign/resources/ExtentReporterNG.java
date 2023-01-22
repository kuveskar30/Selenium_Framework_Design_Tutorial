package seleniumFrameworkDesign.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	
	public static ExtentReports getReportObject() {
		
		//this code should be written at start of each test to use extent report
		//after completion of all tests(and not every test) extent.flush() should be used
		//otherwise report will not get created
		String path = System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Modified Test Results");
		reporter.config().setReportName("Web Automation Results");
		
		ExtentReports extent_reports = new ExtentReports();
		extent_reports.attachReporter(reporter);
		extent_reports.setSystemInfo("Tester Name", "PTK");
		
		return extent_reports;
		
	}
	
	
}
