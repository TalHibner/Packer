package com.mobiquityinc.packer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ItemPackerTest {

	private ItemPacker itemPacker;
	
	@Before
	public void setUp() throws Exception {
		itemPacker = new ItemPacker();
	}

	@Test
	public void oneItemTest() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(1, 10, 45));
		List<Integer> result = itemPacker.getItems(15, items);
		assertEquals("[1]", result.toString());
	}
	
	@Test
	public void oneItemEqualWeightTest() throws Exception {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(1, 10, 45));
		List<Integer> result = itemPacker.getItems(10, items);
		assertEquals("[1]", result.toString());
	}
	
	@Test
	public void oneItemExceedsWeightTest() throws Exception {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(1, 10, 45));
		List<Integer> result = itemPacker.getItems(9, items);
		assertEquals("[]", result.toString());
	}
	
	@Test
	public void selectAllItemsTest() throws Exception {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(1, 1, 10));
		items.add(new Item(2, 2, 20));
		items.add(new Item(3, 3, 30));
		items.add(new Item(4, 4, 40));
		items.add(new Item(5, 5, 50));
		List<Integer> result = itemPacker.getItems(20, items);
		assertEquals("[1, 2, 3, 4, 5]", result.toString());
	}
	
	@Test
	public void selectNoItemsTest() throws Exception {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(1, 10, 10));
		items.add(new Item(2, 20, 20));
		items.add(new Item(3, 30, 30));
		items.add(new Item(4, 40, 40));
		items.add(new Item(5, 50, 50));
		List<Integer> result = itemPacker.getItems(5, items);
		assertEquals("[]", result.toString());
	}
	
	@Test
	public void loswedtWeightSolutionTest() throws Exception {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(1, 9072, 13));
		items.add(new Item(2, 3380, 40));
		items.add(new Item(3, 4315, 10));
		items.add(new Item(4, 3797, 16));
		items.add(new Item(5, 4681, 36));
		items.add(new Item(6, 4877, 79));
		items.add(new Item(7, 8180, 45));
		items.add(new Item(8, 1936, 79));
		items.add(new Item(9, 676, 64));
		List<Integer> result = itemPacker.getItems(5600, items);
		assertEquals("[8, 9]", result.toString());
	}

}
