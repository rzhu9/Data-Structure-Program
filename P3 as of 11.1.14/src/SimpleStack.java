///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             SimpleStack.java
// Semester:         CS367 Fall 2014
//
// Author:           Aren Lorenson 
// CS Login:         aren
// Lecturer's Name:  Skrentny
// Lab Section:  
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     Ruihao Zhu
// CS Login:         ruihao
// Lecturer's Name:  Skrentny
// Lab Section: 
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A LIFO stack.
 *
 * <p>Bugs: (none)
 *
 * @author (aren, ruihao)
 */

public class SimpleStack <E> implements StackADT<E> {
	private E items [];
	private int numItems;
	private int capacity;

	public SimpleStack(int capacity){
		this.capacity = capacity;
		numItems = 0;
		items = (E[])(new Object [capacity]);
	}

	public void push (E item) throws FullStackException{
		if (isFull() ==  true){
			throw new FullStackException();
		}
		else {
			for (int i= numItems-1;i>=0;i--){
				items[i+1] = items[i];
			}
			items[0] = item;
			numItems ++;
		}
	}
	/**
	 * Removes and returns the top item of the stack.
	 *
	 * @return The top item of the stack
	 * @throws EmptyStackException If the stack is empty
	 */
	public E pop() throws EmptyStackException{
		if (isEmpty() == true){
			throw new EmptyStackException();
		}
		else{
			E object = items[0];
			for (int i=numItems-1;i>0;i--){
				items[i-1] = items[i];
			}
			numItems --;
			return object;
		}
	}

	/**
	 * Returns the top item of the stack without removing it.
	 *
	 * @return The top item of the stack
	 * @throws EmptyStackException If the stack is empty
	 */
	public E peek() throws EmptyStackException{
		if (isEmpty() == true){
			throw new EmptyStackException();
		}
		return items[0];

	}

	/**
	 * Checks if the stack is empty.
	 * 
	 * @return True if stack is empty; else false
	 */
	public boolean isEmpty(){
		if (numItems == 0){
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Checks if the stack is full.
	 * 
	 * @return True if stack is full; else false
	 */
	public boolean isFull(){
		if (numItems == capacity) {
			return true;
		}
		else {
			return false;
		}
	}

}
