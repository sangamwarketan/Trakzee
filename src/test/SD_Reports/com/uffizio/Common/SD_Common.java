package com.uffizio.Common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.uffizio.PL_Commons.PL_Commons;
import com.uffizio.PL_LoginAndHome.PL_Login;
import com.uffizio.driverFactory.DriverFactory;
import com.uffizio.utilities.CheckElementStatus;
import com.uffizio.utilities.ClickOnAnyButton;
import com.uffizio.utilities.GetElementText;
import com.uffizio.utilities.IframesOfApplication;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SD_Common {

	IframesOfApplication iframe =new IframesOfApplication();
	ClickOnAnyButton clickOnAnyButton =new ClickOnAnyButton();
	public WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(60));
	SoftAssert soft =new SoftAssert();
	CheckElementStatus checkElementStatus = new CheckElementStatus();
	
	
	
	static String mainmodule;
	static String submodule;
	static String screen;
	static 	String HeaderText;
	@Then("{string} Module {string} Sub Module {string} Screen and Header name is {string}")
	public void module_sub_module_screen_and_header_name_is(String Main, String Sub, String Screen1, String Header1) {
		mainmodule=	Main;
		submodule=	Sub;
		screen=	Screen1;
		HeaderText=Header1;
	}

	//@Before(order = 1)
	@Given("Navigate to Screen")
	public void navigate_to_screen() throws InterruptedException {
		Thread.sleep(500);
	iframe.Switch_To_divframe();

	clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(DriverFactory.getDriver(), PL_Commons.hoverMainModule, mainmodule,mainmodule);

	clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(DriverFactory.
			getDriver(), PL_Commons.hoverSubModule, submodule,submodule);

	clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(DriverFactory.getDriver(), PL_Commons.hoverScreen, screen,screen);

}
	//@Before(order = 2)
	@Given("Verify the User on Screen")
	public void verify_the_user_on_screen() throws InterruptedException {
		iframe.Switch_To_titleframe();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PL_Commons.textHeaderName.replace("headerName", HeaderText))));
		soft.assertEquals((GetElementText.getElementText(DriverFactory.getDriver(), PL_Commons.textHeaderName.replace("headerName", HeaderText))),HeaderText);
		soft.assertAll();
	}
	
	@Then("Verify the Filter screen is open")
	public void verify_the_filter_screen_is_open() throws InterruptedException {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iframe.Switch_To_contentframe();
		soft.assertTrue(checkElementStatus.isElementDisplayed(DriverFactory.getDriver(), PL_Commons.btnFilter), "The btnFilter Field not Displayed");
		
		soft.assertEquals((GetElementText.getElementText(DriverFactory.getDriver(), PL_Commons.btnFilter)),"Filter","Text Not Match with Expected");
		soft.assertAll();
	}

	
}
