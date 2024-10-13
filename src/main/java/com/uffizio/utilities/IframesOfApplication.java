package com.uffizio.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;

import com.uffizio.driverFactory.DriverFactory;

public class IframesOfApplication {
	
public void Switch_To_divframe() {
		
		
		try {
			DriverFactory.getDriver().switchTo().defaultContent();
			DriverFactory.getDriver().switchTo().frame("divframe");
		} catch (NoSuchFrameException e) {
			System.out.println("No alert present.");
		}
		
	}
	public void Switch_To_windowframe() {
		try {
			DriverFactory.getDriver().switchTo().defaultContent();
			DriverFactory.getDriver().switchTo().frame("divframe");
			DriverFactory.getDriver().switchTo().frame("windowframe");
		} catch (NoSuchFrameException e) {
			System.out.println("No alert present.");
		}
	}
	public void Switch_To_bottomframe() {
		try {
			DriverFactory.getDriver().switchTo().defaultContent();
			DriverFactory.getDriver().switchTo().frame("divframe");
			DriverFactory.getDriver().switchTo().frame("windowframe");
			DriverFactory.getDriver().switchTo().frame("bottomframe");
		} catch (NoSuchFrameException e) {
			System.out.println("No alert present.");
		}

	}
	public void Switch_To_contentframe() {
		try {
			DriverFactory.getDriver().switchTo().defaultContent();
			DriverFactory.getDriver().switchTo().frame("divframe");
			DriverFactory.getDriver().switchTo().frame("windowframe");
			DriverFactory.getDriver().switchTo().frame("contentframe");
		} catch (NoSuchFrameException e) {
			System.out.println("No alert present.");
		}
	}

	public void Switch_To_titleframe() {
		try {
			DriverFactory.getDriver().switchTo().defaultContent();
			DriverFactory.getDriver().switchTo().frame("divframe");
			DriverFactory.getDriver().switchTo().frame("windowframe");
			DriverFactory.getDriver().switchTo().frame("titleframe");
		} catch (NoSuchFrameException e) {
			System.out.println("No alert present.");
		}
	}
		public void Switch_To_schedule_Screen() {
			try {
				DriverFactory.getDriver().switchTo().defaultContent();
				DriverFactory.getDriver().switchTo().frame("divframe");
				DriverFactory.getDriver().switchTo().frame("windowframe");
				DriverFactory.getDriver().switchTo().frame("contentframe");
				DriverFactory.getDriver().switchTo().frame("iframe_schedule");
			} catch (NoSuchFrameException e) {
				System.out.println("No alert present.");
			}

	}
		public void Switch_To_Setting_Popup() {
			try {
				DriverFactory.getDriver().switchTo().defaultContent();
				DriverFactory.getDriver().switchTo().frame("divframe");
				WebElement frame = DriverFactory.getDriver().findElement(By.xpath("//iframe[@src='TrakzeeAdavncedSort.jsp']"));
				DriverFactory.getDriver().switchTo().frame(frame);
			
			} catch (NoSuchFrameException e) {
				System.out.println("No alert present.");
			}

	}

}
