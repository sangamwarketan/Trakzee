package com.uffizio.stepDefinationLogin;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.uffizio.PL_LoginAndHome.PL_Home;
import com.uffizio.PL_LoginAndHome.PL_Login;
import com.uffizio.driverFactory.DriverFactory;
import com.uffizio.utilities.CheckElementStatus;
import com.uffizio.utilities.ClickOnAnyButton;
import com.uffizio.utilities.ConfigReader;
import com.uffizio.utilities.GenericReusableMethods;
import com.uffizio.utilities.GetElementText;
import com.uffizio.utilities.IframesOfApplication;
import com.uffizio.utilities.SetDataInTextInputField;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepLogin{

	GenericReusableMethods generic =new GenericReusableMethods();
	IframesOfApplication iframe=new IframesOfApplication();
	SoftAssert soft =new SoftAssert();
	ConfigReader config;
	Properties prop;
	
	ClickOnAnyButton clickOnAnyButton =new ClickOnAnyButton();
	static CheckElementStatus checkElementStatus = new CheckElementStatus();
	SetDataInTextInputField setDataInTextInputField =new SetDataInTextInputField();
	private static Logger logger = LogManager.getLogger(StepLogin.class);

	
	@Given("User is on login page")
	public void user_is_on_login_page() {
		Assert.assertEquals(DriverFactory.getDriver().getTitle(),"Login");

	}
	@When("We are Check All the Buttons and Input Field Are visible and Working")
	public void we_are_check_all_the_buttons_and_input_field_are_visible_and_working() {
		config=new ConfigReader();
		prop=config.init_prop();
		soft.assertTrue(checkElementStatus.isElementDisplayed(DriverFactory.getDriver(), PL_Login.fieldUsername), "The fieldUsername Field not Displayed");

		soft.assertTrue(checkElementStatus.isElementDisplayed(DriverFactory.getDriver(), PL_Login.fieldPassword), "The fieldPassword Field not Displayed");
	
		soft.assertTrue(checkElementStatus.isElementDisplayed(DriverFactory.getDriver(), PL_Login.btnForgetPassword), "The btnForgetPassword Field not Displayed");

		soft.assertTrue(checkElementStatus.isElementDisplayed(DriverFactory.getDriver(), PL_Login.btnGetMobileApp), "The btnGetMobileApp Field not Displayed");
	
		soft.assertAll();
	}

	@Then("user enter {string} and {string}")
	public void user_enter_and(String Username, String Password) throws InterruptedException {
		config=new ConfigReader();
		prop=config.init_prop();

		if(prop.getProperty("Server").equalsIgnoreCase("QA")) {
			Username=prop.getProperty("QAAdminUsername");
			Password=prop.getProperty("QAAdminPassword");
			
			String[] inputFieldNames = {"Username","Password"};
			String[] inputFieldLocators = {PL_Login.fieldUsername,PL_Login.fieldPassword};
			String[] inputFieldValues = {Username,Password};
			setDataInTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(DriverFactory.getDriver(), inputFieldNames, inputFieldLocators, inputFieldValues);
			
		}
		if(prop.getProperty("Server").equalsIgnoreCase("Demo")) {
			Username=prop.getProperty("DemoAdminUsername");
			Password=prop.getProperty("DemoAdminPassword");
			setDataInTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(DriverFactory.getDriver(), "Username", PL_Login.fieldUsername, Username);
			
			setDataInTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(DriverFactory.getDriver(), "Password", PL_Login.fieldPassword, Password);
		}
		if(prop.getProperty("Server").equalsIgnoreCase("Live")) {
			Username=prop.getProperty("LiveAdminUsername");
			Password=prop.getProperty("LiveAdminPassword");
setDataInTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(DriverFactory.getDriver(), "Username", PL_Login.fieldUsername, Username);
			
			setDataInTextInputField.callMeToFillDataIntoTextInputFieldWithNameAndXpathAndValue(DriverFactory.getDriver(), "Password", PL_Login.fieldPassword, Password);
			}
	}

	@Then("Click on Login Button")
	public void click_on_login_button() throws InterruptedException {
		clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(DriverFactory.getDriver(), "LoginBTN", PL_Login.btnLogin);
		
	}

	@Then("User Handled Alert popup")
	public void user_handled_alert_popup() {
		if(prop.getProperty("Browser").equalsIgnoreCase("chrome") ||prop.getProperty("HeadLess").equalsIgnoreCase("YES") ) {
			if(generic.isAlertPresent(05)) {
				generic.acceptAlert();
			}}
	}

	@Then("User on main page")
	public void user_on_main_page() throws InterruptedException {
		iframe.Switch_To_divframe();
//		if(prop.getProperty("Browser").equalsIgnoreCase("chrome") ||prop.getProperty("HeadLess").equalsIgnoreCase("YES") ) {
//
//			try{
//			clickOnAnyButton.callMeToClickOnAnyButtonWithNameAndXpath(DriverFactory.getDriver(), "Alert close", PL_Home.btnAlertJClose);		
//			}
//			catch(Exception e) {
//				logger.info("Pop up not Present");
//			}
//		}
		GetElementText.getElementTextByAttribute(DriverFactory.getDriver(), PL_Home.btnUser, "title");	
		
	}

}
