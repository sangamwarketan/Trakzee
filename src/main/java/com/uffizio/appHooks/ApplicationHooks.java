package com.uffizio.appHooks;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.uffizio.driverFactory.DriverFactory;
import com.uffizio.utilities.ConfigReader;
import com.uffizio.utilities.FileProcessor;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

	public Logger logger = LogManager.getLogger(this.getClass());
	private static Logger reportLogger = LogManager.getLogger("reportLogger");
	FileProcessor fileProcessor = new FileProcessor();

	private DriverFactory driverFactory = null;
	// private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;

	@Before(value = "@Start", order = 1)
	public void deleteOlderReportsLogsScreenshot() throws InterruptedException {
		// TO DELETE THE OLDER LOG FILE CONTENT, REPORTS, SCREENSHORTS
		boolean deleteOlderDataLogsReporsScreenshots = true;
		logger.info("deleteOlderDataLogsReporsScreenshots: " + deleteOlderDataLogsReporsScreenshots);
		if (deleteOlderDataLogsReporsScreenshots) {
			fileProcessor.deleteFileContent("./" + "Logs\\Automation.log");
			fileProcessor.deleteFileContent("./" + "Logs\\ReportLog.log");
			fileProcessor.deleteFiles("./Reports", "", true);
			fileProcessor.deleteFiles("./Screenshots", "", true);
		}

	}

	@Before(value = "@Start", order = 2)
	public void getProperty() {
		reportLogger.info("✅ Welcome To Trakzee Web Automation");
		configReader = new ConfigReader();
		prop = configReader.init_prop();

	}

	@Before(value = "@Start", order = 3)
	public void launchBrowser() throws IOException {

		reportLogger.info("Selected Browser: " + (prop.getProperty("Browser")));
		String browserName = prop.getProperty("Browser");
		String headlessAction = prop.getProperty("HeadLess");

		driverFactory = new DriverFactory();
		driverFactory.init_driver(browserName, headlessAction);

		// DriverFactory.getDriver().manage().deleteAllCookies();

		String server = prop.getProperty("Server").toLowerCase();
		String serverValues = null;
		switch (server) {
		case "demo":
			serverValues = prop.getProperty("DemoURL");
			break;
		case "qa":
			serverValues = prop.getProperty("QAURL");
			break;
		case "live":
			serverValues = prop.getProperty("LiveURL");
			break;
		}
		reportLogger.info("Selected Server: " + server + " and URL is: " + serverValues);
		DriverFactory.getDriver().get(serverValues);

//		if(prop.getProperty("Server").equalsIgnoreCase("Demo")) {
//			reportLogger.info("Selected Server: "+prop.getProperty("Server"));
//			DriverFactory.getDriver().get(prop.getProperty("DemoURL"));
//		}
//		if(prop.getProperty("Server").equalsIgnoreCase("QA")) {
//			reportLogger.info("Selected Server: "+prop.getProperty("Server"));
//			DriverFactory.getDriver().get(prop.getProperty("QAURL"));
//		}
//		if(prop.getProperty("Server").equalsIgnoreCase("Live")) {
//			reportLogger.info("Selected Server: "+prop.getProperty("Server"));
//			DriverFactory.getDriver().get(prop.getProperty("LiveURL"));
//		}

		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@After("@End")
	public void closeBrowser() {
		DriverFactory.getDriver().quit();
	}

	@After(order = 1)
	public void screenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			// Capture screenshot and embed it in the report
			byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Screenshot");
			// DriverFactory.getDriver().navigate().refresh();
		}

	}

}
