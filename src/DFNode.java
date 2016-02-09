import java.awt.Point;

public class DFNode {
	private Point p;
	private int depth;
	
	public DFNode(Point p) {
		this.depth = 1;
		this.p = p;
	}
	
	public DFNode(Point p, int d) {
		this.p = p;
		this.depth = d;
	}
	
	public Point getPoint() {
		return this.p;
	}
	
	public int getDepth() {
		return this.depth;
	}
}
