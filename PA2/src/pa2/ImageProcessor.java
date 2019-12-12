package pa2;

import java.util.ArrayList;

/**
 * 
 * @author Lorenzo Zenitsky, Gabrielle Johnston
 *
 */

public class ImageProcessor {
	// global static picture
	private static Picture picture;
	// global static matrix of the picture
	private static Pixel[][] M;

	/**
	 * Reduces the width of the input image.
	 * 
	 * @param x
	 * @param inputImage
	 * @return picture of reduced width, reduced by x amount
	 */
	public static Picture reduceWidth(int x, String inputImage) {
		picture = new Picture(inputImage);
		fillImageMatrix();
		// printOutImageMatrix();
		for (int i = 0; i < x; i++) {
			int[][] importanceArr = new int[picture.height()][picture.width() - i];
			findImportance(importanceArr);
			ArrayList<Tuple> minCut = MatrixCuts.widthCut(importanceArr);
			for (int j = 0; j < picture.height(); j++) {
				M[j][minCut.get(j + 1).getY()] = null;
			}
		}
		// printOutImageMatrix();
		updateImageMatrix(x);
		return picFromMatrix(x);
	}

	/**
	 * Updates the image matrix to push each null pixel object to the last cell of
	 * the last row to be easily ignored.
	 * 
	 * @param x
	 */
	private static void updateImageMatrix(int x) {
		Pixel[][] tmp = new Pixel[M.length][M[0].length];
		int nCount = 0;
		int j = 0;
		for (int i = 0; i < M.length; i++) {
			nCount = 0;
			for (j = 0; j < M[0].length; j++) {
				if (M[i][j] != null) {
					tmp[i][j - nCount] = M[i][j];
				} else
					nCount++;
			}
			for (int k = 0; k < nCount; k++) {
				tmp[i][j - nCount + k] = null;
			}
		}
		M = tmp;
	}

	/**
	 * Helper method for printing out the image matrix.
	 */
	private static void printOutImageMatrix() {
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[0].length; j++) {
				System.out.print(M[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Creates the image matrix from the given picture.
	 */
	private static void fillImageMatrix() {
		M = new Pixel[picture.height()][picture.width()];
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[0].length; j++) {
				int rgb[] = getRGBValues(picture.getRGB(j, i));
				M[i][j] = new Pixel(rgb[0], rgb[1], rgb[2]);
			}
		}
	}

	/**
	 * Returns the finished picture with a reduced width.
	 * 
	 * @param x
	 * @return new picture from image matrix
	 */
	private static Picture picFromMatrix(int x) {
		Picture newPic = new Picture(picture.width() - x, picture.height());

		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[0].length - 1; j++) {
				if (M[i][j] != null) {
					newPic.setRGB(j, i, getRGBValuesFromPixel(M[i][j]));
				}
			}
		}
		return newPic;
	}

	/**
	 * finds the importance of each pixel in the image matrix.
	 * 
	 * @param arr
	 */
	private static void findImportance(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = importance(i, j);
			}
		}
	}

	/**
	 * Finds the distance between two pixels.
	 * 
	 * @param p
	 * @param q
	 * @return the distance between two pixels
	 */
	private static int dist(Pixel p, Pixel q) {
		int x = p.getR() - q.getR();
		int y = p.getG() - q.getG();
		int z = p.getB() - q.getB();
		return (x * x) + (y * y) + (z * z);
	}

	/**
	 * Finds the importance of a pixel at a certain row and column of the image
	 * matrix.
	 * 
	 * @param row
	 * @param col
	 * @return the importance of a pixel
	 */
	private static int importance(int row, int col) {
		Pixel og = M[row][col];

		if (col == 0) {
			return dist(og, M[row][col + 1]);
		} else if (col == picture.width() - 1) {
			return dist(og, M[row][col - 1]);
		} else {
			return dist(M[row][col - 1], M[row][col + 1]);
		}

	}

	/**
	 * Separates the given RGB value into R, G, and B values.
	 * 
	 * @param rgb
	 * @return an int array of consisting of R, G, and B values from a given RGB
	 *         value
	 */
	private static int[] getRGBValues(int rgb) {
		int[] values = new int[3];

		values[0] = (rgb >> 16) & 0xff;
		values[1] = (rgb >> 8) & 0xff;
		values[2] = (rgb) & 0xff;

		return values;
	}

	/**
	 * R, G, and B values are taken from a pixel object and added together to make a
	 * pixel's overall RGB value.
	 * 
	 * @param p
	 * @return RGB value of pixel
	 */
	private static int getRGBValuesFromPixel(Pixel p) {
		int rgb = 0x10000 * p.getR() + 0x100 * p.getG() + p.getB();
		return rgb;
	}
}
