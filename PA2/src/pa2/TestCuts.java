package pa2;

import java.util.ArrayList;
import pa2.Tuple;
import pa2.MatrixCuts;

public class TestCuts {

	public static void main(String[] args) {
		int[][] M = {{5, 7, 9, 4, 5}, {2, 3, 1, 1, 2}, 
				{2, 0, 49, 46, 8}, {3, 1, 1, 1, 1}, 
				{50, 51, 25, 26, 1}};
		
		ArrayList<Tuple> arr = new ArrayList<Tuple>();
		ArrayList<Tuple> stitch = new ArrayList<Tuple>();
		arr = MatrixCuts.widthCut(M);
		
//		for(int i=0; i<arr.size(); i++) {
//			System.out.println(arr.get(i).toString());
//		}
		System.out.println(arr.toString());
		
//		stitch = MatrixCuts.stitchCut(M);
		
//		System.out.println(stitch.toString());
//		System.out.println("Done with main");
	}


}
