///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS

// Title:            P1
// Files:            (MovieFlix.java, Movie.java, MovieDatabase.java)
// Semester:         CS367 Fall 2014
//
// Author:           (Aren Lorenson)
// Email:            (alorenson@wisc.edu)
// CS Login:         (aren)
// Lecturer's Name:  (Skrentny)
// Lab Section:      (n/a)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If allowed, learn what PAIR-PROGRAMMING IS, 
//                   choose a partner wisely, and complete this section.
//
// Pair Partner:     (Ruihao Zhu)
// Email:            (rzhu9@wisc.edu)
// CS Login:         (ruihao)
// Lecturer's Name:  (Skrentny)
// Lab Section:      (n/a)
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (n/a)
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Enables user to interact with a movie database created by passing a txt file
 * of appropriate format and get/set information concerning movies, their 
 * titles, and the actors cast in them.
 * 
 * <p>Bugs: (none )
 *
 * @author (aren, ruihao)
 */

public class MovieFlix {
	public static MovieDatabase a = new MovieDatabase();
	
/**	 * Enables user to interact with a movie database created by passing a txt 
 	 * file of appropriate format and get/set information concerning movies, 
 	 * their titles, and the actors cast in them.
	 *
	 * @param (String[] args) (default main method arguments, w/ exception of 
	 * args[2] being the file passed in to make the database)
	 *
	 * @return (void)
	 */
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner stdin = new Scanner (System.in);
				File file = new File(args[2]);
		//check the size of command line argument
		if (args.length != 3){
			System.out.println("Usage: java MovieFlix FileName");
			System.exit(0);
		}
		//check whether the input file exists and readable
		if (!(file.exists() && file.canRead())){
			System.out.println("Error: Cannot access input file");
			System.exit(0);
		}
		Scanner fileIn = new Scanner(file);  //for console input	
		//go over each line of the input file
		//add movies to the database first
		//then add actor to movies that belong to that actor
		while(fileIn.hasNextLine())
		{	
			String[] line = fileIn.nextLine().split(",");

			for (int i=1;i<line.length;i++) {
				a.addMovie(standardizeTitle(line[i]));	

			}
			for (int i=1; i<line.length;i++) {
				a.addActor(standardizeTitle(line[0]),standardizeTitle(line[i]));
				//add actor to list
			}
		}	    	
		fileIn.close();

