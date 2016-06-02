///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  MovieFlix.java
// File:             MovieDatabase.java
// Semester:         CS67 Fall 2014
//
// Author:           Aren Lorenson (alorenson@wisc.edu)
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
// Credits:          (n/a)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.List;
import java.util. ArrayList;
import java.util.Iterator;

/**
 * Creates MovieDatabase objects that contain movies w/ a title & cast + 
 * getters & setters to make use of the title & cast of any movie within.
 *
 * <p>Bugs: (none)
 *
 * @author (aren, ruihao)
 */

public class MovieDatabase 
{
	//Set up all variables
	//Set up a new object called MovieDatabase implemented by arraylist 
	//that stores movies
	List <Movie> MovieDatabase = new ArrayList<Movie>();	

	//constructor
	public MovieDatabase()
	{
		MovieDatabase = new ArrayList <Movie>();
	}
	
	/**
	 * If a movie w/ title t is not in a database, one is contructed & added;
	 * if one is, nothing happens.
	 *
	 * @param (t) (movie title to be added to database)
	 * @return (void)
	 */
	
	public void addMovie(String t)
	{
		// create a new movie with its name standardized to upper and lower
		//cases that fit the instruction's requirement 
		Movie movie = new Movie(standardizeTitle(t));
		//if this movie is already exists in the database, then do nothing
		//otherwise, add it to the MovieDatabase
		if (containsMovie(standardizeTitle(t))){
		}
		else
		{
			MovieDatabase.add(movie);
		}
	}
	/**
	 * Adds actor n to cast of movie titled t if n isn't already in the cast;
	 * else do nothing
	 *
	 * @param (n) (actor's name)
	 * @param (t) (movie title)
	 * @return (void)
	 */
	public void addActor(String n, String t)	
	{
		//standardize the input parameter
		String test = standardizeTitle(t);	//comment out
		Iterator <Movie> itr = iterator();
		//if the movie with input t is not in the database, throw exception
		if (!containsMovie(standardizeTitle(t))){
			throw new IllegalArgumentException();
		}
		//otherwise, go over the list of casts of this movie to see whether n 
		//is in the list of cast or not
		//if not, add it
		else{
			while (itr.hasNext()){
				Movie movie = itr.next();
				if (movie.getTitle().equals(standardizeTitle(t))){
					List <String> cast = movie.getCast();
					cast.add(n);
				}
			}
		}
	}
	
	/**
	 * Removes movie of title t from database if it exists; else print error
	 * & do nothing
	 *
	 * @param (t) (movie title)
	 * @return (true iff successful)
	 */
	
