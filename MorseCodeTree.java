import java.util.ArrayList;

/**
 * A class to create a binary tree for decoding Morse Code into English
 * @author Mike Meyers
 * @version 1.0
 *
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>{
	public TreeNode<String> root;
	
	public MorseCodeTree() {
		buildTree();
	}
	
	@Override
	public void buildTree() {
		root = new TreeNode<String>();
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
					addNode(root.getRight(), code.substring(1, code.length()), letter);
					break;
				case '.' :
					addNode(root.getLeft(), code.substring(1, code.length()-1), letter);
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
	
	@Override
	public String fetch(String code){
		//TODO
		try {
			throw new NoSuchNodeException();
		} catch (NoSuchNodeException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return null;
	}
	
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		//TODO
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
	
	@Override
	public MorseCodeTree insert (String code, String letter) {
		//TODO
		return null;
	}

	/**
	 * The recursive method to put the contents of the linked converter tree in an ArrayList<T> 
	 * LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		//TODO
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
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Exception thrown when an invalid character is found in 
	 * an input string
	 * @author Mike Meyers
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("serial")
	class NoSuchNodeException extends Exception {
		
		public NoSuchNodeException() {
			super("Invalid code found in input");
		}
		
		public NoSuchNodeException(String e) {
			super(e);
		}
	}
	
}
