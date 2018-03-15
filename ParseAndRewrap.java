import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ParseAndRewrap.java
 * 
 * Reads in a file and wraps the words at a given line length
 * 
 * @author Lucas Marchand
 *
 */
public class ParseAndRewrap {
	public static void main(String[] args) {
		System.out.println("Give me the name of a text file: ");
		Scanner kbd = new Scanner(System.in);
		String input = kbd.nextLine().trim();
		File file = new File(input);
		System.out.println("What is the maximum number of output characters?: ");
		int maxOut = kbd.nextInt();
		int maxLineLength = Integer.MIN_VALUE;
		int minLineLength = Integer.MAX_VALUE;
		String out = "";
		
		kbd.close();

		try {
			Scanner fileScanner = new Scanner(file);

			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				
				Scanner lineScanner = new Scanner(line);
				
				while (lineScanner.hasNext()) {
					String word = lineScanner.next();
					
					//Check if it goes over the padding
					if ((out.length() + word.length()) > maxOut) {
						
						//keep updating the line lengths
						if(out.length()-1< minLineLength) minLineLength = out.length()-1;
						if(out.length()-1 > maxLineLength) maxLineLength = out.length()-1;
						
						System.out.println(out);
						out = "";
					}
					
					//keep adding words to it
					out = out + word + " ";
					
				}
				
				lineScanner.close();
				
			}
			
			//check the final line to be printed out
			if(out.length()-1< minLineLength) minLineLength = out.length();
			if(out.length()-1 > maxLineLength) maxLineLength = out.length()-1;
			
			System.out.println(out);
			System.out.println();
			
			fileScanner.close();
			
			System.out.println("Longest line: " + maxLineLength);
			System.out.println("Shortest line: " + minLineLength);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
