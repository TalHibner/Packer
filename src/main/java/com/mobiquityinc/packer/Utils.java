package com.mobiquityinc.packer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.TestCase;

public class Utils {
	
	private static final int MAX_WEIGHT_PACKAGE = 100 * 100;
    private static final int MAX_ITEMS_IN_LINE = 15;
    private static final int MAX_WEIGHT_ITEM = 100 * 100;
    private static final int MAX_COST = 100 * 100;


	protected static List<TestCase> readFile(String filename) throws IOException, APIException {
		
		List<TestCase> testCases = new ArrayList<TestCase>();
		
		BufferedReader r = new BufferedReader(new FileReader(filename));
		String line = r.readLine();
		long lineNumber = 0;
		while (line != null) {
			testCases.add(parseLine(line, lineNumber));
			line = r.readLine();
			lineNumber++;
		}
		r.close();
		return testCases;
	}
	
	/**
	 * Parse and adjust the input parameters.
	 * The Knapsack Dynamic Programming Algorithm required to use use 
	 * magic number of 100 to be able to handle floats. Therefore the
	 * weights and capacity are multiplied by 100.
	 * @param lineNumber 
	 * @throws APIException 
	 */
	protected static TestCase parseLine(String line, long lineNumber) throws APIException {
		
		String[] splited = line.split(" : ");
		
        if (splited.length != 2) {
            throw new APIException("Line must contain exactly one `:`", line, lineNumber);
        }
		
		TestCase testCase = new TestCase();
		try {
			testCase.packageWeightLimit = (int)(Float.parseFloat(splited[0]) * 100);
        } catch (NumberFormatException e) {
            throw new APIException("Left side of `:` must be a number", e, line, lineNumber);
        }
		
        if (testCase.packageWeightLimit > MAX_WEIGHT_PACKAGE || testCase.packageWeightLimit < 0) {
            throw new APIException(String.format("max weight that a package can take must be in range (0, %f)", MAX_WEIGHT_PACKAGE), line, lineNumber);
        }
        
        String[] lineData = splited[1].split(" ");

		List<Item> items = new ArrayList<Item>();
		
		// Go over the input items
		for (int i = 0; i < lineData.length; i++) {
			
			// Remove parentheses and split by ','
			String[] itemData = lineData[i]
					.substring(1, lineData[i].length()-1)
					.split(",");
			
			int index = Integer.parseInt(itemData[0]);
			int weight = (int)(Float.parseFloat(itemData[1])*100);
			// Delete any non-decimal characters and save the cost as an integer
			int cost = Integer.parseInt(itemData[2].replaceAll("[^\\d]", ""));
			
			validate(line, lineNumber, index, weight, cost);
			
			items.add(new Item(index, weight, cost));
		}
		
		testCase.optionalItems = items;
		return testCase;
	}
	
	protected static void validate(String line, long lineNumber, int index, int weight, int cost) throws APIException {
		if (index > MAX_ITEMS_IN_LINE || index < 0) {
            throw new APIException(String.format("index must be in range (1, %d)", MAX_ITEMS_IN_LINE), line, lineNumber);
        }

        if (weight > MAX_WEIGHT_ITEM || weight < 0) {
            throw new APIException(String.format("weight must be in range (0, %f)", MAX_WEIGHT_ITEM), line, lineNumber);
        }

        if (cost > MAX_COST || cost < 0) {
            throw new APIException(String.format("cost must be in range (0, %f)", MAX_COST), line, lineNumber);
        }
	}
}
