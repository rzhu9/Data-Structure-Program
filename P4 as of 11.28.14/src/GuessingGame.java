///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            P4
// Files:            GuessingGame.java
// Semester:         CS367 Fall 2014
//
// Author:           Aren Lorenson
// Email:            alorenson@wisc.edu	
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
// Email:            rzhu9@wisc.edu
// CS Login:         ruihao
// Lecturer's Name:  Skrentny
// Lab Section:      n/a
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util. Scanner;

/**
 * Allows the user to play a game akin to 20Q where input is used to traverse a 
 * binary tree of questions and answers, enabling the program to guess what the
 * user is thinking. Also has optins to display the status of the tree in play,
 * quit, and start a new tree. Accepts both txt input via CLAs and manual input. 
 * If the guess is wrong, a subtree is added  that includes what the user was 
 * thinking so that the program "learns."
 *
 * @author aren, ruihao
 */

public class GuessingGame
{
	
	/**
	 * Pre-order prints the tree, starting at the root. Each additional level 
	 * of the tree is incremented by three spaces.
	 *
	 * @return void
	 */
	
	public static void main (String args[]) throws IllegalBinaryTreeOpException, 
	FileNotFoundException
	{
		// set up variables
		BinaryTree tree = new BinaryTree();	//the tree in play	
		char menuOption = ' ';	//menu prompt reply for use in switch  
		String menuInput = "";	//stores menu prompt reply for validity check
		String input = "";	//stores in from scanner
		String guess = "";	// the answer 20Q will provide
		Scanner scnr = new Scanner (System.in); // scans keyboard input
		Scanner in = new Scanner (System.in); // scans file input
		boolean done = false;

		// determine if CLA in use
		if (args.length == 2) 
		{
			while (!done)	
			{
				System.out.println("Please enter a command (o, p, q, r): ");
				try	//test for valid menu command
				{
					menuInput = scnr.next();
					menuOption = menuInput.charAt(0);
					if (menuInput.length() != 1)
					{
						menuOption = 'z';
					}
					if(menuOption == 'r' || menuOption == 'p' || 
							menuOption == 'o' || menuOption == 'q')
					{
						
					}
					else
					{
						menuOption = 'z';
					}
					
				}
				catch (Exception e1)
				{
					menuOption = 'z';
				}
				
				switch (menuOption){
				
				case 'z':	//bad menu command
				{
					System.out.println("Invalid command.");
					break;
				}
				
				case 'o':
					tree.start();
					try
					{
						if (tree.getCurrent() != null)
						{
							tree.print();
						}
					}
					catch (IllegalBinaryTreeOpException e1)
					{
						System.out.println("Empty Tree");
					}
					break;

				case 'p':
					tree.start();	
					//test for empty tree
					try
					{
						tree.getCurrent(); 
					}
					catch (IllegalBinaryTreeOpException e1)	// treat as case r
					{
						System.out.println("Please enter a question.");
						input = in.nextLine();
						//store input in root
						tree = new BinaryTree(input);
System.out.println("Please enter something that is true for that question.");	
						input = in.nextLine();
						tree.addLeftChild(input);	
System.out.println("Please enter something that is false for that question.");
						input = in.nextLine();
						tree.addRightChild(input);
						break;
					}

					//proceed to navigate tree
					// Prompt the user with the question at the current node.
					
					System.out.println(tree.getCurrent());
					
//Read the input from the user; it will be either "y" or "n" not case-sensitive.
//Navigate to either the left or right child depending on the user's response.
					input = in.next();
					
					try
					{
						if (input.equals("y") ||input.equals("Y")) 
						{
							tree.goLeft();
						}
						if (input.equals("n") ||input.equals("N"))
						{
							tree.goRight();
						}
					}
					catch (IllegalBinaryTreeOpException e1)
					{
	System.out.println("No children of root question; returning to main menu");
						break;
					}
					
					// print sub questions until reach a leaf
		boolean sentinel = false;	//probably redundant; true iff leaf reached
					while (tree.isLeaf() == false && sentinel == false)
					{
						try
						{
							System.out.println(tree.getCurrent());
							input = in.next();							
							if (input.equals("y") ||input.equals("Y"))
							{
								tree.goLeft();
							}
							if (input.equals("n") ||input.equals("N"))
							{
								tree.goRight();
							}
						}
//getCurrent() can't throw; getLeft(), getRight() throw when null node data 
//encountered
						catch(IllegalBinaryTreeOpException e1)
						{
							sentinel = true;
						}
					}

					// reach a leaf guess, 
		System.out.println("I guess: " + tree.getCurrent() + ". Was I right?");
					guess = tree.getCurrent();
					// get feedback 
					input = in.next();
					// learn from mistake or gloat
					if (input.equals("y") ||input.equals("Y")) {
						System.out.println("I win!");
					}
					if (input.equals("n") ||input.equals("N")){
System.out.println("Darn. Ok, tell me a question that is true for your guess, "
		+ "but false for my guess.");
						input = in.next();
						tree.changeCurrent(input);
				System.out.println("Thanks! And what were you thinking of?");
						input = in.next();
						tree.addLeftChild(input);
						tree.addRightChild(guess);
					}
					break;
					
				case 'q':
					System.exit(0);
					break;
					
				case 'r':

					System.out.println("Please enter a question.");
					input = in.nextLine();
					//store input in root
					tree = new BinaryTree(input);
System.out.println("Please enter something that is true for that question.");	
					input = in.nextLine();
					tree.addLeftChild(input);	
System.out.println("Please enter something that is false for that question.");
					input = in.nextLine();
					tree.addRightChild(input);
					break;
				}
			}
			in.close();
		}
				if (args.length == 3) //CLA in use; read in file
				{
					File file = new File(args[2]);
					if (file.exists() && file.canRead()){
						Scanner stdin = new Scanner(file);
					while (!done)	
					{
					System.out.println("Please enter a command (o, p, q, r): ");
						try	//test for valid menu command
						{
							menuInput = stdin.next();
							menuOption = menuInput.charAt(0);
							if (menuInput.length() != 1)
							{
								menuOption = 'z';
							}
			if(menuOption == 'r' || menuOption == 'p' || menuOption == 'o' ||
			menuOption == 'q')
							{
								
							}
							else
							{
								menuOption = 'z';
							}
							
						}
						catch (Exception e1)
						{
							menuOption = 'z';
						}
						
						switch (menuOption){
						
						case 'z':	//bad menu command
						{
							System.out.println("Invalid command.");
							break;
						}
						
						case 'o':
							tree.start();
							try
							{
								if (tree.getCurrent() != null)
								{
									tree.print();
								}
							}
							catch (IllegalBinaryTreeOpException e1)
							{
								System.out.println("Empty Tree");
							}

							break;

						case 'p':
							tree.start();	
							//test for empty tree
							try
							{
								tree.getCurrent(); 
							}
					catch (IllegalBinaryTreeOpException e1)	// treat as case r
							{
								System.out.println("Please enter a question.");
								input = stdin.nextLine();
								//store input in root
								tree = new BinaryTree(input);
System.out.println("Please enter something that is true for that question.");	
								input = stdin.nextLine();
								tree.addLeftChild(input);	
System.out.println("Please enter something that is false for that question.");
								input = stdin.nextLine();
								tree.addRightChild(input);
								break;
							}

							//proceed to navigate tree
					// Prompt the user with the question at the current node.
							
							System.out.println(tree.getCurrent());
							
//Read the input from the user; it will be either "y" or "n" not case-sensitive.
//Navigate to either the left or right child depending on the user's response.
							stdin.nextLine();
							input = stdin.nextLine();
							try
							{
								if (input.equals("y") ||input.equals("Y")) 
								{
									tree.goLeft();
								}
								if (input.equals("n") ||input.equals("N"))
								{
									tree.goRight();
								}
							}
							catch (IllegalBinaryTreeOpException e1)
							{
System.out.println("No answer to root question; returning to main menu.");
								break;
							}
							// print sub questions until reach a leaf
	boolean sentinel = false;	//probably redundant; true iff leaf reached
							while (tree.isLeaf() == false && sentinel == false)
							{
								try
								{
									System.out.println(tree.getCurrent());
									input = stdin.next();							
									if (input.equals("y") ||input.equals("Y"))
									{
										tree.goLeft();
									}
									if (input.equals("n") ||input.equals("N"))
									{
										tree.goRight();
									}
								}
//getCurrent() can't throw; getLeft(), getRight() throw when null node data 
//encountered
								catch(IllegalBinaryTreeOpException e1)
								{
									sentinel = true;
								}
							}

							// reach a leaf guess, 
		System.out.println("I guess: " + tree.getCurrent() + ". Was I right?");	
							guess = tree.getCurrent();
							// get feedback 
							input = stdin.next();
							// learn from mistake or gloat
							if (input.equals("y") ||input.equals("Y")) {
								System.out.println("I win!");
							}
							if (input.equals("n") ||input.equals("N")){
System.out.println("Darn. Ok, tell me a question that is true for your guess, "
		+ "but false for my guess.");
								stdin.nextLine();
								input = stdin.nextLine();	
								tree.changeCurrent(input);
System.out.println("Thanks! And what were you thinking of?");
								input = stdin.nextLine();
								tree.addLeftChild(input);
								tree.addRightChild(guess);
							}
							break;
							
						case 'q':
							done = true;
							System.exit(0);
							break;
							
						case 'r':

							System.out.println("Please enter a question.");
							stdin.nextLine();
							input = stdin.nextLine();	
							//store input in root
							tree = new BinaryTree(input);
System.out.println("Please enter something that is true for that question.");	
							input = stdin.nextLine();
							tree.addLeftChild(input);	//left path is true case
System.out.println("Please enter something that is false for that question.");
							input = stdin.nextLine();
							tree.addRightChild(input);
							break;
						}
					}
					stdin.close();
				}
			}
		else {
			// invalid command line argument
			System.exit(0);
		}
		scnr.close();
	}
}
