package com.uffizio.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CloseCMD {
	private static final Logger logger = LogManager.getLogger(CloseCMD.class);
	
	public static void closeAllOpenCMD() {
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Method called closeAllOpenCMD & caller method name: " + callerMethodName);
		
		try {
			
			// Find the cmd processes
			String findCmdCommand = "tasklist /FI \"IMAGENAME eq cmd.exe\"";
			Process findCmdProcess = Runtime.getRuntime().exec(findCmdCommand);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(findCmdProcess.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("cmd.exe")) {
					// Extract the process ID (PID) from the tasklist output
					String[] parts = line.trim().split("\\s+");
					String pid = parts[1];
					System.out.println("Found cmd.exe with PID: " + pid);

					// Kill the cmd process
					String killCmdCommand = "taskkill /F /PID " + pid;
					Process killCmdProcess = Runtime.getRuntime().exec(killCmdCommand);
					killCmdProcess.waitFor();
					System.out.println("Killed cmd.exe with PID: " + pid);
				}
			}

			reader.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Method to record existing cmd.exe processes
	public static Set<String> recordExistingCmdProcesses() {
		
		StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
		String callerMethodName = stackTraceElement[2].getMethodName();
		logger.info("Method called recordExistingCmdProcesses & caller method name: " + callerMethodName);
		
		// Set to store PIDs of existing cmd.exe processes
		Set<String> existingCmdPIDs = new HashSet<>();

		try {
			String findCmdCommand = "wmic process where \"name='cmd.exe'\" get ProcessId";
			Process findCmdProcess = Runtime.getRuntime().exec(findCmdCommand);

			BufferedReader reader = new BufferedReader(new InputStreamReader(findCmdProcess.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().matches("\\d+")) { // Check if the line contains a PID (digits only)
					existingCmdPIDs.add(line.trim());
				}
			}
			reader.close();
			System.out.println("Recorded existing cmd.exe PIDs: " + existingCmdPIDs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return existingCmdPIDs;
	}

	// Method to close only new cmd.exe processes
	public static void closeNewCmdProcesses(Set<String> existingCMDProcessList) {
		
		try {
			String findCmdCommand = "wmic process where \"name='cmd.exe'\" get ProcessId";
			Process findCmdProcess = Runtime.getRuntime().exec(findCmdCommand);

			BufferedReader reader = new BufferedReader(new InputStreamReader(findCmdProcess.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().matches("\\d+")) { // Check if the line contains a PID (digits only)
					String pid = line.trim();
					if (!existingCMDProcessList.contains(pid)) { // Only kill cmd.exe processes not in the existing set
						System.out.println("Found new cmd.exe with PID: " + pid);

						// Kill the new cmd process
						String killCmdCommand = "taskkill /F /PID " + pid;
						Process killCmdProcess = Runtime.getRuntime().exec(killCmdCommand);
						killCmdProcess.waitFor();
						System.out.println("Killed new cmd.exe with PID: " + pid);
					}
				}
			}
			reader.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
