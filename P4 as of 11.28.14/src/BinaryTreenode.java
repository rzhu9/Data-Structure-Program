///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GuessingGame.java
// File:             BinaryTreenode.java
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
 * One node of a binary tree.
 *
 *
 * @author aren, ruihao
 */

public class BinaryTreenode<E> {

	private E data;
	private BinaryTreenode<E> left, right;
	
	public BinaryTreenode() {
		this.data = null;
		this.left = null;
		this.right = null;
	}
	
	public BinaryTreenode(E data) {
		this.data = data;
		left = null;
		right = null;
	}
	
	public E getData() {
		return data;
	}
	
	public BinaryTreenode<E> getLeft() {
		return left;
	}
	
	public BinaryTreenode<E> getRight() {
		return right;
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public void setLeft(E left) {
		this.left = new BinaryTreenode<E>(left);
	}
	
	public void setRight(E right) {
		this.right = new BinaryTreenode<E>(right);
	}
}