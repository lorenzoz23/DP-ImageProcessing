package pa2;

import java.util.ArrayList;
import java.util.LinkedList;

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
		Tuple[][] paths = new Tuple[M[0].length][M.length];
		int[] cost = new int[M[0].length];
		ArrayList<Tuple> minWidthCut = new ArrayList<Tuple>();
		int min = 0;
		int count = -1;
		int row = 0;
		int col = 0;
		
		// initialize first col of paths
		for (int j = 0; j < M[0].length; j++) {
			paths[j][0] = new Tuple(0, j);
			cost[j] = M[0][j];
		}

		// finds the width cut
		for (int i = 0; i < M[0].length; i++) {
			count++;
			row = 0;
			col = i;
			for (int j = 1; j < M.length; j++) {
				try {
					if (col + 1 >= M[0].length) {
						min = min(M[row + 1][col], M[row + 1][col - 1]);
						col = col - 1;
					} 
					else if (col - 1 < 0) {
						min = min(M[row + 1][col + 1], M[row + 1][col]);
					} 
					else {
						min = min(M[row + 1][col + 1], M[row + 1][col], M[row + 1][col - 1]);
					}

					cost[count] = cost[count] + min;

					if (M[row + 1][col + 1] == min) {
						paths[i][j] = new Tuple(row + 1, col + 1);
						row = row + 1;
						col = col + 1;
					} 
					else if (M[row + 1][col] == min) {
						paths[i][j] = new Tuple(row + 1, col);
						row = row + 1;
					} 
					else {
						paths[i][j] = new Tuple(row + 1, col - 1);
						row = row + 1;
						col = col - 1;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {
					if (col > M[0].length) {
						col--;
					} 
					else if (col < 0) {
						col++;
					}
				}
			}
		}
		min = cost[0]+1;
		count = 0;
		for (int i = 0; i < cost.length; i++) {
			if(min>cost[i]) {
				min = cost[i];
				count = i;
			}
		}
		
		minWidthCut.add(new Tuple(min, -1));
		for(int k=0; k<paths[0].length; k++) {
			minWidthCut.add(paths[count][k]);
		}
		
		return minWidthCut;
	}

	/*
	 * Return the min-cost stitch cut of M and its cost. The return type is an
	 * ArrayList of Tuples (ArrayList). First entry of this list is a tuple of the
	 * form <x − 1>, where x is the cost of the min-cost stitch cut. Rest of the
	 * tuples represent the min cost stitch cut
	 */
	public static ArrayList<Tuple> stitchCut(int[][] M) {
		LinkedList<Tuple>[] paths = new LinkedList[M[0].length];
		ArrayList<Tuple> minWidthCut = new ArrayList<Tuple>();
		int[] cost = new int[M[0].length];
		int count = -1;
		int row;
		int col;
		int min;
		
		
		for(int i=0; i<paths.length; i++) {
			paths[i] = new LinkedList<Tuple>();
			paths[i].add(new Tuple(0, i));
			cost[i] = M[0][i];
		}
		
		int withZ = M[0].length;
		int without = M.length;
		
		// finds the width cut
		for (int i = 0; i < M[0].length; i++) {
			count++;
			row = 0;
			col = i;
			for (int j = 1; row < M[0].length-1; j++) {
				try {
					if (col + 1 >= M[0].length) {
						min = M[row + 1][col];
						col = col - 1;
					} 
					else if (col - 1 < 0) {
						min = min(M[row + 1][col + 1], M[row + 1][col]);
					} 
					else {
						min = min(M[row + 1][col + 1], M[row + 1][col], M[row][col + 1]);
					}

					cost[count] = cost[count] + min;

					if (M[row + 1][col + 1] == min) {
						paths[i].add(new Tuple(row + 1, col + 1));
						row = row + 1;
						col = col + 1;
					} 
					else if (M[row + 1][col] == min) {
						paths[i].add(new Tuple(row + 1, col));
						row = row + 1;
					} 
					else {
						paths[i].add(new Tuple(row, col + 1));
						col = col + 1;
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) {
					if (col > M[0].length) {
						col--;
					} 
					else if (col < 0) {
						col++;
					}
				}
			}
		}

		for(int t=0; t<paths.length; t++) {
			System.out.println(paths[t]);
		}
		
		min = cost[0]+1;
		count = 0;
		for (int i = 0; i < cost.length; i++) {
			if(min>cost[i]) {
				min = cost[i];
				count = i;
			}
		}

		minWidthCut.add(new Tuple(min, -1));
		for(Tuple temp : paths[count]) {
			minWidthCut.add(temp);
		}

	

		return minWidthCut;
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
			if(a>c) {
				return c;
			}
			return a;
		} else if (a == c) {
			if(a>b) {
				return b;
			}
			return a;
		}
		if (b == c) {
			return b;
		}

		int m = min(a, b);
		return m < c ? m : c;
	}
}
