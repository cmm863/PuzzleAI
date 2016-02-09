import java.awt.Point;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;


public class Search {
	public static Vector<Point> BFTS(HashMap<Point, Tile> map, Color c) {
		Vector<Point> ret = new Vector<Point>();
		Vector<Point> adjacentPs = new Vector<Point>();
		LinkedList<BFNode> frontier = new LinkedList<BFNode>();
		BFNode currentNode = new BFNode(null, c.getStart());
		frontier.add(currentNode);
		while(currentNode.getPoint() != c.getEnd()) {
			for(Point p : adjacentPs) {
				frontier.add(new BFNode(currentNode, p));
			}
			currentNode = frontier.pop();
			adjacentPs = adjacentPoints(currentNode.getPoint(), c.getDebugCharacter());
		}
		
		while(currentNode.getPrev() != null) {
			ret.add(currentNode.getPoint());
			currentNode = currentNode.getPrev();
		}
		
		return ret;
	}
	
	public static Vector<Point> IDDFTS(Color c) {
		Vector<Point> ret = null;
		int iterativeDepth = 0;
		while(ret == null) {
			ret = DLS(c, iterativeDepth);
			iterativeDepth++;
		}
		return ret;
	}
	
	private static BFNode DFTS(BFNode n, Color c, int depth) {
		if(n.getPoint() == c.getEnd()) {
			return n;
		} else if(depth == 0) {
			return null;
		} else {
			Vector<Point> adjacentPs = adjacentPoints(n.getPoint(), c.getDebugCharacter());
			for(Point p : adjacentPs) {
				BFNode child = new BFNode(n, p);
				BFNode result = DFTS(child, c, depth - 1);
				if(result != null) {
					return result;
				}
			}
			return null;
		}
	}
	
	private static Vector<Point> DLS(Color c, int depth) {
		BFNode n = new BFNode(c.getStart());
		Vector<Point> ret = new Vector<Point>();
		n = DFTS(n, c, depth);
		if(n != null) {
			while(n.getPrev() != null) {
				ret.add(n.getPoint());
				n = n.getPrev();
			}
			return ret;
		}
		return null;
	}
	
	private static Vector<Point> adjacentPoints(Point p, char colorChar) {
		Vector<Point> ret = new Vector<Point>();
		Tile[] tiles = new Tile[4];
		tiles[0] = Game.map.get(new Point(p.x + 1, p.y));
		tiles[1] = Game.map.get(new Point(p.x - 1, p.y));
		tiles[2] = Game.map.get(new Point(p.x, p.y + 1));
		tiles[3] = Game.map.get(new Point(p.x, p.y - 1));
		
		for(Tile t : tiles) {
			if(t != null && t.getDebugChar() == 'e' || t != null && t.getDebugChar() == colorChar) {
				ret.addElement(t.getPoint());
			}
		}
		
		return ret;
	}
}
