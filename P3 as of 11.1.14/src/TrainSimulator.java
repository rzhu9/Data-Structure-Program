///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p3
// Files:            (list of source files)
// Semester:         CS367 Fall 2014
//
// Author:           Aren Lorenson 
// Email:            alorenson@wisc.edu
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
// Email:            rzhu9@wisc.edu
// CS Login:         ruihao
// Lecturer's Name:  Skrentny
// Lab Section:      
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  encloses the main method
 *
 * <p>Bugs: (none)
 *
 * @author (aren, ruihao)
 */

public class TrainSimulator 
{

	/**
	 * Simulates trains operating on a one way line with stops at stations that
	 * function as stacks with gaps between one another where trains are queued.
	 * The stations operate on an exit first policy and all are 10 time units 
	 * apart. Events are documented and displayed to the user via CLAs.
	 *
	 * <p>Bugs: (none)
	 *
	 * @author (aren, ruihao)
	 */

	public static void main(String[] args) throws FileNotFoundException, FullPlatformException, EmptyQueueException, FullQueueException, EmptyPlatformException
	{
		int option =0;
		int time = 0;	
		List <Station> stationList = new ArrayList <Station>();
		List <Train> trainList = new ArrayList <Train>();
		List <String> output = new ArrayList<String>();
		// is args[] length 5?
		if (args.length !=5) 
		{
			System.out.println("args.length !=5");
			System.exit(0);
		}
		// are args[0,1] java TrainSimulator?
		if (!args[0].equals("java") || !args[1].equals("TrainSimulator")) 
		{
			System.out.println("args[0,1] not java TrainSimulator");
			System.exit(0);
		}
		// is args[2] an int?
		try 
		{
			option = Integer.parseInt(args[2]);
		}
		catch (NumberFormatException e)
		{
			System.out.println("args[2] not an int");
			System.exit(0);
		}
		// is args[2] == 0,1,2?
		if (Integer.parseInt(args[2]) < 0 || Integer.parseInt(args[2]) > 2) 
		{
			System.out.println("args[2] must == 0,1,2");
			System.exit(0);
		}
		else 
		{	// read the station file
			int numLines = 0;
			File stationsFile = new File (args[3]);
			Scanner two = new Scanner(stationsFile);
			if (stationsFile.exists() && stationsFile.canRead())
			{
				try	//creates stations w/ platform of some capacity
				{
					numLines = Integer.parseInt(two.nextLine());
				}
				catch (Exception e)
				{
					System.out.println("Number not in first line of stations.");
					System.exit(0);
				}
				if (numLines == 1)
				{
					System.out.println("Must have more than 1 station.");
					System.exit(0);
				}
				for (int i=0;i<numLines; i++)
				{
					String[] line = two.nextLine().split(",");
					int [] stationInfo = new int[line.length];
					for (int j =0;j <line.length;j ++)
					{
						stationInfo[j] = Integer.parseInt(line[j]);
					}
					Station station = new Station(stationInfo[0],stationInfo[1]);	
					stationList.add(station);	
					//stationList[0] is first station
				}
				two.close();	
			}
			File trainsFile = new File (args[4]);
			Scanner one = new Scanner(trainsFile);
			if (trainsFile.exists() && trainsFile.canRead())
			{
				try	//creates trains w/ ETDs & null ATAs,ATDs
				{
					numLines = Integer.parseInt(one.nextLine());
				}
				catch (Exception e)
				{
					System.out.println("Number not in first line of trains.");
					System.exit(0);
				}
				for (int i=0;i<numLines; i++)
				{
					String[] line = one.nextLine().split(",");
					int [] trainInfo = new int[line.length];	
					for (int j =0;j <line.length;j ++)
					{
						trainInfo[j] = Integer.parseInt(line[j]);
					}
					Train train = new Train(trainInfo[0]);
					for (int t = 1; t < trainInfo.length; t++)
					{
						train.getETD().add(trainInfo[t]);	
					}
					trainList.add(train);	
					//	per p3 post 551 train input is given in order
					//	so there is no reordering of trainList
				}
				one.close();
			}
		}
		//begin runs: fill first station so lowest ID train is top of stack
		SimpleQueue <Train> q = new SimpleQueue <Train> (trainList.size());
		for (int i=trainList.size()-1; i>=0; i--)
		{	 
			try 
			{
				stationList.get(0).getPlatform().put(trainList.get(i));
			}
			catch (FullPlatformException e)
			{	//can't happen according to piazza but whatever
				System.out.println("More trains than initial platform capacity.");
				System.exit(0);
			}	
		}
		//while last platform not full
		while (!stationList.get(stationList.size()-1).getPlatform().isFull()) 
		{	
			String time1 = Integer.toString(time);
			for (int i=0;i<trainList.size();i++)
			{
				for(int j = 0; j<stationList.size();j++)	
				{
					//all eligible trains leave stations & enqueued
					if (aTrainCanLeave(stationList.get(j), time)) 	
					{			
						Train t = stationList.get(j).getPlatform().get();
						int ID = t.getId();	//used to find this train in trainList
						q.enqueue(t);	
						String trainID1 = Integer.toString(t.getId());
						String message = time1 + ":	Train " + trainID1 + " has exited from station " + stationList.get(j).getId() + ".";
						output.add(message);
						//find index in trainList w/ train = t & update its ATD
						int location = findTrainInList(trainList,ID);
						trainList.get(location).getATD().add(time);	
					}
				}
			}
			for (int i=0;i<trainList.size();i++)	
			{
				//all eligible trains dequeued & enter stations
				if (aTrainCanEnter(q,time))	
				{
					try
					{
						if(!stationList.get(q.peek().getATD().size()).getPlatform().isFull())
						{
							Train t = q.peek();
							String stationID = Integer.toString(stationList.get(q.peek().getATD().size()).getId());
							String trainID = Integer.toString(q.peek().getId());	
							String message = time1 + ":	Train " + trainID + " has been parked at station " + stationID +".";
							output.add(message);
							t.getATA().add(time);	
							stationList.get(q.peek().getATD().size()).getPlatform().put(t);
							q.dequeue();
						}
					}
					catch (FullPlatformException e1)
					{}	//don't add to a station if full
				}
			}

			time ++;
		}
		if (option == 0) 
		{	//timeline of station events
			for (int i=0;i<output.size();i++) 
			{
				System.out.println(output.get(i));
			}
		}
		if (option == 1) 
		{	//list of the ATDs ordered by the trains' ID
			for (int i=0;i<trainList.size();i++) 
			{
				String message = "";
				message = "[";
				for(int j=0; j < trainList.get(i).getATD().size() -1; j++){
					message = message + Integer.toString(trainList.get(i).getATD().get(j)) + ", ";	//print ATDs a train
				}
				message = message + Integer.toString(trainList.get(i).getATD().get(trainList.get(i).getATD().size()-1)) + "]";
				System.out.println(message);
			}
		}
		if (option == 2) 
		{	// list of ATAs ordered by the trains' ID
			for (int i=0;i<trainList.size();i++) 
			{
				String message = "";
				message = "[";
				for(int j=0; j < trainList.get(i).getATA().size() -1; j++)
				{
					message = message + Integer.toString(trainList.get(i).getATA().get(j)) + ", ";
				}
				message = message + Integer.toString(trainList.get(i).getATA().get(trainList.get(i).getATA().size()-1)) + "]";
				System.out.println(message);
			}
		}
	}

