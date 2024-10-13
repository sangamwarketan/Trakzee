package com.uffizio.PL_Commons;

public class PL_Commons {
	static String uniqueVisibleText;

	// User Icon and Notifications
	public static final String addressUserIcon = "//div[@id='tree-user']//span[@title='User']";
	public static final String addressNotifications = "//div[@id='tree-user']//span[@title='Notifications']";

	// User Menus
	public static final String addressChangePassword = "//div[@class='user_menu']//a[text()='Change Password']";
	public static final String addressChangeLanguage = "//div[@class='user_menu']//a[text()='Change Language']";
	public static final String addressCustomization = "//div[@class='user_menu']//a[text()='Customisation']";
	public static final String addressSetUser = "//div[@class='user_menu']//a[text()='Set Subuser']";
	public static final String addressChangePasswords = "//div[@class='user_menu']//a[text()='Applications']";
	public static final String addresslogout = "(//a[@id='username-a'])[2]";

	// Left Navigation Panel
	public static final String addressNavigationDashboard = "//div[@id='tree-module']//button[@title='Dashboard']";
	public static final String addressNavigationTracking = "//div[@id='tree-module']//button[@title='Tracking']";
	public static final String addressNavigationChart = "//div[@id='tree-module']//button[@title='Chart']";
	public static final String addressNavigationReports = "//div[@id='tree-module']//button[@title='Reports']";
	public static final String addressNavigationSettings = "//div[@id='tree-module']//button[@title='Settings']";
	public static final String addressNavigationDownlaods = "//div[@id='tree-download']//span[@title='Cloud  Download']";

	// MainModule
	public static final String hoverMainModule = "//div[@id='tree-module']//button[@title='YourText']";
	// SubModule
	public static final String hoverSubModule = "//ul[@class='subMenuUl scrollable-content']//span[@title='YourText'][normalize-space()='YourText']";
	// Screens
	public static final String hoverScreen = "//ul[@class='subMenuUl scrollable-content']//li//div[@id='deepMenu']//ul//li[@title='YourText']//a[@href='#']//span[@class='nav-text ng-binding'][normalize-space()='YourText']";

	// Header
	public static final String textHeaderName = "//label[text()='headerName']";
	public static final String btnApply = "//div[@class='objectsearchdropdown_container']//div//a[@id='filter_apply']";
	public static final String listHeaderIcons = "//td[@class='report_date_info']//a";
	public static final String btnFilter = "//a[@title='Filter']";
	public static final String btnHelp = "//a[@title='Help']";
	public static final String btnSetting = "//a[@title='Settings']";
	public static final String btnScheduleReport = "//a[@title='Schedule']";
	public static final String btnFavorite = "//a[@title='Favorite Report']";
	public static final String btnAddFavorite = "//a[@title='Add Favourite Report']";
	public static final String btnSearch = "//a[@title='Search Screen']";

	// Bottom
	public static final String listBottomIcons = "//td[@class='bottom-icon-box']//a[not(@style='display: none;') and not(@style='display:none;')]";
	public static final String btnReset = "//a[@title='Reset']";
	public static final String btnExcelDownload = "//a[@title='Excel Download']";
	public static final String btnPDFDownload = "//a[@title='Download in PDF format']";
	public static final String btnCSVDownload = "//a[@title='Download in CSV format']";
	public static final String searchBoxBottom = "//input[@name='searchterm']";
	public static final String searchfilterDropdown = "//select[@name='searchfield']//select[@name='searchfield']";
	public static final String searchIconBottom = "//a[@class='navItem ft-icon icon bt-bottomsearch']";
	public static final String iconRecordPerPage = "//select[@id='recordsperpages']";
	public static final String pageCounter = "//select[@id='pagecounter']";
	public static final String btnArrowNextPage = "//span[@id='F2']";
	public static final String btnArrowPreviousPage = "//span[@id='F1']";

	// Setting
	public static final String btnDeleteSettings = "//span[@id='popup-delete-btn']";
	public static final String listColumnNameForSummary = "//table[@id='dataTable']//thead//tr[@class='z-columns'][1]//td[@class='TableRowHeader']//div[@id='__']//a";
	public static final String listLHSFieldsOfFilter = "//tbody//tr//td[@align=\\\"left\\\" ]//div[@class='filter-caption' and not(@title=\\\"DATETIMEPICKER\\\")]";
	public static final String btnDeleteFilter = "//a[@id='deleteTrakzeeFilterSetting']";
	public static final String btnsaveFilter = "//a[@id='saveTrakzeeFilterSetting']";
	public static final String btnUpdateFilter = "//a[@id='updateTrakzeeFilterSetting']";
	public static final String textOfPopupMessage = "//span[@class='content']";
	public static final String btnPopupJClose = "//span[@class='jclosebtn']";
	public static final String btnVissibleText = "//*[normalized-space()='" + uniqueVisibleText + "']";

	// Filter
	public static final String labelDateSelection = "//div[@id='date-selection-label']";
	public static final String labelTimeRange = "//label[@id='object-selection-time-label']";
	public static final String labelObjectSelection = "//div[@class='objectsearchdropdown_container']//div[@id='object-selection-label']";
	public static final String listLHSBtnOfFilter = "//div[@id='filter_setting_btn_container']//a";
	public static final String listRHSBtnOfFilter = "//div[@class='objectsearchdropdown_container']//div//div[@id='report_btn_container']//a//span";
//	public static final String pageCounter="";

//	public static final String pageCounter="";
//	public static final String pageCounter="";
//	public static final String pageCounter="";

}
