import java.io.BufferedReader;
import java.io.FileReader;

public class Driver {
	public static boolean debugInfo;
	public static void main(String[] args) {
		// Start time
		long startTime = System.currentTimeMillis();
		long endTime, elapsedTime;
		
		// The name of the file to open
		String fileName = args[0];
		
		// Debug information
		debugInfo = Boolean.valueOf(args[1]);
		
		// The current line that is referenced
		String line = null;
		
		// Line count
		int lineCount = 0;
		
		// Game
		Game g = new Game();
		
		try {
			// File read from file
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// For each line
			while((line = bufferedReader.readLine()) != null) {
				if(lineCount == 0) {
					g = new Game(line); 
				} else {
					g.loadRow(line, lineCount);
				}
				lineCount++;
			}
			
			if(debugInfo) {
			// Print map
				System.out.println("Map:");
				System.out.println(g.debugMap());
				
				// Print colors
				System.out.println("Colors:");
				System.out.println(g.debugColors());
			}
			
			// Calculate paths
			g.calculatePaths();
			
			if(debugInfo) {
				// Print
				System.out.println("Map:");
				System.out.println(g.debugMap());
			}
			
			// Close BufferedReader
			bufferedReader.close();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		// Calculate time
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		System.out.println(elapsedTime);
		
		// Print actions
		System.out.println(g.getActions());
	}

}
