package com.uffizio.homePage;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import com.uffizio.PL_Commons.PL_Commons;
import com.uffizio.PL_LoginAndHome.PL_Home;
import com.uffizio.driverFactory.DriverFactory;
import com.uffizio.utilities.CheckElementStatus;
import com.uffizio.utilities.ClickOnAnyButton;
import com.uffizio.utilities.ConfigReader;
import com.uffizio.utilities.GenericReusableMethods;
import com.uffizio.utilities.HoverOnElement;
import com.uffizio.utilities.IframesOfApplication;
import com.uffizio.utilities.ListComparator;
import com.uffizio.utilities.SetDataInTextInputField;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class HomePage {

	GenericReusableMethods generic = new GenericReusableMethods();
	IframesOfApplication iframe = new IframesOfApplication();
	SoftAssert softAssert = new SoftAssert();
	ConfigReader config;
	Properties prop;

	ClickOnAnyButton clickOnAnyButton = new ClickOnAnyButton();
	static CheckElementStatus checkElementStatus = new CheckElementStatus();
	SetDataInTextInputField setDataInTextInputField = new SetDataInTextInputField();
	private static Logger logger = LogManager.getLogger(HomePage.class);
	private static Logger reportLogger = LogManager.getLogger("reportLogger");
	private HoverOnElement hoverOnElement = new HoverOnElement();

	@Given("User click on UserIcon")
	public void user_click_on_user_icon() throws InterruptedException {

		// DriverFactory.getDriver().switchTo().frame("divframe");
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(DriverFactory.getDriver(), "User icon",
				PL_Commons.addressUserIcon);
		Thread.sleep(500);
	}

	@Then("User click on Logout")
	public void user_click_on_logout() {
		try {
			Thread.sleep(200);
			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(DriverFactory.getDriver(), "Logout Button",
					PL_Commons.addresslogout);
			Thread.sleep(500);
			DriverFactory.getDriver().switchTo().defaultContent();
			if (DriverFactory.getDriver().getPageSource().contains("Login to account")) {
				softAssert.assertTrue(true);
				logger.info("✅✅✅ LOGOUT DONE ...");
			} else {
				softAssert.assertTrue(false);
				logger.info("❎❎❎ LOGOUT FAILEED !!!");
			}
		} catch (Exception e) {
			logger.info("Logout Exception: " + e.getMessage());
			softAssert.assertTrue(false, "After logout it lookin for [Login with Google] text");
		}
		softAssert.assertAll();
	}
	@Given("Check the Navigation of Module {string}")
	public void check_the_navigation_of_module(String module) throws InterruptedException {
		
		String[] moduleList =module.split(",");
		for(String items : moduleList) {
			hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.hoverMainModule.replace("YourText", items.trim()), true);
		}
		
	}

	@Then("User move on Download")
	public void user_move_on_download() throws InterruptedException {
		hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.addressNavigationDownlaods, true);
	}

	@Then("User move on Notifications")
	public void user_move_on_notifications() throws InterruptedException {
		hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.addressNotifications, true);
	}

	@Then("User move on UserIcon")
	public void user_move_on_user_icon() throws InterruptedException {
		hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.addressUserIcon, true);
	}

	@Given("User move on Dashboard")
	public void user_move_on_dashboard() throws InterruptedException {
		hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.addressNavigationDashboard, false);
	}

	@Then("User move on Live Tracking")
	public void user_move_on_live_tracking() throws InterruptedException {
		hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.addressNavigationTracking, false);
	}

	@Then("User move on Reports")
	public void user_move_on_reports() throws InterruptedException {
		hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.addressNavigationReports, false);
	}

	@Then("User move on Charts")
	public void user_move_on_charts() throws InterruptedException {
		hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.addressNavigationChart, false);
	}

	@Then("User move on Setting")
	public void user_move_on_setting() throws InterruptedException {
		hoverOnElement.hoverOnElement(DriverFactory.getDriver(), PL_Commons.addressNavigationSettings, false);

	}

	@Given("User Get the list of menus {string}")
	public void user_get_the_list_of_menus(String moudles) {
		ListComparator.compareListByText(DriverFactory.getDriver(), PL_Home.listMainMenus, moudles,
			"To match the list", "");
	}

}
