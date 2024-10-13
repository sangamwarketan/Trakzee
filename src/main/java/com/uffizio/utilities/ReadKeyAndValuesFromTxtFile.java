package com.uffizio.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadKeyAndValuesFromTxtFile {
	
	public static Logger logger = LogManager.getLogger(ReadKeyAndValuesFromTxtFile.class);

	public static void main(String[] args) {
		String keyValueSeperator = "=";
		String fileAddress = "./"+"\\src\\main\\resources\\Download File info\\WriteDownloadedFileInfo.txt";
		Map<String, String> values = readValuesFromFile(fileAddress,keyValueSeperator);
		String runtimeUploadFileAddress = values.get("uploadFileAddress");
		String runtimeUploadFileName = values.get("uploadFileName");
		System.out.println("runtimeUploadFileAddress: "+runtimeUploadFileAddress);
		System.out.println("runtimeUploadFileName: "+runtimeUploadFileName);
	}

	// Method to read uploadFileAddress and uploadFileName from text.txt
	public static Map<String, String> readValuesFromFile(String fileAddress, String keyValueSeperator) {
		Map<String, String> values = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileAddress))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(keyValueSeperator);
				if (parts.length == 2) {
					values.put(parts[0], parts[1]);
				}
			}
			//logger.info(values);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return values;
	}
}
