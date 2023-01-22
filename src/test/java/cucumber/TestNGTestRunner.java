package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//for running feature file we need CucumberOptions()
//glue specifies stepDefination file path wrt cucumber package path
//by default result come in encoded format so use monochrome=true so it will come in readable format
@CucumberOptions(features = "src/test/java/cucumber", glue = "seleniumFrameworkDesign.stepDefinations", tags = "@SubmitOrderTest", plugin = {
		"html:target/cucumber.html" })
//by default cucumber don't understand testNG code so AbstractTestNGCucumberTests provided 
//by testNG team helps cucumbet to understand testNG code
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
