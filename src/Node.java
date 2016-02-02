import java.awt.Point;

public class Node {
	private Node n;
	private Point p;
	
	public Node(Point p) {
		this.n = null;
		this.p = p;
	}
	
	public Node(Node n, Point p) {
		this.n = n;
		this.p = p;
	}
	
	public Node getPrev() {
		return this.n;
	}
	
	public Point getPoint() {
		return this.p;
	}
}
