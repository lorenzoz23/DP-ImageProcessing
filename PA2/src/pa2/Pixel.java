package pa2;

public class Pixel {
	private int r;
	private int g;
	private int b;
	
	public Pixel(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int[] get() {
		int[] rbg = new int[3];
		rbg[0] = r;
		rbg[1] = b;
		rbg[2] = g;
		return rbg;
	}
	
	public int getR() {
		return r;
	}
	
	public int getG() {
		return g;
	}
	
	public int getB() {
		return b;
	}
	
	@Override
	public String toString() {
		String s = "< " + getR() + " " + getG() + " " + getB() + " >";
		return s;
	}

}
