package com.java.test;

import com.java.src.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import org.junit.jupiter.api.Test;

class TestFileExtensionsLogCount {

	@Test
	void testSampleJson() throws Exception {
	
		//pass file name as argument
		FileExtensionsCount obj = new FileExtensionsCount();
		HashMap<String, Integer> result = obj.parseJson("log.json");
		assertNotNull(result);
		for (String keyString : result.keySet()) {
			System.out.println(keyString + ": " + result.get(keyString));
		}

		System.out.println("---------------End of test Case SampleJson Output --------------");

	}

	@Test
	void testIncorrectJsonFile() throws Exception {
	
		//pass file name as argument
		FileExtensionsCount obj = new FileExtensionsCount();
		HashMap<String, Integer> result = obj.parseJson("LogIncorrectJsonEntry.json");
		for (String keyString : result.keySet()) {
			System.out.println(keyString + ": " + result.get(keyString));
		}
		System.out.println("---------------End of test Case IncorrectJsonFile Output --------------");
	}

	@Test
	void testCountForExtensions() throws Exception {
	
		//pass file name as argument
		FileExtensionsCount obj = new FileExtensionsCount();
		HashMap<String, Integer> result = obj.parseJson("log.json");

		for (String keyString : result.keySet()) {
			if (keyString.equalsIgnoreCase("ppt"))
				assertEquals("193", result.get(keyString).toString());

			if (keyString.equalsIgnoreCase("xls"))
				assertEquals("155", result.get(keyString).toString());

			if (keyString.equalsIgnoreCase("class"))
				assertEquals("209", result.get(keyString).toString());
		}

	}

}
