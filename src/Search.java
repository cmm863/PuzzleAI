import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;


public class Search {
	public static Vector<Point> BFTS(HashMap<Point, Tile> map, Color c) {
		Vector<Point> ret = new Vector<Point>();
		Vector<Point> adjacentPs = new Vector<Point>();
		LinkedList<Node> frontier = new LinkedList<Node>();
		Node currentNode = new Node(null, c.getStart());
		frontier.add(currentNode);
		while(currentNode.getPoint() != c.getEnd()) {
			for(Point p : adjacentPs) {
				frontier.add(new Node(currentNode, p));
			}
			currentNode = frontier.pop();
			adjacentPs = adjacentPoints(map, currentNode.getPoint(), c.getDebugCharacter());
		}
		
		while(currentNode.getPrev() != null) {
			ret.add(currentNode.getPoint());
			currentNode = currentNode.getPrev();
		}
		
		ret.add(c.getStart());
		return ret;
	}
	
	private static Vector<Point> adjacentPoints(HashMap<Point, Tile> map, Point p, char colorChar) {
		Vector<Point> ret = new Vector<Point>();
		Tile[] tiles = new Tile[4];
		tiles[0] = map.get(new Point(p.x + 1, p.y));
		tiles[1] = map.get(new Point(p.x - 1, p.y));
		tiles[2] = map.get(new Point(p.x, p.y + 1));
		tiles[3] = map.get(new Point(p.x, p.y - 1));
		
		for(Tile t : tiles) {
			if(t != null && t.getDebugChar() == 'e' || t != null && t.getDebugChar() == colorChar) {
				ret.addElement(t.getPoint());
			}
		}
		
		return ret;
	}
}
