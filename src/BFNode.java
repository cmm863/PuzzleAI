import java.awt.Point;

public class BFNode {
	private BFNode n;
	private Point p;
	private int f;
	private int g;
	
	public BFNode(Point p) {
		this.n = null;
		this.p = p;
		this.f = 0;
		this.g = 0;
	}
	
	public BFNode(BFNode n, Point p) {
		this.n = n;
		this.p = p;
		this.f = 0;
		this.g = 0;
	}
	
	public BFNode getPrev() {
		return this.n;
	}
	
	public Point getPoint() {
		return this.p;
	}
	
	public void setF(int f) {
		this.f = f;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public int getF() {
		return this.f;
	}
	
	public int getG() {
		return this.g;
	}
}
