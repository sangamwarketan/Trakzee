package com.uffizio.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragAndDrop {

	public Logger logger = LogManager.getLogger(DragAndDrop.class);
	public WebDriverWait wait;
	public static Actions actions;
	public JavascriptExecutor jsExecutor;
	public CheckElementStatus elementEnableState;

	public void dragAndDropElementInDifferentPlane(WebDriver driver, String xpath_SOURCE_ELEMENT,
			String xpath_TARGET_ELEMENT) {
		try {
			WebElement sourceElement = driver.findElement(By.xpath(xpath_SOURCE_ELEMENT));
			WebElement targetElement = driver.findElement(By.xpath(xpath_TARGET_ELEMENT));
			actions = new Actions(driver);
			actions.dragAndDrop(sourceElement, targetElement).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.warn("Exception: " + e.getMessage());
		}
	}

	public void dragAndDropInSamePlane(WebDriver driver, String xpath_ELEMENT, int xOffset, int yOffset) {
		try {
			Thread.sleep(1000);
			WebElement textElement = driver.findElement(By.xpath(xpath_ELEMENT));
			actions = new Actions(driver);
			actions.dragAndDropBy(textElement, xOffset, yOffset).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.warn("Exception: " + e.getMessage());
		}
	}

	public void dragAndDropInSamePlane(WebDriver driver, String xpath_ConatainerFrameAddress,
			String xpath_dragableElement, String topButtonCenter) {
		String xOffsetPercentage = null;
		String yOffsetPercentage = null;
		try {
			switch (topButtonCenter) {
			case "bottom": {
				xOffsetPercentage = "0";
				yOffsetPercentage = "+40%";
				logger.info("Label selection: Bottom");
				break;
			}
			case "top": {
				xOffsetPercentage = "0";
				yOffsetPercentage = "-40%";
				logger.info("Label selection: Top");
				break;
			}
			
			default: {
				// for center
				xOffsetPercentage = "0%";
				yOffsetPercentage = "0%";
				logger.info("Label selection: Center");
				break;
			}
			}

			Thread.sleep(1000);
			WebElement containerFrame = driver.findElement(By.xpath(xpath_ConatainerFrameAddress));
			WebElement textElement = driver.findElement(By.xpath(xpath_dragableElement));

			// Get the width and height of the WebElement
			int elementWidth = containerFrame.getSize().getWidth();
			int elementHeight = containerFrame.getSize().getHeight();

			// Parse the percentage strings and convert them to integers
			int xOffset = (int) (elementWidth * (Integer.parseInt(xOffsetPercentage.replace("%", "")) / 100.0));
			int yOffset = (int) (elementHeight * (Integer.parseInt(yOffsetPercentage.replace("%", "")) / 100.0));

			// Perform the drag and drop action with calculated offsets
			Actions actions = new Actions(driver);
			actions.dragAndDropBy(textElement, xOffset, yOffset).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			logger.warn("Exception: " + e.getMessage());
		}
	}

}