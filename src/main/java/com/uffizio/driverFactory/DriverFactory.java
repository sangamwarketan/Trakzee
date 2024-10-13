package com.uffizio.driverFactory;

import java.time.Duration;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;

	public static ThreadLocal<WebDriver> tlDriver =new ThreadLocal<>();
	private Logger reportLogger = LogManager.getLogger("reportLogger");
	public Logger logger = LogManager.getLogger(this.getClass());

	public WebDriver init_driver(String Browser ,String HeadLess) {
		if(Browser.equalsIgnoreCase("chrome")) {

			//WebDriverManager.chromedriver().setup();
			WebDriverManager.chromedriver().browserVersion("129").setup();
			ChromeOptions options=new ChromeOptions();
			if(HeadLess.equalsIgnoreCase("Yes")) {
				options.setHeadless(true);
				logger.info("Headless Browser Open Successfully");
			}

			options.addArguments("--disable-notifications");
			options.addArguments("--remote-allow-origins=*");
			/////////seter method
			tlDriver.set(new ChromeDriver(options));					
			DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
			
		}

		if(Browser.equalsIgnoreCase("Firefox")) {

			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options =new FirefoxOptions();
			if(HeadLess.equalsIgnoreCase("Yes")) {
				options.setHeadless(true);
				logger.info("Headless Browser Open Successfully");
			}
			options.addArguments("--disable-notifications");
			/////////seter method
			tlDriver.set(new FirefoxDriver(options));
			DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		else {
			logger.info("Please Pass the Corect browser value: " + Browser );
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

}
