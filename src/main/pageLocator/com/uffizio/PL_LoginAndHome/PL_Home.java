package com.uffizio.PL_LoginAndHome;

public class PL_Home {

	public static final String btnUser = "//span[@id='user_info_btn']";

	public static final String btnAlertJClose = "//div[@class='jalert row-flex']//div[@id='jalert_1']//div//span[@class='jclosebtn']";

	// Logo
	public static final String btnlogoTrakzee = "//div[@id='tree-logo']";

	// User Icon and Notifications
	public static final String btnUserIcon = "//div[@id='tree-user']//span[@title='User']";
	public static final String btnNotifications = "//div[@id='tree-user']//span[@title='Notifications']";

	// User Menus
	public static final String btnChangePassword = "//div[@class='user_menu']//a[text()='Change Password']";
	public static final String btnChangeLanguage = "//div[@class='user_menu']//a[text()='Change Language']";
	public static final String btnCustomization = "//div[@class='user_menu']//a[text()='Customisation']";
	public static final String btnSetUser = "//div[@class='user_menu']//a[text()='Set Subuser']";
	public static final String btnChangePasswords = "//div[@class='user_menu']//a[text()='Applications']";
	public static final String btnLogout = "(//a[@id='username-a'])[2]";

	// Left Navigation Panel
	public static final String btnNavigationDashboard = "//div[@id='tree-module']//button[@title='Dashboard']";
	public static final String btnNavigationTracking = "//div[@id='tree-module']//button[@title='Tracking']";
	public static final String btnNavigationChart = "//div[@id='tree-module']//button[@title='Chart']";
	public static final String btnNavigationReports = "//div[@id='tree-module']//button[@title='Reports']";
	public static final String btnNavigationSettings = "//div[@id='tree-module']//button[@title='Settings']";
	public static final String btnNavigationDownlaods = "//div[@id='tree-download']//span[@title='Cloud  Download']";

	//List of naviations
	public static final String listMainMenus = "//div[@id='tree-module']//span[not(ancestor::div[@id='subMenu'])]/following-sibling::span";
	// Top Level Sub Menus List[Level-1]
	public static final String ListSubMenus = "//div[@id='subMenu']//li[not(ancestor::div[@id='deepMenu'])]";

	// Inner Lever Deep Menus List[Level-2]
	public static final String ListDeepMenus = "//div[@id='deepMenu']//li";
}
