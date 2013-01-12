package com.cloudreach.solution.main;

import com.cloudreach.solution.application.FinsburyInventory;

public class FinsburySolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FinsburyInventory finsburyInventory = new FinsburyInventory();
		finsburyInventory.start(args[0], args[1]);
	}
}
