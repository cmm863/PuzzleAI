import java.io.BufferedReader;
import java.io.FileReader;

public class Driver {

	public static void main(String[] args) {
		// The name of the file to open
		String fileName = args[0];
		
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
			
			// Print map
			System.out.println("Map:");
			System.out.println(g.debugMap());
			
			// Print colors
			System.out.println("Colors:");
			System.out.println(g.debugColors());
			
			// Close BufferedReader
			bufferedReader.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
