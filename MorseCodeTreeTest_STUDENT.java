import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class MorseCodeTreeTest_STUDENT {

	MorseCodeTree tree;
	
	@Before
	public void setUp() throws Exception {
		//Rebuild Morse Code Tree for each test
		tree = new MorseCodeTree();
	}
	
	@After
	public void tearDown() throws Exception {
		tree = null;
	}
	
	@Test
	public void testAddNode() {
		//Add nonsense characters to the tree
		tree.addNode(tree.getRoot(), "----", "&");
		tree.addNode(tree.getRoot(), ".....", "*");
		tree.addNode(tree.getRoot(), ".--..", "hello");
		
		//String for expected tree layout
		String treeLayout = "* h s v i f u e l r a hello p w j  b d x n c k y t z g q m o &";
		assertEquals(printTree(), treeLayout);
	}
	
	@Test
	public void testFetch() {
		
		//Verify nodes are in the correct place in the tree
		assertEquals("e", tree.fetch("."));
		assertEquals("d", tree.fetch("-.."));
		assertEquals("r", tree.fetch(".-."));
	}
	
	@Test
	public void testFetchNode() {
		
		//Verify nodes are in the correct place in the tree
		assertEquals("t", tree.fetchNode(tree.getRoot(), "-"));
		assertEquals("w", tree.fetchNode(tree.getRoot(), ".--"));
		assertEquals("y", tree.fetchNode(tree.getRoot(), "-.--"));
	}
	
	@Test
	public void testUnsupportedMethods() {
		
		//test unsupported methods
		try {
			tree.delete("--.");
			assertTrue("This should not have passed", false);
		}
		catch (UnsupportedOperationException e) {
			assertTrue("Test passed", true);
		}
		try {
			tree.update();
			assertTrue("This should not have passed", false);
		}
		catch (UnsupportedOperationException e) {
			assertTrue("Test passed", true);
		}
	}
	
	@Test
	public void testInsert() {
		//insert letters into tree. Note that the second insert will do nothing, as the node already exists
		tree.insert(".....", "$");
		tree.insert("-", "!!!");
		tree.insert(".--..", "%");
		
		String printout = "$ h s v i f u e l r a % p w j  b d x n c k y t z g q m o";
		
		assertEquals(printTree(), printout);
	}
	
	@Test
	public void setRoot() {
		//verify current root
		assertEquals(tree.getRoot().getData(), "");
		assertEquals(tree.getRoot().getLeft().getData(), "e");
		assertEquals(tree.getRoot().getRight().getData(), "t");
		
		//set the root to the current root's left subtree
		tree.setRoot(tree.getRoot().getLeft());
		//verify
		assertEquals(tree.getRoot().getData(), "e");
		assertEquals(tree.getRoot().getLeft().getData(), "i");
		assertEquals(tree.getRoot().getRight().getData(), "a");
		
		//Set the new root to a generic TreeNode
		tree.setRoot(new TreeNode<String>("hello"));
		assertEquals(tree.getRoot().getData(), "hello");
		assertEquals(tree.getRoot().getLeft(), null);
		assertEquals(tree.getRoot().getRight(), null);
	}
	
	@Test
	public void testToArrayList() {
		ArrayList<String> list = tree.toArrayList();
		
		assertEquals(list.get(0), "h");
		assertEquals(list.get(10), "p");
		assertEquals(list.get(15), "d");
		assertEquals(list.get(18), "c");
		assertEquals(list.get(25), "m");
	}
	
	public String printTree() {
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
}
