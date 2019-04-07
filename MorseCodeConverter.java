import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	private static MorseCodeTree tree = new MorseCodeTree();
	
	public MorseCodeConverter() {
		tree.buildTree();
	};
	
	/**
	 * Convert Morse Code into English
	 * @param codeFile the code to be converted
	 * @return an English representation of the argument code
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		
		//Open file. Create Scanner to read file, and an ArrayList to hold read tokens
		Scanner scanner = new Scanner(codeFile);
		String readIn = "";
		
		while (scanner.hasNext()) {
			readIn += (scanner.next());
		}
		
		//tokens is now populated with each line of the file. Close the scanner
		scanner.close();
		
		//Convert the single String into tokens
		String[] tokens = readIn.split(" ");
		
		//Create separate String to hold the output
		String outString = "";
		
		//Convert tokens to English and add to outString
		for (String token : tokens) {
			switch (token) {
				case "/" : //Convert backslashes into spaces
					outString += " ";
					break;
				case "\n" : //Add newline characters as-is
					outString += "\n";
					break;
				default: //Segments of code are converted into letters
					outString += tree.fetch(token);
					}
			}
		
		//Return populated String
		return outString;
	}
	
	/**
	 * Convert Morse Code into English
	 * @param code the code to be converted
	 * @return an English representation of the argument code
	 */
	public static String convertToEnglish(String code) {
		//Convert the single String into tokens
		String[] tokens = code.split(" ");
		
		//Create separate String to hold the output
		String outString = "";
		
		//Convert tokens to English and add to outString
		for (String token : tokens) {
			switch (token) {
				case "/" : //Convert backslashes into spaces
					outString += " ";
					break;
				case "\n" : //Add newline characters as-is
					outString += "\n";
					break;
				default: //Segments of code are converted into letters
					outString += tree.fetch(token);
					}
			}
		
		//Return populated String
		return outString;
	}
	
	/**
	 * Create a string containing all data in the Morse Code Tree
	 * in LNR order
	 * @return a String representing all data in the Morse Code Tree
	 */
	public static String printTree() {
		ArrayList<String> list = new ArrayList<>();
		
		list = tree.toArrayList();
		
		String listString = "";
		
		for(String str : list) {
			listString += str + " ";
		}
		
		return listString.trim();
	}
}
