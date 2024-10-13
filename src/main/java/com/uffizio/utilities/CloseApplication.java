package com.uffizio.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CloseApplication {

    public void closeRunningApplication(String application) {
      String  applicationName = ""+application.toLowerCase() + ".exe";  // Ensure the exact name of your application executable
        System.out.println("applicationName: "+applicationName);
        // Check if the process is running and close all instances
        if (isProcessRunning(applicationName)) {
            closeAllInstances(applicationName);
        } else {
            System.out.println(applicationName + " is not running.");
        }
    }

    // Method to check if a process is running
    private static boolean isProcessRunning(String processName) {
        try {
            // Run the 'tasklist' command to get a list of running processes
            Process process = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // Loop through each line to check for the process
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(processName.toLowerCase())) {
                    return true; // Process is found
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Process not found
    }

    // Method to close all instances of an application using 'taskkill' command
    private static void closeAllInstances(String processName) {
        try {
            // Use taskkill to force close all instances of the application by its process name
            Process process = Runtime.getRuntime().exec("taskkill /F /IM " + processName);
            int exitCode = process.waitFor(); 
            if (exitCode == 0) {
                System.out.println("All instances of " + processName + " have been closed.");
            } else {
                System.out.println("Failed to close instances of " + processName + ". Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
