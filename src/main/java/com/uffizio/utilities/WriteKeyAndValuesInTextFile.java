package com.uffizio.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WriteKeyAndValuesInTextFile {

	public static Logger logger = LogManager.getLogger(WriteKeyAndValuesInTextFile.class);

	public static void main(String[] args) {
		String fileDirectoryAddress = "C:\\Users\\koiri\\Downloads\\LeonardoAI";
		ArrayList<String> fileName = new ArrayList<String>();
		fileName.add("Default_Create_a_girl_listening_a_mind_relaxing_mind_2");
		String outputFilePath = "C:\\Users\\koiri\\Downloads\\LeonardoAI\\WriteDownloadedFileInfo.txt"; // The output
																										// file name
		writeToFile(outputFilePath, fileDirectoryAddress, fileName);
	}

	public static void writeToFile(String outputFilePath, String fileDirectoryAddress, ArrayList<String> fileName) {
       
		int numberOfFiles = fileName.size();
    	try (FileWriter writer = new FileWriter(outputFilePath)) {
    		writer.write("uploadFileAddress= " + fileDirectoryAddress + "\n");
    		writer.write("uploadFileName= ");
    		for(int count = 0;count < numberOfFiles ; count++) {
    			// FileWriter without 'true' will overwrite the file, effectively deleting existing content
                if(numberOfFiles > 1) {
                	writer.write("\""+fileName.get(count) + "\""+" ");
                }else {
                	writer.write(fileName.get(count) + "\n");
                }
    	       }
    		logger.info("Data has been written to " + outputFilePath + " with file name: " + fileName);
            
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file: " + e.getMessage());
        }
    }
	
	public static void writeToFile(String fileDirectoryAddress, String keyValues) {

		try (FileWriter writer = new FileWriter(fileDirectoryAddress)) {
			writer.write("FACEBOOK TITLE, TAGS AND DESCRIPTIN FOR FILE UPLOAD \n");
			writer.write(keyValues);
		} catch (IOException e) {
			logger.error("An error occurred while writing to the file: " + e.getMessage());
		}
	}
	

	public static void writeToFile(String fileDirectoryAddress, Map<String, String> keyAndValues) {
		if (fileDirectoryAddress == null) {
            logger.error("File directory address is null.");
            return;
        }
        if (keyAndValues == null) {
            logger.error("Key and values map is null.");
            return;
        }

        try (FileWriter writer = new FileWriter(fileDirectoryAddress)) {
            for (Map.Entry<String, String> entry : keyAndValues.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    logger.warn("Map entry has null key or value: " + entry);
                    continue;
                }
                writer.write(entry.getKey() + ": " + entry.getValue() + System.lineSeparator());
                System.out.println(entry);
            }
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file: " + e.getMessage(), e);
        }
	}
	
	public static void writeToFileByBufferedWriter(String fileDirectoryAddress, Map<String, String> keyAndValues) {
    	if (fileDirectoryAddress == null) {
            logger.error("File directory address is null.");
            return;
        }
        if (keyAndValues == null) {
            logger.error("Key and values map is null.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileDirectoryAddress))) {
            for (Map.Entry<String, String> entry : keyAndValues.entrySet()) {
                if (entry.getKey() == null || entry.getValue() == null) {
                    logger.warn("Map entry has null key or value: " + entry);
                    continue;
                }
                writer.write(entry.getKey() + ": " + entry.getValue() + System.lineSeparator());
                System.out.println(entry);
            }
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file: " + e.getMessage(), e);
        }
    }

}
