import java.io.BufferedReader;
import java.io.FileReader;

public class Driver {

	public static void main(String[] args) {
		// The name of the file to open
		String fileName = args[0];
		
		// The current line that is referenced
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			// For each line
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			
			// Close BufferedReader
			bufferedReader.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
