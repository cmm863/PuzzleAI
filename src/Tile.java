import java.awt.Point;

public class Tile {
	private Point p;
	private char c;
	
	public Tile() {
		this.p = new Point(0, 0);
		this.c = 'x';
		
		System.out.println(this.initializationString());
	}
	
	public Tile(Point p, char c) {
		this.p = p;
		this.c = c;
		
		System.out.println(this.initializationString());
	}
	
	
	
	private String initializationString() {
		String ret = "";
		ret += "Tile class instantiated.\n";
		ret += "x: " + String.valueOf(this.p.getX()) + "\n";
		ret += "y: " + String.valueOf(this.p.getY()) + "\n";
		ret += "c: " + String.valueOf(this.c) + "\n";
		return ret;
	}
	
	public char getDebugChar() {
		return this.c;
	}
}
