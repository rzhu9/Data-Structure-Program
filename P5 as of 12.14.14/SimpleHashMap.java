///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: MapBenchmark.java
// File: SimpleHashMap.java
// Semester: CS367 Fall 2014
//
// Author: Ruihao Zhu
// CS Login: ruihao
// Lecturer's Name: Jim Skrentny
// Lab Section: (your lab section number)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
// CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
// If allowed, learn what PAIR-PROGRAMMING IS, // choose a partner wisely, and complete this section.
//
// Pair Partner: Aren Lorenson
// CS Login: aren
// Lecturer's Name: Jim Skrentny
// Lab Section: (your partner's lab section number)
//
// STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits: (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 *
 * A map is a data structure that creates a key-value mapping. Keys are
 * unique in the map. That is, there cannot be more than one value associated
 * with a same key. However, two keys can map to a same value.
 *
 * The SimpleHashMap takes two generic parameters, K
 * and V, standing for the types of keys and values respectively.
 *
 */
public class SimpleHashMap<K extends Comparable<K>,V> implements SimpleMapADT<K , V> {


	private int[] tableSizes = { 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437, 102877,
			205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939, 210719881, 
			421439783, 842879579, 1685759167};
	private double lf = 0.75;
	private int currSizeIndex = 0;	
	private LinkedList <Entry<K,V>> [] hashMap = (LinkedList<Entry<K,V>>[]) new LinkedList[tableSizes[0]];	//TODO: ARE NULL

	public SimpleHashMap()
	{

	} 

	/**
	 * Returns the index in the Linked List an entry is hashed to 
	 *
	 * @return the index an entry is placed  
	 */
	private int hash(K k) 
	{
		int hashMapIndex = k.hashCode() % hashMap.length;
		if (hashMapIndex < 0)
		{
			hashMapIndex += tableSizes[currSizeIndex];
			return hashMapIndex;
		}
		else
			return hashMapIndex;
	} 


	/**
	 * Returns the value to which the specified key is mapped, or null if this
	 * map contains no mapping for the key.
	 *
	 * @param key the key whose associated value is to be returned
	 * @return the value to which the specified key is mapped, or null
	 * if this map contains no mapping for the key
	 * @throws NullPointerException if the specified key is null
	 */
	public V get(K key) 
	{	
		if(key == null)
		{
			throw new NullPointerException("tried to get value of null key");
		}
		if (hashMap[hash(key)] != null)
		{
			for(int i=0; i< hashMap[hash(key)].size(); i++)
			{
				K toCompare = hashMap[hash(key)].get(i).getKey();	
				if(toCompare.equals(key))
				{
					return hashMap[hash(key)].get(i).getValue();
				}
			}
			return null;
		}
		return null;
	}   

	/**
	 * Associates the specified value with the specified key in this map.
	 * Neither the key nor the value can be null. If the map
	 * previously contained a mapping for the key, the old value is replaced.
	 *
	 * @param key key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with key, or
	 * null if there was no mapping for key.
	 * @throws NullPointerException if the key or value is null
	 */

	public V put(K key, V value) 	
	{
		if (key.equals(null) || value.equals(null)) //passed null
		{
			throw new NullPointerException();
		}
		int count = 0;
		for (int i=0; i<hashMap.length; i++)
		{
			if (hashMap[i] != null)
			{
				count++;	
			}
		}

		if ((count / hashMap.length) >= lf)
		{
			int capacity = tableSizes[currSizeIndex+1];
			LinkedList <Entry<K,V>> [] hashMap2 = (LinkedList<Entry<K,V>>[]) new LinkedList[capacity];
			System.arraycopy(hashMap, 0, hashMap2, 0, hashMap.length);
		}
		if(hashMap[hash(key)] != null)	//a key is mapped here
		{
			//is an identical key mapped?
			for(int i = 0; i < hashMap[hash(key)].size(); i++)
			{
				K toCompare = hashMap[hash(key)].get(i).getKey();
				if(toCompare.equals(key))	//yes
				{
					V previousValue = hashMap[hash(key)].get(i).getValue();
					hashMap[hash(key)].get(i).setValue(value);
					return previousValue;
				}
				hashMap[hash(key)].add(new Entry(key,value));	//no
				return null;
			}
		}
		else	//a key isn't mapped
		{
			LinkedList <Entry<K,V>> l = new LinkedList<Entry<K,V>> ();
			Entry<K,V> e = new Entry<K,V>(key,value);
			l.add(e);
			hashMap[hash(key)] = l; 
			return null;
		}
		return null;

	}   
	/**
	 * Removes the mapping for the specified key from this map if present. This
	 * method does nothing if the key is not in the map.
	 *
	 * @param key key whose mapping is to be removed from the map
	 * @return the previous value associated with key, or null
	 * if there was no mapping for key.
	 * @throws NullPointerException if key is null
	 */

	public V remove(K key) 	
	{
		if(key == null)
			throw new NullPointerException("can't remove a value for a null key");
		if (hashMap[hash(key)] != null)
		{
			for(int i = 0; i < hashMap[hash(key)].size(); i++)
			{
				K toCompare = hashMap[hash(key)].get(i).getKey();
				if(toCompare.equals(key))
				{
					V previousValue = hashMap[hash(key)].get(i).getValue();
					//remove the entry hashMap[hash(key)].get(i)
					hashMap[hash(key)].remove(i);
					return previousValue;
				}
			}
			return null;
		}
		else
		{
			return null;
		}
	}   

	/**
	 * Returns the greatest key less than or equal to the given key, or null if there is no such key. 
	 * Throws NullPointerException if key is null. 
	 * @param key key whose floor should be found
	 * @return the largest key smaller than the one passed to it
	 * @throws NullPointerException if key is null
	 */
	public K floorKey(K key)	
	{	
		if(key == null)
		{
			throw new NullPointerException("floor key passed null key");
		}

		K currFloorKey = null;	//any Entry's key
		K LLFloorKey = null;	//largest key for any Linked List
		K globalFloorKey = key;

		for (int i = 0; i < hashMap.length; i++)
		{
			if(hashMap[i] != null)	//hashMap[i] != null & must have key
			{
				try
				{
					if(hashMap[i].getFirst().getKey() != null)
					{
						Iterator <Entry<K,V>> itr = hashMap[i].iterator();
						itr.next();
						currFloorKey =  hashMap[i].get(0).getKey();

						//getNextKey <= globalFloorKey && > CurrFloorKey
						while(itr.hasNext())
						{
							if(itr.next().getKey() != null)
							{
								if (itr.next().getKey().compareTo(globalFloorKey)<= 0 && itr.next().getKey().compareTo(currFloorKey)> 1)
								{
									currFloorKey = itr.next().getKey();	//largest key of all Entrys
								}
								itr.next();
							}
						}

						if(currFloorKey.compareTo(LLFloorKey) > 0)	//largest key of all LLs
						{
							LLFloorKey = currFloorKey;
						}
					}
				}

				catch(NullPointerException e1)
				{
					LLFloorKey = currFloorKey;
				}
				catch(NoSuchElementException e2)
				{}
			}
		}
		return LLFloorKey;
	}
}