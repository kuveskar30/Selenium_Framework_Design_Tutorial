package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//for running feature file we need CucumberOptions()
//glue specifies stepDefination file path wrt cucumber package path
//by default result come in encoded format so use monochrome=true so it will come in readable format

//by default cucumber don't understand testNG code so AbstractTestNGCucumberTests
//provided by testNG team helps cucumber to understand testNG code

//cucumber can't run on it's own it needs either junit or testNg to run feature files
//here we are using TestNG assertions hence using testNgRunner

//we are using AbstractTestNGCucumberTests just to run cucumber feature files
//we are not using any testNG features like dataprovider,annotations etc
//for that we should use cucumber specific features which are not covered in
//this course
@CucumberOptions(features = "src/test/java/cucumber", glue = "seleniumFrameworkDesign.stepDefinations",
monochrome=true, tags = "@LoginErrorValidationTest", plugin = {
	"html:target/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
