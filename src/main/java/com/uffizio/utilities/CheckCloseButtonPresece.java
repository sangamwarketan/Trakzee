package com.uffizio.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckCloseButtonPresece {

	
	private  Logger logger = LogManager.getLogger(getClass());
	private  WebDriverWait wait;
	private  Actions action;
	private  JavascriptExecutor jsExecutor;
	
	public  boolean isCloseButtonPresent(WebDriver driver, String elementXpath, String closeButtonIdentifierAttribute ) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(elementXpath));
		String isCloseButton = (String)jsExecutor.executeScript("return !arguments[0].hasAttribute('"+closeButtonIdentifierAttribute+"');", element);
		String[] firstWord = isCloseButton.split(" ");
		isCloseButton = firstWord[0].trim();
		if(isCloseButton.equals(isCloseButton)) {
			return true;
		}else {
			return false;
		}
	}
}
