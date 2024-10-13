package com.uffizio.utilities;

import java.io.IOException;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StartChromeWithRemoteDebugging {

    private final Logger logger = LogManager.getLogger(StartChromeWithRemoteDebugging.class);

    public void startChromeIfNotRunning(int port, String userBrowserStoreDataDirectory, String userProfile) {
        if (isPortInUse(port)) {
            System.out.println("Chrome is already running on port " + port);
        } else {
            startChromeWithRemoteDebugging(port, userBrowserStoreDataDirectory, userProfile);
        }
    }

    private boolean isPortInUse(int port) {
        try (Socket socket = new Socket("localhost", port)) {
            return true; // Port is in use
        } catch (IOException e) {
            return false; // Port is not in use
        }
    }

    private void startChromeWithRemoteDebugging(int port, String userBrowserStoreDataDirectory, String userProfile) {    	
    	try {
            String chromePath = "chrome.exe"; // Path to Chrome executable; ensure it's in the system PATH or provide full path
            String portArg = "--remote-debugging-port=" + port;
            String userDataDirArg = "--user-data-dir=" + userBrowserStoreDataDirectory;
            String profileDirArg = "--profile-directory=" + userProfile;  // Add profile-directory

            // Include both the user-data-dir and profile-directory arguments
            ProcessBuilder processBuilder = new ProcessBuilder(chromePath, portArg, userDataDirArg, profileDirArg);
            Process process = processBuilder.start();
            
            System.out.println("Chrome started on remote debugging on port " + port);
        }catch (IOException e) {
            System.out.println("Error occurred while starting Chrome: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StartChromeWithRemoteDebugging starter = new StartChromeWithRemoteDebugging();
        int port = 9222;
        String userDataDir = "C:\\Users\\koiri\\MyChromeData";  // Update the user data directory path as needed
        String userProfile = "Profile 3";
        starter.startChromeIfNotRunning(port, userDataDir, userProfile);
    }
}

