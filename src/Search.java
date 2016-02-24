import java.awt.Point;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
	/*
	private static Vector<Point> RBFS(Color c) {
		Vector<Point> ret = null;
		BFNode n = new BFNode(c.getStart());
		while(ret == null) {
			ret = RBFTS(n, c, -1);
		}
		return ret;
	}
	
	private static BFNode RBFTS(BFNode n, Color c, int limit) {
		if(n.getPoint() == c.getEnd()) {
			return n;
		}
		Vector<Point> adjacentPs = adjacentPoints(n.getPoint(), c.getDebugCharacter());
		Vector<BFNode> successors = new Vector<BFNode>();
		for(Point p : adjacentPs) {
			successors.addElement(new BFNode(n, p));
		}
		if(successors.isEmpty()) {
			return null;
		}
		for(BFNode bn : successors) {
			bn.setF(manhattanDistance(bn.getPoint(), c.getEnd()) + manhattanDistance(bn.getPoint(), c.getStart()));
		}
		
		
	}
	*/
	
	public static Vector<Point> AStar(Color c) {
		// Comparator for Priority Queue
		Comparator<BFNode> comparator = new BFNodeComparator();
		
		// Open Closed Sets
		PriorityQueue<BFNode> openSet = new PriorityQueue<BFNode>(10, comparator);
		Vector<Point> closedSet = new Vector<Point>();
		
		// Declarations for variables later on
		Vector<Point> adjacentPs = new Vector<Point>();
		Vector<Point> ret = new Vector<Point>();
		BFNode current = new BFNode(c.getStart());
		BFNode temp = new BFNode(c.getStart());
		HashMap<Point, Point> cameFrom = new HashMap<Point, Point>();
		
		// G and F scores
		HashMap<Point, Integer> gScore = new HashMap<Point, Integer>();
		HashMap<Point, Integer> fScore = new HashMap<Point, Integer>();
		int tenativeGScore;
		
		// Initial scores for the start
		gScore.put(c.getStart(), 0);
		fScore.put(c.getStart(), euclidianDistance(c.getStart(), c.getEnd()));
		
		// Add start
		openSet.add(new BFNode(c.getStart()));
		
		// While openSet still has things
		while(!(openSet.isEmpty())) {
			// Remove the top element
			current = openSet.remove();
			
			// If current is the end
			if(current.getPoint() == c.getEnd()) {
				return reconstructPath(cameFrom, current);
			}
			
			// If point is already evaluated
			if(closedSet.contains(current.getPoint())) {
				continue;
			}
			
			// Add it to the closed set
			closedSet.add(current.getPoint());
			
			// Calculate adjacent
			adjacentPs = adjacentPoints(current.getPoint(), c.getDebugCharacter());
			
			// For each adjacent point
			for(Point p : adjacentPs) {
				// If it's in the closed set
				if(closedSet.contains(p)) {
					continue;
				}
				
				tenativeGScore = gScore.get(current.getPoint()) + 1; 
				temp = new BFNode(current, p);
				openSet.add(temp);
				
				if(gScore.get(temp.getPoint()) != null) {
					if(gScore.get(temp.getPoint()) <= tenativeGScore) {
						continue;
					}
				}
				
				cameFrom.put(temp.getPoint(), current.getPoint());
				gScore.put(temp.getPoint(), tenativeGScore);
				fScore.put(temp.getPoint(), gScore.get(temp.getPoint()) + euclidianDistance(temp.getPoint(), c.getEnd()));
				temp.setG(gScore.get(temp.getPoint()));
				temp.setF(fScore.get(temp.getPoint()));
				
			}
		}
		
		return ret;
	}
	
	
	private static Vector<Point> reconstructPath(HashMap<Point, Point> cameFrom, BFNode current) {
		Vector<Point> path = new Vector<Point>();
		path.add(current.getPoint());
		while(cameFrom.containsKey(current.getPoint())) {
			current = new BFNode(cameFrom.get(current.getPoint()));
			path.add(current.getPoint());
		}
		return path;
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
	
	private static int manhattanDistance(Point a, Point b) {
		return (int) (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()));
	}
	
	private static int euclidianDistance(Point a, Point b) {
		double dx = Math.abs(a.getX() - b.getX());
		double dy = Math.abs(a.getY() - b.getY());
		return (int) Math.sqrt(dx * dx + dy * dy);
	}
	
	
}
