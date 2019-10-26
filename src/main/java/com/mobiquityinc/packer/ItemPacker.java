package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The main algorithm. Implemented according to the Dynamic Programming
 * solution for the 0/1 Knapsack problem.
 */
public class ItemPacker {
	
	public List<Integer> getItems(int capacity, List<Item> items){
		
		List<Item> adjustedItems = adjustItems(items);
		int[][] mat = buildMatrix(capacity, adjustedItems);
		return getResultItems(capacity, adjustedItems, mat);
	}

	private List<Item> adjustItems(List<Item> items) {
		
		List<Item> adjustedItems = new ArrayList<Item>(items);
		
		// Sort by weight to get the lowest weight solution
		adjustedItems.sort(Comparator.comparing(Item::getWeight));
		return adjustedItems;
	}

	/**
	 * The algorithm uses a matrix for memoization.
	 * Each row represents an item in the items list, and each column
	 * represents a possible weight between 0 and capacity.
	 * For indices i,j in the matrix, m[i][j] holds the optimal solution
	 * for items 0..i, with a weight limit of j.
	 * Eventually, m[items.size][capacity] contains the optimal solution.
	 */
	private int[][] buildMatrix(int capacity, List<Item> items) {
		int[][] mat = new int[items.size() + 1][capacity + 1];
		
		for (int i = 0; i < mat[0].length; i++) {
			mat[0][i] = 0;
		}
		
		for (int i = 1; i < mat.length; i++) {
			Item currItem = items.get(i-1);
			for (int j = 0; j < mat[i].length; j++) {
				
				// If the current item isn't taken, the value
				// is the previously calculated value for weight j
				int itemNotTaken = mat[i-1][j];
				int itemTaken = 0;
				
				// The item might be taken only if it weights
				// no more than j
				if (currItem.getWeight() <= j) {
					
					// If the item is taken, the value is item.price + the
					// optimal calculated value for weight j-item.weight
					itemTaken = mat[i-1][j-currItem.getWeight()] +
							currItem.getCost();
				}
				
				mat[i][j] = Math.max(itemTaken, itemNotTaken);
			}
		}
		return mat;
	}
	
	/**
	 * To get the result items, start from the optimal cost and go up.
	 * When the cost changes, the item should be taken. Then, continue
	 * searching for items according to the remaining weight.
	 */
	private List<Integer> getResultItems(int capacity, List<Item> items, int[][] mat) {
		List<Integer> result = new ArrayList<Integer>();
		
		int j = capacity;
		for (int i = items.size(); i > 0; i--) {
			if (mat[i][j] != 0 && mat[i][j] != mat[i-1][j]) {
				Item currItem = items.get(i-1);
				result.add(currItem.getIndex());
				j = j - currItem.getWeight();
			}
		}
		
		result.sort(Comparator.naturalOrder());
		return result;
	}
	
}
