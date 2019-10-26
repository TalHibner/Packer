package com.mobiquityinc.packer;

public class Item {

	private int index;
	private int weight;
	private int cost;
	
	public Item(int index, int weight, int cost) {
		this.index = index;
		this.weight = weight;
		this.cost = cost;
	}

	public Item() {	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Item [index=" + index + ", weight=" + weight + ", cost=" + cost + "]";
	}
}
