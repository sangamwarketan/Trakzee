package com.uffizio.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {
				"src/test/resources/LoginFeatures"
		},
		
		glue = {"stepDefinationLogin","appHooks"
				},
		plugin= {"pretty"},
		publish=true,
		dryRun=false
		
		)

public class RunnerJunit {

}
