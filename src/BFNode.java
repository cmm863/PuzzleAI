import java.awt.Point;

public class BFNode {
	private BFNode n;
	private Point p;
	
	public BFNode(Point p) {
		this.n = null;
		this.p = p;
	}
	
	public BFNode(BFNode n, Point p) {
		this.n = n;
		this.p = p;
	}
	
	public BFNode getPrev() {
		return this.n;
	}
	
	public Point getPoint() {
		return this.p;
	}
}
