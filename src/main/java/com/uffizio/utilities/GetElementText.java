package com.uffizio.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GetElementText {

	//DECLARATIONS
	public static Logger logger = LogManager.getLogger(GetElementText.class);
	public static Actions action;
	public static JavascriptExecutor jsExecutor = null;
	

	//TO FIND TOOL-TIP TITLE
	public static void getElementTextByAttribute(WebDriver driver, String toolTip_TitleAddress, String attributeName) throws InterruptedException {
		try {
			//Thread.sleep(500);
			WebElement tooltips_Title = driver.findElement(By.xpath(toolTip_TitleAddress));
			//Thread.sleep(500);
			String	tooltips_TitleValues = tooltips_Title.getAttribute(attributeName);
			

			//logger.info("tooltip_Title: " + tooltips_TitleValues);
			//Thread.sleep(500);
		} catch (Exception e) {
			logger.info("Exception from tooltipTitle: " + e.getMessage());
		}
	}
	
	//TO FIND TOOL-TIP TITLE
		public static String getElementText(WebDriver driver, String toolTip_TitleAddress) throws InterruptedException {
			String tooltips_TitleValues=null;
			try {
				Thread.sleep(500);
				WebElement tooltips_Title = driver.findElement(By.xpath(toolTip_TitleAddress));
				Thread.sleep(500);
				 tooltips_TitleValues = tooltips_Title.getText();

				logger.info("tooltip_Title: " + tooltips_TitleValues);
				Thread.sleep(500);
			} catch (Exception e) {
				logger.info("Exception from tooltipTitle: " + e.getMessage());
			}
			return tooltips_TitleValues;
		}

}
