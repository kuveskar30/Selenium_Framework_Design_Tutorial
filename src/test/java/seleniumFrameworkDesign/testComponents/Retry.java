package seleniumFrameworkDesign.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int count = 0;
	int max_count = 1;

	//it will rerun test until it returns true
	@Override
	public boolean retry(ITestResult result) {
		if (count < max_count) {
			count++;
			return true;
		}else {
			return false;
		}
	}

}