		boolean done = false;
		while (!done) {
			System.out.print("Enter option (cdprswx): ");
			String input = stdin.nextLine();

			//only do something if the user enters at least one character
			if (input.length() > 0) {
				char choice = input.charAt(0); //strip off option character
				String remainder = "";         //will hold the remaining input
				if (input.length() > 1) {      //if there is an argument
					//trim off any leading or trailing spaces
					remainder = input.substring(1).trim(); 

					switch (choice) { //the commands that have arguments

					case 'c': {
						//check whether the movie with a title t is in database 
						//if not found, print the message
						Iterator <Movie> x = a.iterator();
						List <String> l = new ArrayList<String>();
						String title = standardizeTitle(remainder);
						boolean check = a.containsMovie(title);	
						int lastIndex = 1;
						if (check == false)
						{
							System.out.println("movie not found");
							break;                    		
						}
						//found the list of casts of that movie and print it
						else {
							while (x.hasNext()){
								Movie movie = x.next();
								if(movie.getTitle().equals(title)){
									l = movie.getCast();

								}
							}
							System.out.print(l.get(0)+ ", ");	
							for (int i=1;i<l.size()- 1;i++){
								System.out.print(l.get(i) +", ");
								lastIndex++;
							}
							System.out.println(l.get(lastIndex)); 
						}
						break;
					}
					
					case 'p': {
						//check whether database contains the actor or not
						//if not, print the message
						a.iterator();
						if(!a.containsActor(standardizeTitle(remainder))){	
							System.out.println("actor not found");
							break;
						}
						//if found, get the list of movies and print them
						else{
					List <String> l = a.getMovies(standardizeTitle(remainder));
							int c = 0;
							for (int i=0; i < l.size() - 1; i++)
							{
								System.out.print(l.get(i) + ", ");	
								c++;
							}
							System.out.print(l.get(c));
						}
						System.out.println();
						break;
					}

					case 'r': {
						//check whether the database contains the remainder
						//if not found, print message
						a.iterator();
						if(!a.containsMovie(standardizeTitle(remainder))){
							System.out.println("movie not found");
							break;
						}
						//if found, remove the movie and print message
						else{
							a.removeMovie(standardizeTitle(remainder));
							System.out.println("movie removed");
						}

						break;

					}
					case 's': {
					// The following code reads in a comma-separated sequence 
					// of strings.  If there are exactly two strings in the 
					// sequence, the strings are assigned to name1 and name2.
					// Otherwise, an error message is printed.
						String[] tokens = remainder.split("[,]+");
						List <String> titles = new ArrayList <String> ();
						boolean notCoStars = false;
					//true iff name1 & name2 are not cast in same movie
						if (tokens.length != 2) {
					System.out.println("need to provide exactly two names");
						}
						else {
							String name1 = tokens[0].trim();
							String name2 = tokens[1].trim();
							name1 = standardizeTitle(name1);
							name2 = standardizeTitle(name2);
				//go over the database to see whether there is a movie contains 
						//both actors
							Iterator <Movie> itr = a.iterator();
						if (a.containsActor(name1) && a.containsActor(name2))
							{
								while (itr.hasNext())
								{
									Movie movie = itr.next();
						//if found, print that movie, if not, go to the next one
		if (movie.getCast().contains(name1) && movie.getCast().contains(name2))	
									{
										titles.add(movie.getTitle());
									}
								}
								if (titles.size()== 0)
								{
									notCoStars = true;
								}
							}
							//if no movies contains both actors, print none
		if (!a.containsActor(name1) && !a.containsActor(name2) && notCoStars)
							{
								System.out.println("none");
								break;
							}
							int c = 0;
							for (int i = 0;  i < titles.size()-1; i++)
							{
								System.out.print(titles.get(i) + ", ");
								c++;
							}
							System.out.print(titles.get(c));
							System.out.println();
						}
						break;
					}
					case 'w': {
						String remainder2 = standardizeTitle(remainder);
						Iterator <Movie> itr = a.iterator();
				//go over the database to check whether database contains actor
				//if contains, remove all the movies that fits the request
						if(a.containsActor((remainder2)))
						{
							while (itr.hasNext())
							{
								List <String> l = itr.next().getCast();
								if(l.contains(remainder2))
								{
									l.remove(remainder2);
								}
							}
					System.out.println(remainder2 + " withdrawn from all " +
							"movies");
						}
						//if not found, print the message
						else
						{
							System.out.println("actor not found");
							break;
						}
						break;
					}
					//if input letter is not what we want, print message
					default: //ignore invalid commands
						System.out.println("Incorrect command.");
						break;

					} // end switch
				} // end if
				else { //if there is no argument
					switch (choice) { //the commands without arguments
					//all methods that required by case d is created 
					//call the method and print the result from the methods
					case 'd': 
				//part1 Display on a line: "Movies: integer, Actors: integer"
						int size = a.size();
						int actorSize = Actors().size();
				System.out.println("Movie: " + size + ", Actors: " + actorSize);

				//part2 Display on a line: "# of actors/movie: most integer, 
				//least integer, average integer"
			List <Integer> numberOfActorsPerMovie = NumberOfActorsPerMovie();
						int max = foundMax(NumberOfActorsPerMovie());
						int min = foundMin(NumberOfActorsPerMovie());
int average = (int) Math.round(foundAverage(foundSum(NumberOfActorsPerMovie()),
		numberOfActorsPerMovie.size()));
System.out.println("# of actors/movie: " + "most " + max + ", " + "least " + min
		+ ", " + "average " + average);

//part3 Display on a line: "# of movies/actor: most integer, least integer, 
//average integer"
			List <Integer> NumberOfMoviesPerActor = NumberOfMoviesPerActor();
						int max2 = foundMax(NumberOfMoviesPerActor());
						int min2 = foundMin(NumberOfMoviesPerActor());
int average2 = (int) Math.round(foundAverage(foundSum(NumberOfMoviesPerActor()),
		NumberOfMoviesPerActor.size()));
System.out.println("# of movies/actor: " + "most " + max2 + ", " + "least "+ 
		min2 + ", "+"average " + average2);

				//part4 Display on a line: "Largest Cast: movie(s) [integer]"
						String print1= " ";
			List <String> MoviesWithTheLargestCast = new ArrayList<String>();
						for (int i=0;i<NumberOfActorsPerMovie().size();i++){
							if (NumberOfActorsPerMovie().get(i) == max){
								MoviesWithTheLargestCast.add(Movies().get(i));
							}
						}
						for (int i=0; i<MoviesWithTheLargestCast.size()-1;i++){
					print1 = print1 + MoviesWithTheLargestCast.get(i) +", ";
						}
print1 = print1 + MoviesWithTheLargestCast.get(MoviesWithTheLargestCast.size()
		-1);
			System.out.println( "Largest Cast: " + print1 + " [" + max + "]");


				//part5 Display on a line: "Smallest Cast: movie(s) [integer]"
						String print2 = " ";
			List <String> MoviesWithTheSmallestCast = new ArrayList<String>();
						for (int i=0;i<NumberOfActorsPerMovie().size();i++){
							if (NumberOfActorsPerMovie().get(i) == min){
								MoviesWithTheSmallestCast.add(Movies().get(i));
							}
						}
						for (int i=0; i<MoviesWithTheSmallestCast.size()-1;i++){
					print2 = print2 + MoviesWithTheSmallestCast.get(i) + ", ";
						}
print2 = print2 + MoviesWithTheSmallestCast.get(MoviesWithTheSmallestCast.size()
		-1);
			System.out.println("Smallest Cast: " + print2 + " [" + min+ "]");
						break;
					case 'x':
						done = true;
						System.out.println("exit");
						break;

					default: //a command with no argument
						System.out.println("Incorrect command.");
						break;
					} //end switch
				} //end else 
			} //end if
		}//end while
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
		String[] line = str.split(" ");
		for (int i=0;i<line.length;i++){
line[i] = line[i].substring(0,1).toUpperCase() + line[i].substring(1).
toLowerCase();
		}
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
	
