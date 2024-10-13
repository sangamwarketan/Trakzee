//package com.facebook.projectUtility;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class StartApplicationOrBatchFile {
//
//	private final Logger logger = LogManager.getLogger(AddArgumentsInAutoITScriptFile.class);
//	
//    public void startApplicationOrBatchFile(String fileAddressWithNameAndExtention) {
//        try {
//            // Specify the path to your run.bat file
//            String batFilePath = fileAddressWithNameAndExtention; 
//            ProcessBuilder processBuilder = new ProcessBuilder(batFilePath);
//            
//            // Set the working directory (optional)
//           // processBuilder.directory(new File("D:\\Eclipse Project\\WebAutomation"));
//            
//            Process process = processBuilder.start();
//            Thread.sleep(5000);
//            
//            System.out.println("Application | Batch file executed successfully.");
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}


package com.uffizio.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StartApplicationOrBatchFile {

    private final Logger logger = LogManager.getLogger(StartApplicationOrBatchFile.class);
    
    public void startApplicationOrBatchFile(String fileAddressWithNameAndExtension) {
        try {
            // Extract the file name without path to check the process name
            String fileName = new File(fileAddressWithNameAndExtension).getName();
            
            // Check if the application or batch file is already running
            if (isProcessRunning(fileName)) {
                System.out.println("Application | Batch file is already running: " + fileName);
                return;  // If running, do not start a new instance
            }
            
            // If not running, start the application or batch file
            ProcessBuilder processBuilder = new ProcessBuilder(fileAddressWithNameAndExtension);
            
            // Optionally set the working directory
            // processBuilder.directory(new File("D:\\Eclipse Project\\WebAutomation"));
            
            Process process = processBuilder.start();
            Thread.sleep(5000);
            
            System.out.println("Application | Batch file executed successfully.");
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occurred while starting the application or batch file: " + e.getMessage());
        }
    }

    // Method to check if a process with the given name is running
    private boolean isProcessRunning(String processName) {
        try {
            // Run the 'tasklist' command to list all running processes
            Process process = Runtime.getRuntime().exec("tasklist");
            Scanner scanner = new Scanner(process.getInputStream());
            
            // Read the output and check if the process is listed
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Task running: "+line);
                if (line.toLowerCase().contains(processName.toLowerCase())) {
                    scanner.close();
                    return true;  // Process is running
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error occurred while checking if the process is running: " + e.getMessage());
        }
        return false;  // Process is not running
    }

    public static void main(String[] args) {
        StartApplicationOrBatchFile starter = new StartApplicationOrBatchFile();
        String chromeDebuggerAddress = "./" + "\\ChromeDebugger\\Chrome Debugger.bat";

        starter.startApplicationOrBatchFile(chromeDebuggerAddress);  // Update this path as needed
    }
}
