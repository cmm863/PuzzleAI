import java.awt.Point;

public class Color {
	private char c;
	private Point start, end;
	
	public Color() {
		this.start = null;
		this.end = null;
		this.c = 'x';
		
		// Debug information
		System.out.println(this.debugString(true));
	}
	
	
	
	private String debugString(boolean init) {
		String ret = "";
		if(init) {
			ret += "Color class instantiated.\n";
		}
		try {
			ret += "start x: " + String.valueOf(this.start.getX() + "\n");
			ret += "start y: " + String.valueOf(this.start.getY() + "\n");
		} catch(Exception e) {
			ret += "start x: null\n";
		}
		try {
			ret += "end x: " + String.valueOf(this.end.getX() + "\n");
			ret += "end y: " + String.valueOf(this.end.getY() + "\n");
		} catch(Exception e) {
			ret += "end x: null\n";
		}
		ret += "c: " + this.c + "\n";
		
		return ret;
	}
	
	public String debugString() {
		return this.debugString(false);
	}
	
	public Point getStart() {
		return this.start;
	}
	
	public void setStart(Point p) {
		this.start = p;
	}
	
	public Point getEnd() {
		return this.end;
	}
	
	public void setEnd(Point p) {
		this.end = p;
	}
	
	public char getDebugCharacter() {
		return this.c;
	}
	
	public void setDebugCharacter(char c) {
		this.c = c;
	}
}
