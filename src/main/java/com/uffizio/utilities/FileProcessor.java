package com.uffizio.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileProcessor {

	private final Logger logger = LogManager.getLogger(FileProcessor.class);

	public void fileProcessor(String fileAddress) {
		String filePath = fileAddress;
		Map<String, String> dataMap = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			StringBuilder valueBuilder = new StringBuilder();
			String currentKey = null;

			while ((line = reader.readLine()) != null) {
				// Remove asterisks from the lines
				line = line.replace("**", "").trim();

				if (line.startsWith("Title-output:")) {
					if (currentKey != null) {
						dataMap.put(currentKey, valueBuilder.toString().trim());
						valueBuilder.setLength(0); // Clear the builder for new content
					}
					currentKey = "Title";
					valueBuilder.append(line.substring("Title-output:".length()).trim());
				} else if (line.startsWith("Description-output:")) {
					if (currentKey != null) {
						dataMap.put(currentKey, valueBuilder.toString().trim());
						valueBuilder.setLength(0); // Clear the builder for new content
					}
					currentKey = "Description";
					valueBuilder.append(line.substring("Description-output:".length()).trim());
				} else if (line.startsWith("#Tag-output:")) {
					if (currentKey != null) {
						dataMap.put(currentKey, valueBuilder.toString().trim());
						valueBuilder.setLength(0); // Clear the builder for new content
					}
					currentKey = "Tags";
					valueBuilder.append(line.substring("#Tag-output:".length()).trim());
				} else {
					if (currentKey != null) {
						valueBuilder.append(line).append(System.lineSeparator());
					}
				}
			}

			// Add the last entry
			if (currentKey != null) {
				dataMap.put(currentKey, valueBuilder.toString().trim());
			}

			// Clear the file and write the updated content
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
				for (Map.Entry<String, String> entry : dataMap.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();

					if (key.equals("Title")) {
						writer.write("Title= " + value);
					} else if (key.equals("Description")) {
						writer.write("Description= " + value);
					} else if (key.equals("Tags")) {
						writer.write("Tags= " + value);
					}
					writer.newLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteFileContent(String filePath) throws InterruptedException {
		try (FileWriter fileWriter = new FileWriter(filePath, false)) {
			// Opening the file in "write" mode with 'false' to overwrite any existing
			// content
			fileWriter.write("");
			Thread.sleep(2000);// Writing an empty string to clear the file's content
			logger.info("File content deleted successfully.");
		} catch (IOException e) {
			logger.info("Error occurred while deleting file content: " + e.getMessage());
		}
	}

	public void deleteFiles(String deleteFileDirectory, String fileContainsMatchedString, boolean deleteAll) {

		if (deleteAll) {
			// Create a File object with the given folder path
			File folder = new File(deleteFileDirectory);

			// Check if the folder exists and is a directory
			if (folder.exists() && folder.isDirectory()) {
				// Get all files in the folder
				File[] files = folder.listFiles();

				// Check if there are files in the folder
				if (files != null && files.length > 0) {

					// Iterate through the files
					for (File file : files) {
						// If it's a file, try to delete it
						if (file.isFile()) {
							if (!file.delete()) {
								logger.info("Failed to delete file: " + file.getName());
							}
						}
					}

				} else {
					logger.info("No files to delete in the folder: " + deleteFileDirectory);
				}
			} else {
				logger.info("Folder does not exist or is not a directory: " + deleteFileDirectory);
			}
		} else {
			// Directory where files are located
			File directory = new File(deleteFileDirectory);

			// List to store matched files
			List<String> matchedFiles = new ArrayList<String>();

			// Check if the directory exists and is a directory
			if (directory.exists() && directory.isDirectory()) {
				// Get all files in the directory
				File[] files = directory.listFiles();

				// Loop through the files
				if (files != null) {
					for (File file : files) {
						// Check if the file name contains the match string
						if (file.getName().contains(fileContainsMatchedString)) {
							matchedFiles.add(file.getName());

							if (file.delete()) {
								logger.info("Deleted file: " + file.getName());
							} else {
								logger.info("Failed to delete file: " + file.getName());
							}

						}
					}
				}

				// If no files matched the criteria
				if (matchedFiles.isEmpty()) {
					logger.info("No files matched the criteria: " + fileContainsMatchedString);
				}
			} else {
				logger.info("Directory not found: " + deleteFileDirectory);
			}
		}

	}

	public static void main(String[] args) {
		// Replace with the path to your file
		FileProcessor fileProcessor = new FileProcessor();
		// fileProcessor("./src/test/resources/Facebook Command/Title Description
		// Tag.txt");
	}
}
