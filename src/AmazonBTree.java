

import java.util.ArrayList;
import java.util.List;

/**
 * The AmazonBTree class implements the logic and algorithm for the "B-tree Validation"
 * exercise.
 * 
 * @author Ian Harris
 *
 */
public class AmazonBTree {
	
	public class Node{
		
		// Node members provided by exercise description
		public List<Integer> keys;
		public List<Node> children;
		
		public Node(){
			keys = new ArrayList<Integer>();
			children = new ArrayList<Node>();
		}
		
		/**
		 * Checks whether this Node and its children are valid (ie. are sorted).
		 * This is a recursive method which will also be called on this Node's child Nodes (if any exist).
		 * 
		 * This method will immediately return False if it encounters any invalid sorting in this Node
		 * or in its child Nodes.
		 * 
		 * 
		 * @return True if this node and its children are valid, False otherwise.
		 */
		public boolean isSorted(){			
			for (int i=0; i<keys.size(); i++){
				int thisKey = keys.get(i);  // Get the key at the current key index
				
				// Validate left subtree (or left key if one exists and there is no left subtree)
				Node leftSubTree = getLeftSubtree(i);
				if (leftSubTree != null){  // A left subtree exists, so validate using it
					if (!leftSubTree.isSorted()) return false;  // Check whether the left subtree is valid.
					else if (leftSubTree.getLastKey() >= thisKey) return false;  // Check that the last key of left subtree is LESS than thisKey.
					else if ((i > 0) && (leftSubTree.getFirstKey() <= keys.get(i-1))) return false;  // Finally, check that the first key of the left subtree is GREATER than the previous key in this Node (if this is not the first key in this Node).
				}
				else{  // There is no left subtree.
					if ((i > 0) && (thisKey <= keys.get(i-1))) return false;  // Validate this key against previous key if this key is not the first.
				}
				
				// Special case for last key in this Node: Validate right subtree if one exists.
				if (i == (keys.size()-1)){
					Node rightSubTree = getRightSubtree(i);
					if (rightSubTree != null){  // A right subtree exists, so validate using it
						if (!rightSubTree.isSorted()) return false;  // Check whether the right subtree is valid.
						else if (rightSubTree.getFirstKey() <= thisKey) return false;  // Check that the last key of right subtree is GREATER than thisKey.
					}
				}				
			}
			return true;  // If validation hasn't failed after checking this Node and it's child Nodes, this Node is considered valid.

		}
		
		/**
		 * Retrieves the Node which corresponds to the left subtree of the index at this Node.
		 * 
		 * Note that if there is no child node for the left subtree, this function will return null.
		 * 
		 * @param index Index of the key in this Node whose left subtree will be returned.
		 * @return A Node object which is the left subtree of the key at the given Index.
		 */
		private Node getLeftSubtree(int index){
			if (index >= children.size()) return null;  // There are at most keys.size()+1 child nodes in this tree
			return children.get(index);  // Left child for any index will be at children.get(index);
		}

		/**
		 * Retrieves the Node which corresponds to the right subtree of the index at this Node.
		 * 
		 * Note that if there is no child node for the right subtree, this function will return null.
		 * 
		 * @param index Index of the key in this Node whose right subtree will be returned.
		 * @return A Node object which is the right subtree of the key at the given Index.
		 */		
		private Node getRightSubtree(int index){
			if (index > children.size()) return null;  // If index > children.size(), child nodes may be empty
			return children.get(index+1);  // Right child for any index will be at children.get(index+1);
		}
		
		/**
		 * Gets the first key of this Node.
		 * 
		 * If this Node is sorted, the first key will also be the smallest key.
		 * 
		 * If this Node has child nodes, this method will traverse down the left subtrees of each
		 * Node before bottoming out and returning the left-most key.
		 *  
		 * @return the first key (at index 0) of this Node.
		 */
		public int getFirstKey(){			
			Node leftSubTree = getLeftSubtree(0);
			if (leftSubTree != null){  // If left subtree exists, recursively get the first key of the left subtree.
				return leftSubTree.getFirstKey();
			}
			else{  // Otherwise, return this Node's first key.
				return keys.get(0);
			}
		}
		
		/**
		 * Gets the last key of this Node.
		 * 
		 * If this Node is sorted, the last key will also be the smallest key.
		 * 
		 * If this Node has child nodes, this method will traverse down the right subtrees of each
		 * Node before bottoming out and returning the right-most key.
		 *  
		 * @return the last key (at index keys.size()-1) of this Node.
		 */
		public int getLastKey(){
			Node rightSubTree = getRightSubtree(keys.size()-1);
			if (rightSubTree != null){  // If right subtree exists, recursively get the last key of the right subtree.
				return rightSubTree.getLastKey();
			}
			else{  // Otherwise, return this Node's last key.
				return keys.get(keys.size()-1);
			}			
		}
		
	}
	
	public Node root;  // Head node of the BTree
	
	
	/**
	 * Validates whether this B-Tree is correctly sorted. 
	 * 
	 * @return True if the entire B-Tree is correctly sorted, False otherwise.
	 */
	public boolean IsSorted(){
		if (this.root == null){
			return true;  // Technically an empty B-Tree is valid/sorted.
		}else{
			return root.isSorted();  // Otherwise, if this tree is non-empty, recursively call isSorted() starting with the root node.
		}
	}
	
	
	public static void main(String[] args) {
		
		// Trivial example: empty tree.
		
		AmazonBTree tree = new AmazonBTree();
		System.out.println("Empty tree is sorted (expected True): " + tree.IsSorted());
		
		
		// Demonstrate a valid B-tree.
		// Simple example. Tree only contains keys {1,3}.
		
		tree = new AmazonBTree();
		
		Node n = tree.new Node();

		n.keys.add(1);
		n.keys.add(3);
		
		n.children.add(null);
		n.children.add(null);
		n.children.add(null);
		
		tree.root = n;
		
		System.out.println("Tree of keys {1,3} is sorted (expected True): " + tree.IsSorted());
		tree = null;
		
		// More complex example with children.
		// Taken from Figure 2 (top two levels);
		
		tree = new AmazonBTree();
		n = tree.new Node(); // left child
		n.keys.add(4);
		n.keys.add(7);
		
		n.children.add(null);
		n.children.add(null);
		n.children.add(null);
		
		Node m = tree.new Node();  // right child
		m.keys.add(17);
		m.keys.add(24);
		
		m.children.add(null);
		m.children.add(null);
		m.children.add(null);
		
		Node o = tree.new Node();  // root
		o.keys.add(13);
		o.children.add(n);
		o.children.add(m);
		
		tree.root = o;
		
		System.out.println("Tree with children is sorted (expected True): " + tree.IsSorted());
		tree = null;
		
		// Same as last example but with transposed keys which renders the tree invalid.
		
		tree = new AmazonBTree();
		n = tree.new Node();
		n.keys.add(4);
		n.keys.add(13);
		
		n.children.add(null);
		n.children.add(null);
		n.children.add(null);
		
		m = tree.new Node();
		m.keys.add(7);
		m.keys.add(24);
		
		m.children.add(null);
		m.children.add(null);
		m.children.add(null);
		
		o = tree.new Node();
		o.keys.add(13);
		o.children.add(n);
		o.children.add(m);
		
		tree.root = o;	
		
		System.out.println("Tree with transposed keys is not sorted (expected False): " + tree.IsSorted());
		tree = null;
	}

}