	public boolean removeMovie(String t)
	{
		Iterator <Movie> itr = iterator();
		int count = 0;
		//iterate through movies, check title against t, if t exists 
		//remove all instances of movie with a title t in database
		while(itr.hasNext())
		{
			if(itr.next().getTitle() == t)	//can't be case sensitive
			{
				count++;
				MovieDatabase.remove(itr.next());
			}
		}
		// if nothing is removed, return true
		// else return false
		if (count != 0)
		{
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * Checks if a movie of title t is in database.
	 *
	 * @param (t) (movie title)
	 * @return (true if a movie w/ title t is in database)
	 */
	
	public boolean containsMovie(String t)
	{
		int count =0;
		Iterator <Movie> itr = MovieDatabase.iterator();
		// go over the whole database to see whether movie with
		// a title t exists or not
		while(itr.hasNext())
		{
			if(itr.next().getTitle().equals(t))	
			{
				// if found, increase the count
				count++;
			}
		}
		// if there is not, return false
		if (count == 0){
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Checks if an actor of name n is cast in any film in database.
	 *
	 * @param (n) (actor's name)
	 * @return (true if an actor w/ name n is in database)
	 */
	
	public boolean containsActor(String n)	
	{
		Iterator <Movie> itr = iterator();
		// go over the whole database, get the list of cast for each movie
		while(itr.hasNext())
		{

			List <String> cast = itr.next().getCast();
			Iterator <String> itr2 = cast.iterator();
			// go over each list to see whether there is any n in the list
			while(itr2.hasNext())
			{
				if (itr2.next().equals(n))
				{
					// if there is, return true
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if an actor named n is cast in a paticular film.
	 *
	 * @param (n) (actor's name)
	 * @param (t) (movie title)
	 * @return (true if an actor w/ name n is cast in movie titled t)
	 */
	
	public boolean isCast(String n, String t)	
	{
		Iterator <Movie> itr = iterator();
		int count = 0;// used to check whether there is a movie with title t
		int count2 = 0;//tracks the position of iterator
		// Go over the database to see whether the move with a title t exists
		while(itr.hasNext())
		{
			//If it exists, increase the count
			//And check whether n is in t's cast
			if(itr.next().getTitle() == t)	//can't be case sensitive
			{
				count++;
				//get the list of cast of movie with title t
				//use the iterator to go over the list of cast
				List <String> cast = MovieDatabase.get(count2).getCast();
				Iterator <String> itr2 = cast.iterator();
				while(itr2.hasNext())
				{
					//if there is a cast named n, return true
					if (itr2.next()==n)
					{
						return true;
					}
				}
			}
		}
		//else return false
		return false;
	}

	/**
	 * Gets cast of movie titled t.
	 *
	 * @param (t) (movie title)
	 * @return (List of cast in movie t)
	 */
	
	public List<String> getCast(String t)
	{
		Iterator <Movie> itr = this.MovieDatabase.iterator();
		int count = 0;
		//go over the whole database to find the movie with title t
		while(itr.hasNext())
		{
			if(itr.next().getTitle().equals(t))	
			{
				//get the list of cast and return it
				List <String> cast = MovieDatabase.get(count).getCast();	
				count++;
				return cast;
			}
		}
		return null;
	}
	
	/**
	 * Gets all movie titles actor n appears in.
	 *
	 * @param (n) (actor name)
	 * @return (List of movie titles actor n appears in; null if n is not in
	 * database)
	 */
	
	public List<String> getMovies(String n)	//list of movie titles n starred in
	{
		List <String> filmsActorAppearsIn = new ArrayList <String>();
		Iterator <Movie> itr = MovieDatabase.iterator();
		//go over the whole database
		//for each movie, get list of cast and check whether it contains n
		//or not
		while(itr.hasNext()){
			Movie movie = itr.next();
			//if it contains n, add that movie title to a list 
				if(movie.getCast().contains(n))
				{
					filmsActorAppearsIn.add(movie.getTitle());
				}
			
		}
		
		if (filmsActorAppearsIn.size() != 0)
		{
			return filmsActorAppearsIn;
		}
		else
			return null;
	}
	
	/**
	 * Gets an iterator that goes over movies in MovieDatabase.
	 *
	 * @return (iterator that goes over movies in MovieDatabase)
	 */

	public Iterator<Movie> iterator()
	{
		Iterator <Movie> itr = MovieDatabase.iterator();
		return itr;
	}
	
	/**
	 * Gets size of MovieDatabase.
	 *
	 * @return (int of # of movies in database)
	 */
	
	public int size()
	{
		return MovieDatabase.size();
	}
	
	/**
	 * Removes actor n from all cast lists they appear in; nothing iff actor
	 * isn't in any movie.
	 * 
	 * @param (n) (actor name)
	 * 
	 * @return (true if successful; false if not)
	 */
	
	public boolean removeActor(String n)
	{
		Iterator <Movie> itr = this.MovieDatabase.iterator();
		int count = 0;
		int count2 = 0;
		//go over the whole database
		while(itr.hasNext())
		{
			//for each movie, go over its list of casts to find the cast we want
			List <String> cast = MovieDatabase.get(count2).getCast();
			Iterator <String> itr2 = cast.iterator();
			while(itr2.hasNext())
			{
				//when we find the n, remove it from the list of casts
				if (itr2.next()==n)
				{
					cast.remove(n);
					count++;
				}
			}
			count2++;
		}
		//if nothing is removed, return false
		//otherwise, return true
		if(count == 0) // if count == 0 no actor in any movies
			return false;

		else
			return true;

	}
	
	/**
	 * Changes the input string to the correct format: Capital first char of 
	 * every word; lowercase chars everywhere else.
	 * 
	 * @param (str) (string to be corrected)
	 * 
	 * @return (same String with revised capitalizations)
	 */
	
	private static String standardizeTitle(String str)
	{
		String correctTitle = "";
		//split the input phrase using space
		String[] line = str.split(" ");
		//for each word, set the first letter to be upperletter case
		//and set rest of letters to be lowerletter case
		for (int i=0;i<line.length;i++){
line[i]  = line[i].substring(0,1).toUpperCase() + line[i].substring(1).
		toLowerCase();	
		}
		//combine each corrected word to a long string, and return it
		for (int i=0;i<line.length;i++){
			if(i==0)
			{
				correctTitle = correctTitle + line[i];
			}
			else
				correctTitle = correctTitle + " " + line[i];

		}

		return correctTitle;
	}
}