package pa2;

import java.util.ArrayList;

/**
 * 
 * @author Lorenzo Zenitsky, Gabrielle Johnston
 *
 */

public class ImageProcessor {
	private static Picture picture;
	private static Pixel[][] M;
	
	static Picture reduceWidth(int x, String inputImage) {
		picture = new Picture(inputImage);
		fillImageMatrix();
		
		for(int i = 0; i < x; i++) {
			int[][] importanceArr = new int[picture.height()][picture.width() - i];
			findImportance(importanceArr);
			ArrayList<Tuple> minCut = MatrixCuts.widthCut(importanceArr);
			for(int j = 0; j < picture.height(); j++) {
				M[j][minCut.get(j).getY()] = null;
			}
		}
		
		return picFromMatrix(x);
	}
	
	private static void fillImageMatrix() {
		for(int i = 0; i < M.length; i++) {
			for(int j = 0; j < M[0].length; j++) {
				int rgb[] = getRGBValues(picture.getRGB(j, i));
				M[i][j] = new Pixel(rgb[0], rgb[1], rgb[2]);
			}
		}
	}
	
	private static Picture picFromMatrix(int x) {
		Picture newPic = new Picture(picture.width() - x, picture.height());
		
		for(int i = 0; i < M.length; i++) {
			for(int j = 0; j < M[0].length; j++) {
				if(M[i][j] != null) {
					newPic.setRGB(j, i, getRGBValuesFromPixel(M[i][j]));
				}
			}
		}
		return newPic;
	}
	
	private static void findImportance(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				arr[i][j] = importance(i, j);
			}
		}
	}
	
	private static int dist(Pixel p, Pixel q) {
		int x = p.getR() - q.getR();
		int y = p.getG() - q.getG();
		int z = p.getB() - q.getB();
		return (x * x) + (y * y) + (z * z);
	}
	
	private static int importance(int row, int col) { // possibly need to check if column is less than 0 or more than the width - 1
		Pixel og = M[row][col];
		
		if(col == 0) {
			return dist(og, M[row][col + 1]);
		}
		else if(col == picture.width() - 1) {
			return dist(og, M[row][col - 1]);
		}
		else {
			return dist(M[row][col - 1], M[row][col + 1]);
		}
		
	}
	
	private static int[] getRGBValues(int rgb) {
		int[] values = new int[3];
		
		values[0] = (rgb >> 16) & 0xff;
		values[1] = (rgb >> 8) & 0xff;
		values[2] = (rgb) & 0xff; // or (rgb >> 0)
		
		return values;
	}
	
	private static int getRGBValuesFromPixel(Pixel p) {
		int rgb = p.getR() + p.getG() + p.getB();
		return rgb;
	}
}
