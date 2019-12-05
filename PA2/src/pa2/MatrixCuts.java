package pa2;

import java.util.ArrayList;

/**
 * 
 * @author Lorenzo Zenitsky, Gabrielle Johnston
 *
 */

public class MatrixCuts {

	static ArrayList<Tuple> widthCut(int[][] M) {
		// TODO
		return null;
	}

	static ArrayList<Tuple> stitchCut(int[][] M) {
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
