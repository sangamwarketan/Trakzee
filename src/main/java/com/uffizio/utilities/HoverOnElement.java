package com.uffizio.utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.uffizio.driverFactory.DriverFactory;

public class HoverOnElement {

	private static Logger logger = LogManager.getLogger(HoverOnElement.class);
	private static Logger reportLogger = LogManager.getLogger("reportLogger");
	private static Actions action;
	SoftAssert softAssert = new SoftAssert();
	private static JavascriptExecutor jsExecutor = null;
	private static WebDriverWait wait = null;

	public void hoverOnElement(WebDriver driver, String ElementAddress) throws InterruptedException {

		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Method called hoverOnElement and caller method name: " + callerMethodName);

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		WebElement hoverElement = driver.findElement(By.xpath(ElementAddress));
		try {
			wait.until(ExpectedConditions.visibilityOf(hoverElement));
			action.moveToElement(hoverElement).pause(300).build().perform();
			logger.info("Mouse hover on the element");
		} catch (Exception e) {
			logger.info("Exception from: hoverOnElement >> " + e.getMessage());
		}
		 softAssert.assertAll();
	}

	public void hoverOnElement(WebDriver driver, String elementLocator, String elementName, String elementValue)
			throws InterruptedException {

		// To Handle Caller Method name
		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Method called hoverOnElement and caller method name: " + callerMethodName);

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		By dynamicLocator = By.xpath(elementLocator.toString().replace(elementName, elementValue));
		WebElement hoverElement = DriverFactory.getDriver().findElement(dynamicLocator);
		try {
			wait.until(ExpectedConditions.visibilityOf(hoverElement));
			action.moveToElement(hoverElement).pause(300).build().perform();
			logger.info("Mouse hover on the element");
		} catch (Exception e) {
			logger.info("Exception from: hoverOnElement >> " + e.getMessage());
		}
		 softAssert.assertAll();

	}

	public void hoverOnElement(WebDriver driver, String ElementAddress, boolean wantToCatchTooltip)
			throws InterruptedException {

		StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTrace[2].getMethodName();
		logger.info("Method called hoverOnElement and caller method name: " + callerMethodName);

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;

		if (!wantToCatchTooltip) {
			WebElement hoverElement = driver.findElement(By.xpath(ElementAddress));
			try {
				wait.until(ExpectedConditions.visibilityOf(hoverElement));
				action.moveToElement(hoverElement).pause(100).build().perform();
				reportLogger.info("Mouse hover on the element: " + hoverElement.getText().toString());
				// logger.info("Mouse hover on the element:
				// "+hoverElement.getText().toString());
			} catch (Exception e) {
				reportLogger.info("Exception from: hoverOnElement >> " + e.getMessage());
				// logger.info("Exception from: hoverOnElement >> "+e.getMessage());
			}
		} else {
			FindHoverDataOfLineGraphs.tooltipTitle(driver, ElementAddress);
		}
		 softAssert.assertAll();

	}
	
	public void hoverOnElementAndVerify(WebDriver driver, String hoverElementXPath, String verifyElementXPath,String verifyElementValues) throws InterruptedException {

	    StackTraceElement stackTrace[] = Thread.currentThread().getStackTrace();
	    String callerMethodName = stackTrace[2].getMethodName();
	    logger.info("Method called hoverOnElementAndVerify, called by: " + callerMethodName);

	    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    action = new Actions(driver);
	    jsExecutor = (JavascriptExecutor) driver;

	    // Find the element to hover on
	    WebElement hoverElement = driver.findElement(By.xpath(hoverElementXPath));

	    try {
	        // Wait for the hover element to be visible
	        wait.until(ExpectedConditions.visibilityOf(hoverElement));

	        // Perform hover action
	        action.moveToElement(hoverElement).pause(300).build().perform();
	        logger.info("Mouse hover performed on the element with XPath: " + hoverElementXPath);

	        // After hover, verify the appearance of another element
	        WebElement verifyElement = driver.findElement(By.xpath(verifyElementXPath));
	        String verifyElementActualValues = verifyElement.getText().toString().trim();
	        softAssert.assertEquals(verifyElementActualValues, verifyElementValues.trim(),"To verify elment after hover");
	        // Wait for the verification element to appear
	        wait.until(ExpectedConditions.visibilityOf(verifyElement));

	        logger.info("Verified appearance of element with XPath: " + verifyElementXPath);
	    } catch (Exception e) {
	        logger.error("Exception in hoverOnElementAndVerify >> " + e.getMessage());
	    }
	    softAssert.assertAll();
	}


}
