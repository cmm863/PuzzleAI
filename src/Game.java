
public class Game {
	private int colors;		// Number of colors in game
	private int n;			// NxN game board
	
	public Game() {
		// Load information
		this.colors = 0;
		this.n = 0;
		
		// Debug information
		System.out.println(this.initializationString());
	}
	
	public Game(int colors, int n) {
		// Load information
		this.colors = colors;
		this.n = n;
		
		// Debug information
		System.out.println(this.initializationString());
	}
	
	public Game(String fileString) {
		// Information comes in from file
		String[] info = fileString.split(" ");
		this.n = Integer.valueOf(info[0]);
		this.colors = Integer.valueOf(info[1]);
		
		// Debug information
		System.out.println(this.initializationString());
	}
	
	public int getN() {
		return this.n;
	}
	
	public int getColors() {
		return this.colors;
	}
	
	private String initializationString() {
		String ret = "";
		ret += "Game class instantiated.\n";
		ret += "Colors: " + String.valueOf(this.colors) + "\n";
		ret += "Size: " + String.valueOf(this.n) + "\n";
		return ret;
	}
}
