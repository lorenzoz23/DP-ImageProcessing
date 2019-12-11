package pa2;

import java.util.ArrayList;

/**
 * 
 * @author Lorenzo Zenitsky, Gabrielle Johnston
 *
 */

public class MatrixCuts {


	/*
	 * Returns the min-cost width cut of M and its cost. 
	 * The return type is an arraylist of Tuples. First entry 
	 * of this list is a tuple of the form <x−1>, where x is the 
	 * cost of the min-cost width cut. Rest of the tuples represent 
	 * the min cost width cut.
	 */
	public static ArrayList<Tuple> widthCut(int[][] M) {
		int cost = M[0][0];
		Tuple first = new Tuple(0, 0);
		ArrayList<Tuple> minWidthCut = new ArrayList<Tuple>();

		// Finds smallest cost of first row and adds to arraylist
		for(int i=0; i< M.length; i++) {
			if(cost >= M[0][i]) {
				cost = M[0][i];
				first = new Tuple(0, i);
			}
		}
		minWidthCut.add(first);
		
		
		
		
		System.out.println("Final cost is " + cost);
		
		return minWidthCut;
	}

	/*
	 * Return the min-cost stitch cut of M and its cost. 
	 * The return type is an ArrayList of Tuples (ArrayList). 
	 * First entry of this list is a tuple of the form <x − 1>, 
	 * where x is the cost of the min-cost stitch cut. Rest of 
	 * the tuples represent the min cost stitch cut
	 */
	public static ArrayList<Tuple> stitchCut(int[][] M) {
		// TODO
		return null;
	}
	
	/**
	 * Returns the minimum of two numbers.
	 * @param a
	 * @param b
	 * @return the minimum of two numbers.
	 */
	private int min(int a, int b) {
		return a < b ? a : b;
	}
	
	/**
	 * Returns the minimum of three numbers.
	 * @param a
	 * @param b
	 * @param c
	 * @return the minimum of the three parameters.
	 */
	private int min(int a, int b, int c) {
		if(a == b) {
			return a;
		}
		else if(a == c) {
			return a;
		}
		if(b == c) {
			return b;
		}
		
		int m = min(a, b);
		return m < c ? m : c;
	}
}
