package com.uffizio.utilities;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class WebElementList {

	private static Logger logger = LogManager.getLogger(WebElementList.class);
	private static Logger reportLogger = LogManager.getLogger("reportLogger");
	static SoftAssert softAssert = new SoftAssert();

	public static boolean selectElementFromList(WebDriver driver, String listElementAddress, String elementName) {

		List<WebElement> listElement = driver.findElements(By.xpath(listElementAddress));
		for (WebElement item : listElement) {
			String actualName = item.getText().trim();
			if (actualName.equals(elementName)) {
				try {
					item.click();
					reportLogger.info("Selected element: " + actualName);
					return true;
				} catch (Exception e) {
					logger.warn("Exception: " + e.getMessage());
				}
			}
		}
		return false;
	}
    
}
