///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  TrainSimulator.java
// File:             Platform.java
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
 * Models a train platform as a stack.
 *
 * <p>Bugs: (none)
 *
 * @author (aren, ruihao)
 */

public class Platform implements PlatformADT  {
	private int numTrains;
	private int capacity;
	private Train trains [];

	public Platform (int capacity){
		this.capacity = capacity;
		numTrains = 0;
		trains = new Train [capacity];
	}
	/**
	 * Adds the given train to the platform.
	 *
	 * @param train The train to be added to the platform
	 * @throws FullPlatformException If the platform is full
	 */
	public void put(Train item) throws FullPlatformException{
		if (isFull() ==  true){
			throw new FullPlatformException();
		}
		else {
			for (int i= numTrains-1;i>=0;i--){
				trains[i+1] = trains[i];
			}
			trains[0] = item;
		}
		numTrains++;
	}

	/**
	 * Removes a train from the platform.
	 *
	 * @return The train removed from the platform
	 * @throws EmptyPlatformException If the platform is empty
	 */
	public Train get() throws EmptyPlatformException{
		if (isEmpty() == true){
			throw new EmptyPlatformException();
		}
		else{	
			Train object = trains[0];
			for (int i=0; i < numTrains-1; i++){
				trains[i] = trains[i+1];
			}
			trains[numTrains-1] = null;
			numTrains--;
			return object;
		}
	}

	/**
	 * Returns the train which may exit first without removing it from the
	 * platform.
	 *
	 * @return The train removed from the platform
	 * @throws EmptyPlatformException If the platform is empty
	 */
	public Train check() throws EmptyPlatformException{
		if (isEmpty() == true){
			throw new EmptyPlatformException();
		}
		return trains[0];
	}

	/**
	 * Checks if the platform is empty.
	 * 
	 * @return True if the platform is empty; else false
	 */
	public boolean isEmpty(){
		if (numTrains == 0){
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Checks if the platform is full.
	 * 
	 * @return True if the platform is full; else false
	 */
	public boolean isFull(){
		if (numTrains == capacity) {
			return true;
		}
		else {
			return false;
		}
	}
}
