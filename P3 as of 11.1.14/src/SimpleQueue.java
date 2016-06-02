///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             SimpleQueue.java
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
 * A FIFO queue.
 *
 * <p>Bugs: (none)
 *
 * @author (aren, ruihao)
 */

public class SimpleQueue <E> implements QueueADT<E>{
	private E items [];
	private int numItems;
	private int capacity;

	public SimpleQueue(int capacity){
		this.capacity = capacity;
		numItems = 0;
		items = (E[])(new Object [capacity]);
	}

	/**
	 * Adds an item to the rear of the queue.
	 *
	 * @param item The item to be added to the queue
	 */

	public void enqueue(E item) throws FullQueueException{
		if (isFull() ==  true){
			throw new FullQueueException();

		}
		if(numItems==0)
		{
			items[0]=item;
			items[1] = null;
			numItems++;
		}
		else{	
			items[numItems] = item;
			numItems++;
		}
	}

	/**
	 * Removes and returns the front item of the queue.
	 *
	 * @return The front item of the queue
	 * @throws EmptyQueueException If the queue is empty
	 */
	public E dequeue() throws EmptyQueueException{
		if (isEmpty() == true){			
			throw new EmptyQueueException();
		}
		if(items.length == 1)
		{
			E object = items[0];
			items[0] = null;
			return object;
		}
		else{
			E object = items[0];	
			for (int i=0;i<items.length-1;i++){
				items[i] = items[i+1];
			}
			numItems --;
			items[numItems] = null;
			return object;
		}
	}

	/**
	 * Returns the front item of the queue without removing it.
	 *
	 * @return The front item of the queue
	 * @throws EmptyQueueException If the queue is empty
	 */
	public E peek() throws EmptyQueueException{
		if (isEmpty() == true){
			throw new EmptyQueueException();
		}
		return items[0];	
	}


	/**
	 * Checks if the queue is empty.
	 * 
	 * @return True if queue is empty; else false
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
	 * Checks if the queue is full.
	 * 
	 * @return True if queue is full; else false
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
