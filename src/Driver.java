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
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			Game g;
			// For each line
			while((line = bufferedReader.readLine()) != null) {
				if(lineCount == 0) {
					g = new Game(line); 
				} else {
					System.out.println(line);
				}
				lineCount++;
			}
			
			// Close BufferedReader
			bufferedReader.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
