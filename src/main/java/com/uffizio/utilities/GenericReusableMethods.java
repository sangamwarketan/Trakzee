package com.uffizio.utilities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.uffizio.driverFactory.DriverFactory;

public class GenericReusableMethods {

	//private GenericReusableMethods gen =new GenericReusableMethods();


	public void clickingOnWebElemt(WebElement element,long waitTimeInSec) {
		WebDriverWait wait=new WebDriverWait(DriverFactory.getDriver(),Duration.ofSeconds(waitTimeInSec));
		WebElement elements = wait.until(ExpectedConditions.elementToBeClickable(element));
		elements.click();
	}

	public void sendKeysOnWebElement(WebElement element,String text) {
		element.click();
		element.clear();
		element.sendKeys(text);
	}
	public String getElementText(WebElement element) {
		try {
			WebDriverWait wait=new WebDriverWait(DriverFactory.getDriver(),Duration.ofSeconds(20));
			WebElement elements = wait.until(ExpectedConditions.elementToBeClickable(element));
			return elements.getText();
		} catch(Exception e) {
			return null;
		}
	}
	public String getElementtextByAttribute(WebElement element, String att) {
		try {
			return element.getAttribute(att);
		} catch(Exception e) {
			return null;
		}
	}
	
	public boolean isElementDisplayed(WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch(NoSuchElementException e) {
			System.out.println("No such");
			return false;
		}
	}
	public boolean isElementEnabled(WebElement ele) {
		try {
			return ele.isEnabled();
		} catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public void selectByVisibleText(WebElement element,String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public boolean isAlertPresent(int time) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(time));
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch(Exception e) {	//TimeoutException
			return false;
		}
	}

	public void acceptAlert() {
		Alert alt = DriverFactory.getDriver().switchTo().alert();
		alt.accept();	
	}
	public void dismissAlert() {
		Alert alt = DriverFactory.getDriver().switchTo().alert();
		alt.dismiss();	
	}
	public void mouseHoverAndClickElement(WebElement element) {
		Actions action=new Actions(DriverFactory.getDriver());
		action.moveToElement(element).click().build().perform();
	}
	public void mouseHoverOnly(WebElement element) {
		Actions action=new Actions(DriverFactory.getDriver());
		action.moveToElement(element).build().perform();
	}
	
	public void Implicitlywait(int time) {

		DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

	}
	
	public void ClickSearchSelectDropDownOption(By ClickDropDown,By SearchBarDropDown,By DropDownOptionList,String Value) {
		WebElement Click = DriverFactory.getDriver().findElement(ClickDropDown);
		Click.click();
		
		WebElement Click1 = DriverFactory.getDriver().findElement(SearchBarDropDown);
		Click1.click();
		WebElement Clear = DriverFactory.getDriver().findElement(SearchBarDropDown);
		Clear.clear();

		WebElement Sendkeys = DriverFactory.getDriver().findElement(SearchBarDropDown);
		Sendkeys.sendKeys(Value);
		
		Actions Act= new Actions(DriverFactory.getDriver());
		List<WebElement> object_type = 	DriverFactory.getDriver().findElements(DropDownOptionList);
		for(WebElement type:object_type) { 
			//Thread.sleep(1000);
			Act.moveToElement(type).perform();

			if (type.getText().equalsIgnoreCase(Value)) { 
				type.click(); 
				break; 
			}
		}
	}
	

	public void ClickandSelectDropDownOption(WebElement ClickDropDown,List<WebElement> DropDownList,String Value,String AttributeName) {
	
		ClickDropDown.click();
			
		Actions Act= new Actions(DriverFactory.getDriver());
		for(WebElement type:DropDownList) { 
			//Thread.sleep(1000);
			Act.moveToElement(type).perform();

			if (type.getText().equalsIgnoreCase(Value)) { 
				type.click(); 
				
					String Text = ClickDropDown.getAttribute(AttributeName);
					
				
					Assert.assertEquals(Text, Value,"Selected value not show in the dropdown");
					
					break;
				}	
			ClickDropDown.click();
			
			}
		}
	
	
	
	public void SearchAndSelectFromSearchbar(WebElement Searchbar,List<WebElement> ObjectList,By ObjectName,String Value) {
		
		Searchbar.click();
		Searchbar.clear();
		Searchbar.sendKeys(Value);
		
		
			
		Actions Act= new Actions(DriverFactory.getDriver());
		for(WebElement type:ObjectList) { 
			//Thread.sleep(1000);
			Act.moveToElement(type).perform();

			if (type.getText().equalsIgnoreCase(Value)) { 
				WebElement Click = DriverFactory.getDriver().findElement(ObjectName);
				Click.click();
				
				break;
				}	
			
			
			}
		}
	
	
	public String GetCurrentDate(String TimeZone,String DateFormat) {
		
		LocalDate nyDate = LocalDate.now(ZoneId.of(TimeZone));
		return nyDate.format(DateTimeFormatter.ofPattern(DateFormat));
	}
	
public String GetCurrentTime(String TimeZone,String TimeFormat) {
		
	 LocalTime currentTime = LocalTime.now(ZoneId.of(TimeZone));
      return currentTime.format(DateTimeFormatter.ofPattern(TimeFormat));
      }
	
	
public List<String> GetTextandStoreinList(List <WebElement> Element) {
	
	Actions Act= new Actions(DriverFactory.getDriver());
	List<String> List1=new ArrayList<String>();
	List<WebElement> object_Name = (Element);
	for(WebElement type:object_Name) { 
		//Thread.sleep(1000);
		Act.moveToElement(type).perform();

		String obj=type.getText();
		List1.add(obj);
}
return List1;	
}
public List<Float> GetValueandStoreinList(List <WebElement> Element) {
	
	Actions Act= new Actions(DriverFactory.getDriver());
	List<Float> List1=new ArrayList<Float>();
	List<WebElement> DistanceData = (Element);
	for(WebElement type:DistanceData) { 
		//Thread.sleep(1000);
		Act.moveToElement(type).perform();

		String Distance=type.getText();
		float Convertedvalue = Float.parseFloat(Distance);
		//Assert.assertTrue(Convertedvalue sysmbol DistanceValue);
		List1.add(Convertedvalue);
	}
	return List1;
}	
	
public void PresenceOfElement(String Element) {
    WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));

	 try {
         // Wait for the element to be present within 10 seconds
         WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));

         System.out.println("Element found and clicked.");
     } catch (TimeoutException e) {
         // Handle the case where the element is not found within the specified time
         System.out.println("Element not found within 10 seconds, skipping this step.");
     } finally {
         // Continue with other steps
         System.out.println("Executing next steps...");

}
}

	
}
