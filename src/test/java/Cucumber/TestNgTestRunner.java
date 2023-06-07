package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber",glue="PurchaseOrder/StepDefinition",monochrome = true,tags="@Regression",plugin = {"html:target/cucumber.html"})

public class TestNgTestRunner extends AbstractTestNGCucumberTests {
}
