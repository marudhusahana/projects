package com.java.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FileExtensionsCount {

	HashMap<String, Integer> fileExtensionsCount;
	HashSet<String> fileNames;

	public FileExtensionsCount() {
		this.fileNames = new HashSet<String>();
		this.fileExtensionsCount = new HashMap<String, Integer>();
	}

	/**
	 * Extracts elements from the input stream
	 * 
	 * @param in - input stream containing the JSON content
	 * @throws Exception - if stream cannot be read or the contents are ill-formed
	 *                   JSON.
	 */
	public HashMap<String, Integer> parseJson(String inputFileName) throws Exception {

		try {
			File file = new File(inputFileName);
			FileInputStream in = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String line = reader.readLine();
			while (line != null) {

				try {
					// Parse JSON using JSON Parser
					Object obj = new JSONParser().parse(line);

					// type casting obj to JSONObject
					JSONObject jsonObj = (JSONObject) obj;

					// Extract File name from the JSON Object
					String fileName = (String) jsonObj.get("nm");

					int count = 0;
					// extract the extension from file name and store in a HashMap
					if (!fileNames.contains(fileName)) {
						if (fileName.contains(".")) {
							fileNames.add(fileName);
							String extension = fileName.substring(fileName.toString().indexOf(".") + 1,
									fileName.length());

							if (fileExtensionsCount.containsKey(extension))
								count = fileExtensionsCount.get(extension);
							fileExtensionsCount.put(extension, count + 1);
						} else {
							// checks for files with No Extensions
							fileNames.add(fileName);
							if (fileExtensionsCount.containsKey("no_extension"))
								count = fileExtensionsCount.get("no_extension");
							fileExtensionsCount.put("no_extension", count + 1);
						}
					}

				} catch (Exception e) {
					System.out.println("Invalid JSON Entry. Skipping the record.");
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			throw new IllegalArgumentException("input file exception " + e);
		}
		return fileExtensionsCount;
	}
}