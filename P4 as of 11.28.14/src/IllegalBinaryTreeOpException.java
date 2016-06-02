///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GuessingGame.java
// File:             IllegalBinaryTreeOpException.java
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
 * An exception thrown when performing operations that violate binary tree 
 * properties & implementation as discussed in class.
 *
 *
 * @author aren, ruihao
 */

public class IllegalBinaryTreeOpException extends Exception
{
	private String message;
	
	public IllegalBinaryTreeOpException(String message)
	{
		this.message = message;
	}
}