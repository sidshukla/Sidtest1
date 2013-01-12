package com.cloudreach.solution.main;

import com.cloudreach.solution.application.FinsburyInventory;
import com.cloudreach.solution.exception.FinsburyApplicationException;

public class FinsburySolution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FinsburyInventory finsburyInventory = new FinsburyInventory();
		try {
			finsburyInventory.start(args[0], args[1]);
		} catch (FinsburyApplicationException e) {
			System.out.println("There has been a problem");
			e.printStackTrace();
		}
	}
}