	/**
	 * Finds all the actors in the database and form a list of their names.
	 * 
	 * @return (list of actor name strings)
	 */
	//
	
	private static List<String> Actors(){
		Iterator <Movie> itr = a.iterator();
		List <String> actors = new ArrayList<String>();
		while (itr.hasNext()){
			List <String> cast = itr.next().getCast();
			for (int i=0;i<cast.size();i++){
				if (!actors.contains(cast.get(i))){
					actors.add(cast.get(i));
				}
			}
		}
		return actors;
	}
	
	/**
	 * Gets int list of cast size per movie as big as there are movies.
	 * 
	 * @return (int list of cast size per movie)
	 */
	
	private static List<Integer> NumberOfActorsPerMovie(){
		List <Integer> numberOfCast = new ArrayList<Integer>();
		Iterator <Movie> itr = a.iterator();
		while (itr.hasNext()){
			numberOfCast.add(itr.next().getCast().size());
		}
		return numberOfCast;
	}

	/**
	 * Gets all movie titles in the database and form a list in order they're 
	 * stored.
	 * 
	 * @return (String list of all movie titles)
	 */
	
	private static List<String> Movies(){
		Iterator <Movie> itr = a.iterator();
		List <String> movies = new ArrayList <String>();
		while (itr.hasNext()){
			movies.add(itr.next().getTitle());
		}
		return movies;
	}
	
	/**
	 * Gets int list of # of films every actor stars in.
	 * 
	 * @return (int list of # of films every actor stars in)
	 */
	
	private static List<Integer> NumberOfMoviesPerActor(){
		List <Integer> numberOfMovie = new ArrayList<Integer>();
		for (int i=0;i<Actors().size();i++){
			List <String> l = a.getMovies(Actors().get(i));
			numberOfMovie.add(l.size());
		}
		return numberOfMovie;
	}

	/**
	 * Gets max of an int list
	 * 
	 * @ param (l) (any int list).
	 * 
	 * @return (int max that appeared in passed int list)
	 */
	
	private static int foundMax(List <Integer> l){
		int max = l.get(0);
		for (int i =1;i<l.size();i++){
			if (l.get(i) > max){
				max = l.get(i);
			}
		}
		return max;
	}

	/**
	 * Gets min of an int list
	 * 
	 * @ param (l) (any int list).
	 * 
	 * @return (int min that appeared in passed int list)
	 */
	
	private static int foundMin(List <Integer> l){
		int min = l.get(0);
		for (int i =1;i<l.size();i++){
			if (l.get(i) < min){
				min = l.get(i);
			}
		}
		return min;
	}

	/**
	 * Gets sum of an int list
	 * 
	 * @ param (l) (any int list).
	 * 
	 * @return (int sum of all values in passed int list)
	 */
	private static int foundSum(List <Integer> l){
		int sum =0;
		for (int i=0;i<l.size();i++){
			sum = sum + l.get(i);
		}
		return sum;
	}

	/**
	 * Gets mean of an int list
	 * 
	 * @ param (l) (any int list).
	 * @ param (size) (# of indices of l).
	 * 
	 * @return (double mean of all values in passed int list)
	 */
	private static double foundAverage(int sum, int size){
		double sum1 = (double)sum;
		double size1 = (double)size;
		double average = sum1/size1;
		return average;
	}
} 
