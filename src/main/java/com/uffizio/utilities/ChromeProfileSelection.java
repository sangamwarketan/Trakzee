package com.uffizio.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeProfileSelection{
    public static void selectProfile(String userDirectoryData,String browserType) {
    	switch(browserType) {
    	case "chrome": {
    		
    	}
    	case "firefox": {
    		
    	}
    	case "edge":{
    		
    	}
    	}
        // Create an instance of ChromeOptions
        ChromeOptions options = new ChromeOptions();

        // Set the path to the Chrome user data directory
        // Replace "path/to/user/data" with the path to your Chrome User Data folder
        options.addArguments(userDirectoryData);

        // Specify the profile directory
        // Replace "Profile X" with the desired profile folder name, e.g., "Profile 1" or "Profile 2"
        options.addArguments("profile-directory=Profile 1");
    }
}

