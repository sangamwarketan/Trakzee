package com.uffizio.runner;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = {
			//	"src/test/resources/Reports"
				"src/test/resources/FeaturesFiles/HomePage"
	//	,"src/test/resources/CommonReportFeatures"
		
		},

		glue = {"com.uffizio.homePage", "com.uffizio.stepDefinationLogin","com.uffizio.appHooks","com.uffizio.Activity","com.uffizio.Common",
		},
		plugin= {"pretty"},
		publish=true,
		monochrome = true,
		dryRun=false
	//	,tags = "@Start and @Travel or @Travel"
		//,tags = "@Start and @TravelHistory or @TravelHistory"
		//,tags = "@Start and @Trip or @Trip"
		)


public class Runner2 extends AbstractTestNGCucumberTests {

}
