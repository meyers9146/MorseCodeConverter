/**
 * A Node class for use with MorseCodeTree.
 * Each Node contains an item of data and two references
 * to other Nodes in the tree.
 * 
 * @author Mike Meyers
 * @version 1.0
 *
 * @param <T> The type of data contained in each Node
 */
public class TreeNode<T> {
	
	private T data;
	private TreeNode<T> leftNode;
	private TreeNode<T> rightNode;
	
	/**
	 * Create an empty Node. All references are set to null
	 */
	public TreeNode() {
		data = null;
		leftNode = null;
		rightNode = null;
	}
	/**
	 * Create a new TreeNode leaf
	 * @param dataNode the data to be contained in the TreeNode
	 */
	public TreeNode(T dataNode) {
		data = dataNode;
		leftNode = null;
		rightNode = null;
	}
	
	/**
	 * Create a new TreeNode as a clone of another TreeNode
	 * @param node
	 */
	public TreeNode(TreeNode<T> node) {
		data = node.getData();
		leftNode = null;
		rightNode = null;
	}
	
	/**
	 * Set this TreeNode's left child reference
	 * @param node this node's new left child
	 */
	public void setLeft(TreeNode<T> node) {
		leftNode = node;
	}
	
	/**
	 * Set this TreeNode's right child reference
	 * @param node this node's new right child
	 */
	public void setRight(TreeNode<T> node) {
		rightNode = node;
	}
	
	/**
	 * Get this node's leftNode reference
	 * @return this node's left child
	 */
	public TreeNode<T> getLeft() {
		return leftNode;
	}
	
	/**
	 * Get this node's rightNode reference
	 * @return this node's right child
	 */
	public TreeNode<T> getRight() {
		return rightNode;
	}
	
	/**
	 * Return the data within this TreeNode
	 * @return this node's data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Determine if this node is a leaf of the tree
	 * @return true if this node is a leaf, and false otherwise
	 */
	public boolean isLeaf() {
		if (leftNode == null && rightNode == null) return true;
		else return false;
	}
}
