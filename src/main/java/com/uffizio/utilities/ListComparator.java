package com.uffizio.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class ListComparator {

	// TO LOG THE MESSAGES ON THE CONSOLE AND LOG FILES BOTH
	private static Logger logger = LogManager.getLogger(ListComparator.class);
	private static Logger reportlogger = LogManager.getLogger("reportLogger");
	private static SoftAssert softAssert = new SoftAssert();
	
	public static class ComparisonResult {
		private final boolean allMatch;
		private final List<Integer> mismatchIndices;

		public ComparisonResult(boolean allMatch, List<Integer> mismatchIndices) {
			this.allMatch = allMatch;
			this.mismatchIndices = mismatchIndices;
		}

		public boolean isAllMatch() {
			return allMatch;
		}

		public List<Integer> getMismatchIndices() {
			return mismatchIndices;
		}
	}

	//WAY ONE
	// Method to compare two lists using a for loop
	public static ComparisonResult compareLists(ArrayList<String> list1, ArrayList<String> list2) {
		System.out.println("Method called: compareLists");
		List<Integer> mismatchIndices = new ArrayList<>();

		// Ensure both lists are of the same size
		if (list1.size() != list2.size()) {
			return new ComparisonResult(false, mismatchIndices);
		}

		// Compare elements and collect mismatched indices
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equals(list2.get(i))) {
				mismatchIndices.add(i);
			}
		}

		return new ComparisonResult(mismatchIndices.isEmpty(), mismatchIndices);
	}

	//WAY TWO
	// Method to compare two lists using streams
	public static ComparisonResult compareListsUsingStream(ArrayList<String> list1, ArrayList<String> list2) {
		List<Integer> mismatchIndices = new ArrayList<>();
		logger.info("Method called: compareListsUsingStream");

		// Ensure both lists are of the same size
		if (list1.size() != list2.size()) {
			return new ComparisonResult(false, mismatchIndices);
		}

		// Compare elements using IntStream and collect mismatched indices
		IntStream.range(0, list1.size()).filter(i -> !list1.get(i).equals(list2.get(i))).forEach(mismatchIndices::add);

		return new ComparisonResult(mismatchIndices.isEmpty(), mismatchIndices);
	}
	
	
	
	public static Object[] convertWebElementListToStringArray(WebDriver driver, String listXpath) {
		List<WebElement> listItems = driver.findElements(By.xpath(listXpath));
		List<String> acutalList = new ArrayList<String>();
		for (WebElement items : listItems) {
			String itemName = items.getText().toString();
			acutalList.add(itemName);
		}
		return acutalList.toArray();
	}

	public static String[] compareListByText(WebDriver driver, String listXpath) {
		List<WebElement> listItems = driver.findElements(By.xpath(listXpath));
		List<String> acutalList = new ArrayList<String>();
		for (WebElement items : listItems) {
			String itemName = items.getText().toString();
			acutalList.add(itemName);
		}
		return (String[]) acutalList.toArray();
	}

	public static void compareListByText(WebDriver driver, String xpathOfListElement, String expectedList,
			String Error_message, String seperator) {

		String[] list = expectedList.split(",");
 		String[] keyValuesSeperator = seperator.split(",");
		List<WebElement> elementList = driver.findElements(By.xpath(xpathOfListElement));
		List<String> Actual_Option = new ArrayList<String>();

		for (WebElement col : elementList) {
			String values = null;
			int seperatorLength = keyValuesSeperator.length;
			values = col.getText().toString();
			while (seperatorLength != 0) {
				values = values.replace(keyValuesSeperator[seperatorLength - 1], "");
				seperatorLength--;
			}
			reportlogger.info("values: " + values);
			Actual_Option.add(values);

		}
		List<String> Actual_Value = Actual_Option;
		// expected
		List<String> Expected_Value = Arrays.asList(list);
		softAssert.assertEquals(Actual_Value, Expected_Value, Error_message);
		softAssert.assertAll();
	}

	public void compareListByAttribute(WebDriver driver, String xpathOfListElement, String[] list,
			String Error_message,String attributeName, String seperator) {
		
		String[] keyValuesSeperator = seperator.split(",");
		List<WebElement> elementList = driver.findElements(By.xpath(xpathOfListElement));
		List<String> Actual_Option = new ArrayList<String>();
		
		for (WebElement col : elementList) {
			String values = null;
			int seperatorLength = keyValuesSeperator.length;
			values = col.getAttribute(attributeName).toString();
			while (seperatorLength != 0) {
				values = values.replace(keyValuesSeperator[seperatorLength - 1], "");
				seperatorLength--;
			}
			reportlogger.info("values: " + values);
			Actual_Option.add(values);
		}
		List<String> Actual_Value = Actual_Option;
		// expected
		List<String> Expected_Value = Arrays.asList(list);
		softAssert.assertEquals(Actual_Value, Expected_Value, Error_message);
		softAssert.assertAll();
	}

    public void compareListBySelectClass(WebDriver driver,String xPathLocator, String[] list,String Error_message) {
    	WebElement ele = driver.findElement(By.xpath(xPathLocator));
    	Select ss= new Select(ele);
        List<WebElement> lists = ss.getOptions();
        List<String> Actual_Option= new ArrayList<String>();
        for(WebElement dList:lists) {
            Actual_Option.add(dList.getText());
        }
        List<String> Actual_Value=Actual_Option;
        List<String> Expected_Value=Arrays.asList(list);
        softAssert.assertEquals(Actual_Value, Expected_Value,Error_message);
        softAssert.assertAll();
    }

}

