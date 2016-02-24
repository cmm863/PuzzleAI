import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Driver {
	public static boolean debugInfo;
	public static String heuristic;
	public static void main(String[] args) {
		// Start time
		long startTime = System.currentTimeMillis();
		long endTime, elapsedTime;
		
		// The name of the file to open
		String fileName = args[0];
		
		// Debug information
		debugInfo = Boolean.valueOf(args[1]);
		
		// Read in heuristic
		if(args.length < 3) {
			heuristic = "manhattan";
		} else if(args[2].equalsIgnoreCase("euclidean")) {
			heuristic = "euclidean";
		} else {
			System.out.println("Invalid heuristic.");
			return;
		}
		
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
		
		// Write to file
		String outputFileName = args[0].substring(0, args[0].length() - 4) + "_" + heuristic + "_solution.txt";
		File fout = new File(outputFileName);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fout);
		} catch(Exception e) {
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		// Calculate time
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		System.out.println(elapsedTime);
		try {
			bw.write(String.valueOf(elapsedTime));
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Print actions
		System.out.println(g.getActions());
		try {
			bw.write(String.valueOf(g.getActions()));
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Print paths
		System.out.println(g.pathsFormatted());
		try {
			bw.write(String.valueOf(g.pathsFormatted()));
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Print map
		System.out.println(g.debugMap());
		try {
			bw.write(String.valueOf(g.debugMap()));
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
