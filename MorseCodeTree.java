import java.util.ArrayList;

/**
 * A class to create a binary tree for decoding Morse Code into English
 * @author Mike Meyers
 * @version 1.0
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	private TreeNode<String> root;
	
	public MorseCodeTree() {
		buildTree();
	}
	
	@Override
	public void buildTree() {
		root = new TreeNode<String>("");
		addNode(root, ".", "e");
		addNode(root, "-", "t");
		addNode(root, "..", "i");
		addNode(root, ".-", "a");
		addNode(root, "-.", "n");
		addNode(root, "--", "m");
		addNode(root, "...", "s");
		addNode(root, "..-", "u");
		addNode(root, ".-.", "r");
		addNode(root, ".--", "w");
		addNode(root, "-..", "d");
		addNode(root, "-.-", "k");
		addNode(root, "--.", "g");
		addNode(root, "---", "o");
		addNode(root, "....", "h");
		addNode(root, "...-", "v");
		addNode(root, "..-.", "f");
		addNode(root, ".-..", "l");
		addNode(root, ".--.", "p");
		addNode(root, ".---", "j");
		addNode(root, "-...", "b");
		addNode(root, "-..-", "x");
		addNode(root, "-.-.", "c");
		addNode(root, "-.--", "y");
		addNode(root, "--..", "z");
		addNode(root, "--.-", "q");
	}
	
	@Override
	public void addNode(TreeNode<String> root, String code, String letter){
		//Create the temporary node for adding
		TreeNode<String> tempNode = new TreeNode<>(letter);
		
		if (code.length() == 1) {
			switch(code) {
				case "-" :
					root.setRight(tempNode);
					break;
				case "." :
					root.setLeft(tempNode);
					break;
				default:
					//if the character is neither a dot nor a dash, throw Exception
					try {
						throw new Exception("Something went wrong building the tree");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}//end switch
			}
		else {
			switch(code.charAt(0)) {
				case '-' :
					addNode(root.getRight(), code.substring(1), letter);
					break;
				case '.' :
					addNode(root.getLeft(), code.substring(1), letter);
					break;
				default :
					//if the character is neither a dot nor a dash, throw Exception
					try {
						throw new Exception("Something went wrong building the tree");
					} catch (Exception e) {
						e.printStackTrace();
					}
			}//end switch
		}//end else
	}
	
	/**
	 * Delete method is not supported for Morse Code Tree
	 * @throws UnsupportedOperationException as method is not supported
	 */
	@Override
	public MorseCodeTree delete(String data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Operation not supported");
	}
	
	/**
	 * Fetch the letter associated with a given string of Morse code
	 * @param code the code to be searched for within the tree
	 * @return the letter associated with the code
	 */
	@Override
	public String fetch(String code){
		
		//Call fetchNode to traverse the tree to find the indicated code
		return fetchNode(root, code);
	}
	
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		TreeNode<String> returnNode = this.root;
		
		if(code.length() == 1) {
			switch(code) {
				case "-" : 
					returnNode = root.getRight();
					return returnNode.getData();
				case "." :
					returnNode = root.getLeft();
					return returnNode.getData();
				default:
					//if the character is neither a dot nor a dash, throw Exception
					try {
						throw new Exception();
					} catch (Exception e) {
						e.printStackTrace(); //Should be unreachable if isValid() did its job
					}
			}//end switch
		}
		else {
			switch(code.charAt(0)) {
				case '-' :
					return fetchNode(root.getRight(), code.substring(1));
				case '.' :
					return fetchNode(root.getLeft(), code.substring(1));
				default:
					//if the character is neither a dot nor a dash, throw Exception
					try {
						throw new Exception();
					} catch (Exception e) {
						e.printStackTrace(); //Should be unreachable if isValid() did its job
					}
			}//end switch
		}
		
		//This code should not be reached
		return null;
	}
	
	/**
	 * Return this tree's root node
	 * @return this tree's root node
	 */
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}
	
	/**
	 * Add a node to the tree without an indicated root node.
	 * Method starts with the tree root and goes from there
	 * @param code the code to be added
	 * @param letter the letter to be added
	 */
	@Override
	public MorseCodeTree insert (String code, String letter) {

		//add the node to the tree
		addNode(root, code, letter);
		
		//return this tree object with the node added
		return this;
	}

	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {

		if (root == null) return;
		
		else {
			LNRoutputTraversal(root.getLeft(), list);
			list.add(root.getData()); //If condition prevents adding empty tree root data to list
			LNRoutputTraversal(root.getRight(), list);	
		}
	}
	
	/**
	 * Set this tree's root node
	 * @param newNode the new root for this tree
	 */
	@Override
	public void setRoot(TreeNode<String> newNode) {
		this.root = newNode;
	}
	
	/**
	 * Update function is not supported for the Morse Code Tree
	 * @return nothing
	 * @throws UnsupportedOperationException as this method is not supported
	 */
	@Override
	public MorseCodeTree update() {
		throw new UnsupportedOperationException("Operation not supported");
	}

	/**
	 * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
	 * Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked Tree
	 */
	@Override
	public ArrayList<String> toArrayList() {
		
		//Create empty ArrayList
		ArrayList<String> list = new ArrayList<>();
		
		//Pass ArrayList to traversal method
		LNRoutputTraversal(root, list);
		
		//Return the now-populated list
		return list;
	}
	

	

}
