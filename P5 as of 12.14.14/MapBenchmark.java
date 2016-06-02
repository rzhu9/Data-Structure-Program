//////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Title: MapBenchmark.java // Files: (list of source files)
// Semester: CS367 Fall 2014
//
// Author: Ruihao Zhu
// Email: rzhu9@wisc.edu
// CS Login: ruihao
// Lecturer's Name: Jim Skrentny
// Lab Section: (your lab section number)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
// CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
// If allowed, learn what PAIR-PROGRAMMING IS, // choose a partner wisely, and complete this section.
//
// Pair Partner: Aren Lorenson
// Email: alorenson@wisc.edu
// CS Login: aren
// Lecturer's Name: Jim Skrentny
// Lab Section: (your partner's lab section number)
//
// STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits: (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * enables a key value K to be mapped to its associated value V
 * and compares the speed of map operations of two 
 * implementations of maps in APIs: HashMaps and TreeMaps
 * <p>No Bugs
 *
 * @author Ruihao Zhu & Aren Lorenson
 */
public class MapBenchmark
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		SimpleTreeMap treeMap = new SimpleTreeMap();
		SimpleHashMap hashMap = new SimpleHashMap();
		int numIter = Integer.parseInt(args[3]); //number of iterations to run
		//arrays of times kept track of
		long [] HashMapPutTime = new long[numIter];
		long [] TreeMapPutTime = new long[numIter];
		long [] HashMapFloorKeyTime = new long[numIter];
		long [] TreeMapFloorKeyTime = new long[numIter];
		long [] HashMapGetTime = new long[numIter];
		long [] TreeMapGetTime = new long[numIter];
		long [] HashMapRemoveTime = new long[numIter];
		long [] TreeMapRemoveTime = new long[numIter];
		for(int ndx = 0;ndx < numIter;ndx++)
		{
			File file = new File(args[2]);
			if(file.exists() && file.canRead())
			{
				Scanner stdin = new Scanner(file);
				String [] keyValuePair = stdin.nextLine().split(" ");
				int key;	//stores subsequent keys
				int maxKey = Integer.parseInt(keyValuePair[0]);	//store first key
				String value = keyValuePair[1];	//store first value
				long treePutTime = 0;	//time taken for tree put operations
				long hashMapPutTime = 0;	//time taken for hash map put operations

				long startTime = System.currentTimeMillis();	//first put operations & timing
				treeMap.put(maxKey, value);
				long elapsedTime = System.currentTimeMillis() - startTime;
				treePutTime += elapsedTime;

				startTime = System.currentTimeMillis();	
				hashMap.put(maxKey, value);
				elapsedTime = System.currentTimeMillis() - startTime;
				hashMapPutTime += elapsedTime;

				while (stdin.hasNextLine())		//populate maps & obtain max key
				{
					keyValuePair = stdin.nextLine().split(" ");
					key = Integer.parseInt(keyValuePair[0]);
					value = keyValuePair[1];
					if(key > maxKey)	
					{
						maxKey = key;
					}

					startTime = System.currentTimeMillis();	//subsequent put operations & timing
					treeMap.put(key, value);
					elapsedTime = System.currentTimeMillis() - startTime;
					treePutTime += elapsedTime;

					startTime = System.currentTimeMillis();	
					hashMap.put(key, value);
					elapsedTime = System.currentTimeMillis() - startTime;
					hashMapPutTime += elapsedTime;
				}

				//get every key-value pair from maps + floorKey everything + remove
				long hashMapFloorKeyTime = 0;
				long treeMapFloorKeyTime = 0;
				long hashMapRemoveTime = 0;
				long treeMapRemoveTime = 0;
				long hashMapGetTime = 0;
				long treeMapGetTime = 0;

				boolean done = false;
				while (!done)
				{
					try	
					{
						startTime = System.currentTimeMillis();
						hashMap.floorKey(maxKey);	
						elapsedTime = System.currentTimeMillis() - startTime;
						hashMapFloorKeyTime += elapsedTime;
						startTime = System.currentTimeMillis();
						hashMap.get(hashMap.floorKey(maxKey));//TODO: AFTER 1 ITER
						elapsedTime = System.currentTimeMillis() - startTime;
						hashMapGetTime+= elapsedTime;
						startTime = System.currentTimeMillis();
						hashMap.remove(hashMap.floorKey(maxKey));	//always gets the same key
						elapsedTime = System.currentTimeMillis() - startTime;
						hashMapRemoveTime += elapsedTime;
					}
					catch(NullPointerException e1)
					{
						done = true;
					}
				}

				done = false;
				while (!done)
				{
					try	
					{
						startTime = System.currentTimeMillis();
 						treeMap.floorKey(maxKey);
						elapsedTime = System.currentTimeMillis() - startTime;
						treeMapFloorKeyTime+= elapsedTime;
						startTime = System.currentTimeMillis();
						treeMap.get(treeMap.floorKey(maxKey));
						elapsedTime = System.currentTimeMillis() - startTime;
						treeMapGetTime += elapsedTime;
						startTime = System.currentTimeMillis();
						treeMap.remove(treeMap.floorKey(maxKey));
						elapsedTime = System.currentTimeMillis() - startTime;
						treeMapRemoveTime += elapsedTime;
					}
				
					catch(NullPointerException e1)
					{
						done = true;
					}
				}

				HashMapPutTime[ndx] = hashMapPutTime;
				TreeMapPutTime[ndx] = treePutTime;
				HashMapFloorKeyTime[ndx] = hashMapFloorKeyTime;
				TreeMapFloorKeyTime[ndx] = treeMapFloorKeyTime;
				HashMapGetTime[ndx] = hashMapGetTime;
				TreeMapGetTime[ndx] = treeMapGetTime;
				HashMapRemoveTime[ndx] = hashMapRemoveTime;
				TreeMapRemoveTime[ndx] = treeMapRemoveTime;
				
				//Basic progress bar
				System.out.print(String.format("%.2f",100* ndx/(float)numIter) +
				"% done \r"); 
			}
		}

		printStatistics(HashMapGetTime, HashMapPutTime, HashMapFloorKeyTime, 
		HashMapRemoveTime, TreeMapGetTime, TreeMapPutTime, TreeMapFloorKeyTime, TreeMapRemoveTime, numIter, args[2]);
	}

	/**
	 * prints all the displayed sentence and all
	 * the statistical data
	 * @param (parameter name) (Describe the first parameter here)
	 * @param (parameter name) (Do the same for each additional parameter)
	 * @return (does not have a return value)
	 */
	
	/**
	 * prints all the displayed sentence and all
	 * the statistical data
	 * @param (A) (An array that stores HashMapGetTime)
	 * @param (B) (An array that stores HashMapPutTime)
	 * @param (C) (An array that stores HashMapFloorKeyTime)
	 * @param (D) (An array that stores HashMapRemoveTime)
	 * @param (E) (An array that stores TreeMapGetTime)
	 * @param (F) (An array that stores TreeMapPutTime)
	 * @param (G) (An array that stores TreeMapFloorKeyTime)
	 * @param (H) (An array that stores TreeMapRemoveTime)
	 * @param (i) (An integer that stores the length of array)
	 * @param (fileName) (a string that stores the filename )
	  @return (does not have a return value)
	 */
	private static void printStatistics(long[]A, long[]B, long[]C, long[]D, long[]E, long[]F, long[]G, long[]H, int i, String fileName)
	{
		System.out.println("Results from " + i + " runs of . ./files/" + fileName);
		System.out.println(" ");
		System.out.println("HashMap: get");
		System.out.println("--------------------");
		System.out.println("Min : "+ Long.toString(findMin(A)));
		System.out.println("Max : "+ Long.toString(findMax(A)));
		System.out.println("Mean : "+ Double.toString(findMean(A)));
		System.out.println("Std Dev : "+ Double.toString(findStdDev(A)));
		System.out.println(" ");

		System.out.println("HashMap: put");
		System.out.println("--------------------");
		System.out.println("Min : "+ Long.toString(findMin(B)));
		System.out.println("Max : "+ Long.toString(findMax(B)));
		System.out.println("Mean : "+ Double.toString(findMean(B)));
		System.out.println("Std Dev : "+ Double.toString(findStdDev(B)));
		System.out.println(" ");

		System.out.println("HashMap: floorKey");
		System.out.println("--------------------");
		System.out.println("Min : "+ Long.toString(findMin(C)));
		System.out.println("Max : "+ Long.toString(findMax(C)));
		System.out.println("Mean : "+ Double.toString(findMean(C)));
		System.out.println("Std Dev : "+ Double.toString(findStdDev(C)));
		System.out.println(" ");

		System.out.println("HashMap: remove");
		System.out.println("--------------------");
		System.out.println("Min : "+ Long.toString(findMin(D)));
		System.out.println("Max : "+ Long.toString(findMax(D)));
		System.out.println("Mean : "+ Double.toString(findMean(D)));
		System.out.println("Std Dev : "+ Double.toString(findStdDev(D)));
		System.out.println(" ");

		System.out.println("TreeMap: get");
		System.out.println("--------------------");
		System.out.println("Min : "+ Long.toString(findMin(E)));
		System.out.println("Max : "+ Long.toString(findMax(E)));
		System.out.println("Mean : "+ Double.toString(findMean(E)));
		System.out.println("Std Dev : "+ Double.toString(findStdDev(E)));
		System.out.println(" ");

		System.out.println("TreehMap: put");
		System.out.println("--------------------");
		System.out.println("Min : "+ Long.toString(findMin(F)));
		System.out.println("Max : "+ Long.toString(findMax(F)));
		System.out.println("Mean : "+ Double.toString(findMean(F)));
		System.out.println("Std Dev : "+ Double.toString(findStdDev(F)));
		System.out.println(" ");

		System.out.println("TreeMap: floorKey");
		System.out.println("--------------------");
		System.out.println("Min : "+ Long.toString(findMin(G)));
		System.out.println("Max : "+ Long.toString(findMax(G)));
		System.out.println("Mean : "+ Double.toString(findMean(G)));
		System.out.println("Std Dev : "+ Double.toString(findStdDev(G)));
		System.out.println(" ");

		System.out.println("TreeMap: remove");
		System.out.println("--------------------");
		System.out.println("Min : "+ Long.toString(findMin(H)));
		System.out.println("Max : "+ Long.toString(findMax(H)));
		System.out.println("Mean : "+ Double.toString(findMean(H)));
		System.out.println("Std Dev : "+ Double.toString(findStdDev(H)));
		System.out.println(" ");
	}

	/**
	 * find and return the minimum value of a given array
	 *
	 * @param (array) (an array that stores large integer)
	 * @return (return the minimum value)
	 */

	private static long findMin(long[]array)
	{
		long min = array[0];
		for (int i =1;i<array.length;i++){
			if (array[i] < min){
				min = array[i];
			}
		}
		return min;
	}

	/**
	 * find and return the maximum value of a given array
	 *
	 * @param (array) (an array that stores large integer)
	 * @return (return the maximum value)
	 */
	private static long findMax(long[]array)
	{
		long max = array[0];
		for (int i =1;i<array.length;i++){
			if (array[i] > max){
				max = array[i];
			}
		}
		return max;
	}

	/**
	 * find and return the sum value of a given array
	 *
	 * @param (array) (an array that stores large integer)
	 * @return (return the sum)
	 */
	private static long foundSum(long[]array){
		long sum =0;
		for (int i=0;i<array.length;i++){
			sum = sum + array[i];
		}
		return sum;
	}

	/**
	 * find and return the mean value of a given array
	 *
	 * @param (array) (an array that stores large integer)
	 * @return (return the average)
	 */
	private static double findMean(long[]array)
	{
		double sum = (double)foundSum(array);
		double size = (double)array.length;
		double average = sum/(size);
		double result = Math.round(average*1000.000)/1000.000;
		return result;
	}

	/**
	 * find and return the standard deviation of a given array
	 *
	 * @param (array) (an array that stores large integer)
	 * @return (return the standard deviation)
	 */
	private static double findStdDev(long[]array)
	{
		double average = findMean(array);
		double [] list = new double [array.length];
		for (int i=0;i<array.length;i++)
		{
			list[i] = Math.pow(array[i]-average, 2);
		}
		double sum =0;
		for (int i=0;i<list.length;i++){
			sum = sum + list[i];
		}
		double size = (double)list.length;
		double average2 = sum/size;
		double stdDev = Math.sqrt(average2);
		double result = Math.round(stdDev*1000.000)/1000.000;
		return result;
	}
}