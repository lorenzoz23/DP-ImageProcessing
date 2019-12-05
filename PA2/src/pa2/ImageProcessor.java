package pa2;

/**
 * 
 * @author Lorenzo Zenitsky, Gabrielle Johnston
 *
 */

public class ImageProcessor {
	private static Picture picture = null;
	
	static Picture reduceWidth(int x, String inputImage) {
		picture = new Picture(inputImage);
		
		return picture;
	}
	
	private int dist(int[] p, int[] q) {
		if(p.length > 3 || q.length > 3) {
			return -1;
		}
		
		int x = p[0] - q[0];
		int y = p[1] - q[1];
		int z = p[2] - q[2];
		return (x * x) + (y * y) + (z * z);
	}
	
	private int importance(int row, int col) { // possibly need to check if column is less than 0 or more than the width - 1
		int rgb = picture.getRGB(col, row);
		int r = (rgb >> 16) & 0xff;
		int g = (rgb >> 8) & 0xff;
		int b = (rgb) & 0xff;
		int[] og = { r, g, b };
		
		if(col == 0) {
			int[] q = getRGBValues(picture.getRGB(col + 1, row));
			
			return dist(og, q);
		}
		else if(col == picture.width() - 1) {
			int[] q = getRGBValues(picture.getRGB(col - 1, row));
			
			return dist(og, q);
		}
		else {
			int[] p = getRGBValues(picture.getRGB(col - 1, row));
			int[] q = getRGBValues(picture.getRGB(col + 1, row));
			
			return dist(p, q);
		}
		
	}
	
	private int[] getRGBValues(int rgb) {
		int[] values = new int[3];
		
		values[0] = (rgb >> 16) & 0xff;
		values[1] = (rgb >> 8) & 0xff;
		values[2] = (rgb) & 0xff;
		
		return values;
	}
}
