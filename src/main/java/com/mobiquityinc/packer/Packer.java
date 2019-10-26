package com.mobiquityinc.packer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.TestCase;

/**
 * Package Challenge solution.
 * The challenge is solved using the Dynamic Programming solution
 * for the Knapsack problem.
 * Input assumptions:
 * Package capacity - Float with up to 2 decimal places
 * Item's weight - Float with up to 2 decimal places
 * Item's cost - Integer
 */
public class Packer {
	
	private ItemPacker itemPacker;
	
	@SuppressWarnings("unused")
	private Packer() {
	}
	
	public Packer(ItemPacker itemPacker) {
		super();
		this.itemPacker = itemPacker;
	}
	
	public static String pack(String filePath) throws APIException {
		ItemPacker itemPacker = new ItemPacker();
		Packer packer = new Packer(itemPacker);
		return packer.procesFile(filePath);
	}

	public String procesFile(String filename) throws APIException {
		String result = "";
		
		try {
			List<TestCase> testCases = Utils.readFile(filename);
			
			result = testCases.stream()
				.map(parsedLine -> itemPacker.getItems(parsedLine.packageWeightLimit, parsedLine.optionalItems))
				.map(this::resultToString)
				.collect(Collectors.joining("\n"));
			
		} catch (IOException e) {
			throw new APIException(e.getMessage());
		}
		return result;
	}
	
	/**
	 * Convert a test case result to the required output
	 */
	private String resultToString(List<Integer> items){
		if (items == null || items.isEmpty()) {
			return "-";
		} else {
			return items.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(","));
		}
	}

}
