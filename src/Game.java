import java.awt.Point;
import java.util.HashMap;

public class Game {
	private int colors;		// Number of colors in game
	private int n;			// NxN game board
	private HashMap<Point, Tile> map; // Board
	private HashMap<Character, Color> colorMap; // Colors
	
	public Game() {
		// Load information
		this.colors = 0;
		this.n = 0;
		this.map = new HashMap<Point, Tile>();
		this.colorMap = new HashMap<Character, Color>();
		
		// Debug information
		System.out.println(this.debugString(true));
	}
	
	public Game(int colors, int n) {
		// Load information
		this.colors = colors;
		this.n = n;
		this.map = new HashMap<Point, Tile>();
		this.colorMap = new HashMap<Character, Color>();
		
		// Debug information
		System.out.println(this.debugString(true));
	}
	
	public Game(String fileString) {
		// Information comes in from file
		String[] info = fileString.split(" ");
		this.n = Integer.valueOf(info[0]);
		this.colors = Integer.valueOf(info[1]);
		this.map = new HashMap<Point, Tile>();
		this.colorMap = new HashMap<Character, Color>();
		
		// Debug information
		System.out.println(this.debugString(true));
	}
	
	public void loadRow(String fileString, int rowNumber) {
		Point p;
		Tile t;
		Color c;
		String[] columns = fileString.split(" ");
		
		// For each column in the row
		for(int i = 0; i < columns.length; i++) {
			System.out.println(columns[i]);
			p = new Point(i, rowNumber-1);
			t = new Tile(p, columns[i].charAt(0));
			
			// Add to map
			map.put(p, t);
			if(columns[i].charAt(0) != 'e') {
				if(colorMap.get(columns[i].charAt(0)) == null) {
					c = new Color();
					c.setStart(p);
					c.setDebugCharacter(columns[i].charAt(0));
					this.colorMap.put(columns[i].charAt(0), c);
				} else {
					c = colorMap.get(columns[i].charAt(0));
					c.setEnd(p);
				}
			}
			
		}
	}
	
	public int getN() {
		return this.n;
	}
	
	public int getColors() {
		return this.colors;
	}
	
	private String debugString(boolean init) {
		String ret = "";
		if(init) {
			ret += "Game class instantiated.\n";
		}
		ret += "Colors: " + String.valueOf(this.colors) + "\n";
		ret += "Size: " + String.valueOf(this.n) + "\n";
		return ret;
	}
	
	public String debugMap() {
		String ret = "";
		for(int j = 0; j < this.n; j++) {
			for(int i = 0; i < this.n; i++) {
				ret += map.get(new Point(i, j)).getDebugChar() + " ";
			}
			ret += "\n";
		}
		
		return ret;
	}
	
	public String debugColors() {
		String ret = "";
		for(Color c : this.colorMap.values()) {
			ret += c.debugString() + "\n";
		}
		
		return ret;
	}
	
	public void calculatePaths() {
		for(Color c : this.colorMap.values()) {
			Search.BFTS(this.map, c);
		}
	}
}