	/**
	 * Sees if the time is right for a train to be popped from a station stack.
	 *
	 * @param s the station of interest 
	 * @param time the current time
	 * @return true if the time permits a train to leave station s
	 */

	private static boolean aTrainCanLeave (Station s, int time) 
	{
		try
		{
			int lastIndex = s.getPlatform().check().getATD().size();
			if (time >= s.getPlatform().check().getETD().get(lastIndex))
			{
				return true;	
			}
			return false;
		}
		catch (EmptyPlatformException e)
		{
			return false;
		}
		catch(Exception e2)	//handles last station since trains can't leave it
		{	
			return false;
		}
	}

	/**
	 * Sees if the time is right for a train to get pushed into a station stack.
	 *
	 * @param q the queue of trains not in stations
	 * @param time the current time
	 * @return true if time permits the top of q, a train, to enter a station 
	 */

	private static boolean aTrainCanEnter (SimpleQueue <Train> q, int time) 
	{
		if(q == null)	//all trains in a station
		{
			return false;
		}
		try //time must be >= last ATD + 10 to attempt entry
		{
			int size = q.peek().getATD().size();	
			if( time >= 10 + q.peek().getATD().get(size-1))	
			{
				return true;
			}
			return false;
		}
		catch (EmptyQueueException e1)
		{
			return false;
		}
	}

	/**
	 * Looks for the index in a List<Train> w/ a train w/ ID == trainID
	 *
	 * @param t the list of every train in the simulation
	 * @param trainID the ID of one train in t we are searching for 
	 * @return the index in t containing the train w/ ID == trainID
	 */

	private static int findTrainInList(List<Train> t, int trainID)
	{	//the train will always be found so this int will always be overwritten
		int a = 69;	
		for(int i = 0; i < t.size(); i++)
		{
			if(t.get(i).getId()==trainID)
				a=i;
		}
		return a;
	}
}

