package com.uffizio.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.uffizio.driverFactory.DriverFactory;

public class CommonApplicationReusableMethod {

	SoftAssert soft =new SoftAssert();
	
	public void compareListByText(List <WebElement> ele ,String[] list,String Error_message) {

		List<String> Actual_Option= new ArrayList<String>();
		for(WebElement col:ele) {

			Actual_Option.add(col.getText().replaceAll(" :","").replace(":",""));

		}
		List<String> Actual_Value=Actual_Option;
		//expected
		List<String> Expected_Value=Arrays.asList(list);
		soft.assertEquals(Actual_Value, Expected_Value,Error_message);
		soft.assertAll();


	}
	public void compareListByAttribute (List <WebElement> ele ,String[] list,String Error_message) {

		List<String> Actual_Option= new ArrayList<String>();
		for(WebElement col:ele) {

			Actual_Option.add(col.getAttribute("title").replaceAll(" :",""));
		}

		List<String> Actual_Value=Actual_Option;
		//expected
		List<String> Expected_Value=Arrays.asList(list);
		soft.assertEquals(Actual_Value, Expected_Value,Error_message);
		soft.assertAll();
	}
	
	public void compareListBySelectClass(WebElement Ele,String[] list,String Error_message) {
		Select ss= new Select(Ele);
		List<WebElement> lists = ss.getOptions();
		List<String> Actual_Option= new ArrayList<String>();
		for(WebElement dList:lists) {

			Actual_Option.add(dList.getText());
		}
		List<String> Actual_Value=Actual_Option;
		List<String> Expected_Value=Arrays.asList(list);
		soft.assertEquals(Actual_Value, Expected_Value,Error_message);
		soft.assertAll();
	}

	public void compareColumnNameByText(List <WebElement> ele ,String[] list,String Error_message) {

		List<String> Actual_Option= new ArrayList<String>();
		for(WebElement col:ele) {
			 	Actual_Option.add(col.getText());
		}
		List<String> Actual_Value=Actual_Option;
		//expected
		List<String> Expected_Value=Arrays.asList(list);
		soft.assertEquals(Actual_Value, Expected_Value,Error_message);
		soft.assertAll();


	}




}
