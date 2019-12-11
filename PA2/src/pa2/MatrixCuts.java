package pa2;

import java.util.ArrayList;

/**
 * 
 * @author Lorenzo Zenitsky, Gabrielle Johnston
 *
 */

public class MatrixCuts {

	/*
	 * Returns the min-cost width cut of M and its cost. The return type is an
	 * arraylist of Tuples. First entry of this list is a tuple of the form <x - 1>,
	 * where x is the cost of the min-cost width cut. Rest of the tuples represent
	 * the min cost width cut.
	 */
	public static ArrayList<Tuple> widthCut(int[][] M) {
		int cost = M[0][0];
		Tuple first = new Tuple(0, 0);
		Tuple add;
		ArrayList<Tuple> minWidthCut = new ArrayList<Tuple>();
		int row = 0;
		int col = 0;
		int min = 0;

		// Finds smallest cost of first row and adds to arraylist
		for (int i = 0; i < M[0].length; i++) {
			if (cost >= M[0][i]) {
				cost = M[0][i];
				first = new Tuple(0, i);
				col = i;
			}
		}
		minWidthCut.add(first);

		// finds the width cut
		// need to add checks for going out of bounds
		while (row + 1 < M.length) {
			try {

				if (col + 1 >= M[0].length) {
					min = min(M[row + 1][col], M[row + 1][col - 1]);
					col = col - 1;
				} else if (col - 1 <= 0) {
					min = min(M[row + 1][col + 1], M[row + 1][col]);
					col = col + 1;
				} else {
					min = min(M[row + 1][col + 1], M[row + 1][col], M[row + 1][col - 1]);
				}

				cost = cost + min;
				// System.out.println("Cost of row: "+row+", col: "+col);
				// System.out.println(M[row+1][col+1]);
				// System.out.println(M[row+1][col]);
				// System.out.println(M[row+1][col-1]);

				if (M[row + 1][col + 1] == min) {
					add = new Tuple(row + 1, col + 1);
					row = row + 1;
					col = col + 1;
				} else if (M[row + 1][col] == min) {
					add = new Tuple(row + 1, col);
					row = row + 1;
				} else {
					add = new Tuple(row + 1, col - 1);
					row = row + 1;
					col = col - 1;
				}

				minWidthCut.add(add);
			} catch (ArrayIndexOutOfBoundsException e) {
				if (col > M[0].length) {
					col--;
				} else if (col < 0) {
					col++;
				} else {
					System.out.println("M[0].length is: " + M[0].length);
					System.out.println("Something else is wrong. Col is " + col);
					System.out.println("Row is: " + row);
				}
				System.out.println(e.toString());
				// row++;
			}

		}

		// System.out.println("Final cost is " + cost);
		add = new Tuple(cost, -1);
		minWidthCut.add(0, add);

		return minWidthCut;
	}

	/*
	 * Return the min-cost stitch cut of M and its cost. The return type is an
	 * ArrayList of Tuples (ArrayList). First entry of this list is a tuple of the
	 * form <x − 1>, where x is the cost of the min-cost stitch cut. Rest of the
	 * tuples represent the min cost stitch cut
	 */
	public static ArrayList<Tuple> stitchCut(int[][] M) {
		// TODO
		return new ArrayList<Tuple>();
	}

	/**
	 * Returns the minimum of two numbers.
	 * 
	 * @param a
	 * @param b
	 * @return the minimum of two numbers.
	 */
	private static int min(int a, int b) {
		return a < b ? a : b;
	}

	/**
	 * Returns the minimum of three numbers.
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return the minimum of the three parameters.
	 */
	private static int min(int a, int b, int c) {
		if (a == b) {
			return a;
		} else if (a == c) {
			return a;
		}
		if (b == c) {
			return b;
		}

		int m = min(a, b);
		return m < c ? m : c;
	}
}
