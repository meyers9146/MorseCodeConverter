import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
		
		//Open file. Create Scanner to read file, and a String to hold read characters
		Scanner scanner = new Scanner(codeFile);
		String readIn = "";
		
		while (scanner.hasNext()) {
			readIn += scanner.nextLine() + "\n";
		}
		
		//readIn is now populated with each line of the file. Close the scanner
		scanner.close();
		
		return convertToEnglish(readIn.trim());
		
	}
	
	/**
	 * Convert Morse Code into English
	 * @param code the code to be converted
	 * @return an English representation of the argument code
	 */
	public static String convertToEnglish(String code) {
		//Check the characters in the String. If Invalid, throw Exception and return
		if (!isValid(code)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Invalid Characters Found");
			alert.setContentText("The input contained invalid characters and cannot be processed");
			alert.showAndWait();
			return null;
		}
		
		//Add whitespace to the string as needed to delineate words and lines
		code = addWhiteSpace(code);
		
		//Remove any double-spaces that would throw off the tokenization
		code = code.replaceAll("  ", " ");
		
		//Split the String into tokens
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
					try {
					outString += tree.fetch(token);
					}
					catch (NullPointerException e) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("Invalid Morse Code Found");
						alert.setContentText("Input contained an invalid Morse Code sequence and cannot be processed");
						alert.showAndWait();
						return null;
					}
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
		
		//Convert the tree to an ArrayList<String>
		list = tree.toArrayList();
		
		String listString = "";
		
		//Add each element of the list to the return String
		for(String str : list) {
			listString += str + " ";
		}
		
		//Return the final string
		return listString.trim();
	}
	
	/**
	 * Validate a String of characters. The only permitted characters are dots, dashes,
	 * whitespace, and backslashes
	 * @param code the code to be validated
	 * @return true if the code is valid, and false otherwise
	 */
	private static boolean isValid(String code) {

		//Check each character for validity. If any invalid character is found, the code is invalid
		for (int i = 0; i < code.length(); i++) {
			switch (code.charAt(i)) {
				case '.' : case '-' : case '\n' : case ' ' : case '/' : continue;
				default:
					return false;
			}
		}	
			//If no invalid characters are found, return true
			return true;
	}
	
	/**
	 * Add whitespace around delineating backslashes or newlines
	 * @param s the String to be manipulated
	 * @return the String with additional whitespace added
	 */
	private static String addWhiteSpace(String s) {
				
		boolean done = false;
		
		while(done == false) {
			done = true;
			
			for (int i = 1; i < s.length()-1; i++) {
				if (s.charAt(i) == '/' || s.charAt(i) == '\n') {
					//Check for a space before the character
					if (s.charAt(i-1) != ' ') {
						done = false;
						
						//Convert the String to a StringBuilder and insert a space before the character
						StringBuilder sb = new StringBuilder(s);
						sb.insert(i, ' ');
						s = sb.toString();
						
						//Decrement i to reflect the added character
						i--;
					}
					//Check for a space after the character
					else if (s.charAt(i+1) != ' ') {
						done = false;
						//Convert the String to a StringBuilder and insert a space after the character
						StringBuilder sb = new StringBuilder(s);
						sb.insert(i+1,  ' ');
						s = sb.toString();
						
						//Decrement i to reflect the added character
						i--;
					}
				}
			}
		}
		//Return the altered String
		return s;
	}

}
