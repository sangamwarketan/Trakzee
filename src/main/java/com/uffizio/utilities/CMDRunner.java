package com.uffizio.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CMDRunner {

    public static boolean openCMDAndRunBatFile(String batFileAddress, String batchFileName) {
        StackTraceElement stackTraceElement[] = Thread.currentThread().getStackTrace();
        String callerMethodName = stackTraceElement[2].getMethodName();
        System.out.println("Method called setFileDirectoryAndName & caller method name: " + callerMethodName);

        try {
            System.out.println("CMD directory address: " + batFileAddress);

            // Construct the command to change directory and execute the batch file
            String command = "cd /d " + batFileAddress + " && " + batchFileName;

            // Create and start the Command Prompt process
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", command);
            Process process = processBuilder.start();

            // Create a new thread to wait for the process to finish and close the cmd window
            new Thread(() -> {
                try {
                    // Wait for the process to complete
                    process.waitFor();

                    // Get the PID of the cmd process
                    String cmdPid = getCmdProcessId(process);

                    // Kill the cmd process using its PID
                    if (cmdPid != null) {
                        String killCommand = "taskkill /F /PID " + cmdPid;
                        Runtime.getRuntime().exec(killCommand);
                        System.out.println("Closed cmd window with PID: " + cmdPid);
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Return true if the process was started
            return process.isAlive();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getCmdProcessId(Process process) {
        try {
            // Get the process ID of the cmd process
            String line;
            String pid = null;
            String command = "wmic process where (CommandLine like '%cmd.exe%') get ProcessId";
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && line.matches("\\d+")) {
                    pid = line;
                }
            }
            reader.close();
            return pid;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String batFileAddress = "C:\\Users\\koiri\\Downloads\\LeonardoAI";
        String batchFileName = "AutoIT.bat";
        boolean result = openCMDAndRunBatFile(batFileAddress, batchFileName);
        System.out.println("Batch file execution result: " + result);
    }
}
