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
		
		//Open file. Create Scanner to read file, and an ArrayList to hold read tokens
		Scanner scanner = new Scanner(codeFile);
		String readIn = "";
		
		while (scanner.hasNext()) {
			readIn += (scanner.nextLine());
		}
		
		//tokens is now populated with each line of the file. Close the scanner
		scanner.close();
		
		return convertToEnglish(readIn);
		
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
		
		list = tree.toArrayList();
		
		String listString = "";
		
		for(String str : list) {
			listString += str + " ";
		}
		
		return listString.trim();
	}
	
	/**
	 * Validate a String of characters. The only permitted characters are dots, dashes,
	 * whitespace, and backslashes
	 * @param code the code to be validated
	 * @return true if the code is valid, and false otherwise
	 */
	private static boolean isValid(String code) {
		char[] chars = code.toCharArray();
		
		//Check each character for validity. If any invalid character is found, the code is invalid
		for (char ch : chars) {
			switch (ch) {
				case '.' : case '-' : case '\n' : case ' ' : case '/' : continue;
				default:
					return false;
			}
		}	
			//If no invalid characters are found, return true
			return true;
	}

}
