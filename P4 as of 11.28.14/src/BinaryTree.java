///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GuessingGame.java
// File:             BinaryTree.java
// Semester:         CS367 Fall 2014
//
// Author:           Aren Lorenson
// CS Login:         aren
// Lecturer's Name:  Skrentny
// Lab Section:      n/a
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     Ruihao Zhu
// CS Login:         ruihao
// Lecturer's Name:  Skrentny
// Lab Section:      n/a
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A Binary tree with a root and a pointer node currentNode. 
 *
 * @author aren, ruihao
 */

public class BinaryTree 
{
	private BinaryTreenode <String> root;
	private BinaryTreenode <String> currentNode;

	//constructors
	public BinaryTree()	
	{ 
		root = new BinaryTreenode <String> ();
		start();
	}

	public BinaryTree(String data)
	{	
		root = new BinaryTreenode <String> (data);
		start();
	}

	/**
	 * Sets currentNode to the tree's root. 
	 *
	 * @return void
	 */

	public void start()
	{
		currentNode = root;
	}

	/**
	 * Returns the data stored in the current node in navigation.
	 *
	 * @throws IllegalBinaryTreeOpException if no current node in navigation 
	 * @return void
	 */

	public String getCurrent() throws IllegalBinaryTreeOpException
	{
		if (currentNode.getData() == null)
		{
			throw new IllegalBinaryTreeOpException(
					"Not pointing at a node in navigation.");
		}
		if (currentNode.getData() != null)
		{
			return currentNode.getData();
		}
		return "";	//should never reach this case & thus we're OK


	}


	/**
	 * Sets currentNode to its left child 
	 *
	 * @throws IllegalBinaryTreeOpException if left child is null 
	 * @return void
	 */

	public void goLeft() throws IllegalBinaryTreeOpException
	{
		if(currentNode.getLeft()!= null)	
		{

			currentNode = currentNode.getLeft();
		}
		else
		{
			throw new IllegalBinaryTreeOpException(""
					+ "Cannot go left; there is no left child of this node.");
		}	
	}

	/**
	 * Sets currentNode to its right child 
	 *
	 * @throws (IllegalBinaryTreeOpException) if right child is null 
	 * @return void
	 */

	public void goRight() throws IllegalBinaryTreeOpException
	{
		if(currentNode.getRight()!= null)
		{

			currentNode = currentNode.getRight();
		}
		else
		{
			throw new IllegalBinaryTreeOpException("Cannot go right; there is "
					+ "no right child of this node.");

		}
	}

	/**
	 * Returns true iff currentNode is a leaf.
	 *
	 * @return true iff currentNode is a leaf.
	 */

	public boolean isLeaf()
	{	
		if (currentNode.getLeft() == null && currentNode.getRight() == null) 
		{	
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Changes the data held by the current node in navigation to the 
	 * specified data.
	 * @param (E data) the data to store at currentNode
	 * @return void
	 */

	public void changeCurrent(String data)
	{
		currentNode.setData(data);
	}

	/**
	Adds a node with the specified child as the right child of the current node 
	 * in navigation.
	 * @throws (IllegalBinaryTreeOpException) if right child is not null 
	 * @param (E child) The child node to append
	 * @return void
	 */

	public void addRightChild(String data) throws IllegalBinaryTreeOpException
	{
		if(currentNode.getRight()== null)
		{
			currentNode.setRight(data);
		}
		else
		{
			throw new IllegalBinaryTreeOpException("Failed to add right child. "
					+ "One already exists for this node.");
		}
	}


	/**
 * Adds a node with the specified child as the left child of the current node 
	 * in navigation.
	 *
	 * @param (E child) (The child node to append
	 * @throws (IllegalBinaryTreeOpException) if left child is not null 
	 * @return void
	 */

	public void addLeftChild(String data) throws IllegalBinaryTreeOpException
	{
		if(currentNode.getLeft()== null)
		{
			currentNode.setLeft(data);
		}
		else
		{
			throw new IllegalBinaryTreeOpException("Failed to add left child. "
					+ "One already exists for this node.");
		}
	}
	
	/**
	 * Pre-order prints the tree, starting at the root. Each additional level 
	 * of the tree is incremented by three spaces.
	 *
	 * @return void
	 */

	public void print()	
	{
		int level = 1;
		if (root != null)
		{
			print(root, level - 1);
		}
		else
		{
		}
	}
	/**
	 * print() companion method that pre-order prints the tree.
	 *
	 * @return void
	 */
	private void print (BinaryTreenode <String> node, int numSpaces)
	{
		//VLR

		String spaces = "";
		for (int i = 0; i < numSpaces; i++)
		{
			spaces = spaces + "   ";
		}

		System.out.println(spaces + node.getData());

		if(node.getLeft() != null)
		{
			print(node.getLeft(), numSpaces+1);	
		}

		if(node.getRight() != null)
		{
			print(node.getRight(), numSpaces+1);	
		}
	}
}
