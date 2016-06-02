///////////////////////////////////////////////////////////////////////////////
// ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File: MapBenchmark.java
// File: Entry.java
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

/**
     * A map entry (key-value pair).
     */
public class Entry<K, V> {
    private K key;
    private V value;

    /**
     * Constructs the map entry with the specified key and value.
     */
    public Entry(K k, V v) {
       this.key = k;
       this.value = v;
    }

    /**
     * Returns the key corresponding to this entry.
     *
     * @return the key corresponding to this entry
     */
    public K getKey() {
        return key;
    }

    /**
     * Returns the value corresponding to this entry.
     *
     * @return the value corresponding to this entry
     */
    public V getValue() {
        return value;
    }

    /**
     * Replaces the value corresponding to this entry with the specified
     * value.
     *
     * @param value new value to be stored in this entry
     * @return old value corresponding to the entry
     */
    public V setValue(V value) {
        V old = this.value;
    	this.value=value;
    	return old;
    }
}