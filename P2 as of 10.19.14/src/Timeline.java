import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields should come earlier in the list.
 */
class Timeline {	
	private SimpleLinkedList<Tweet> list;	
	private int size;	//# of tweets in timeline
	/**
	 * Constructs an empty timeline
	 */
	public Timeline(int size, SimpleLinkedList<Tweet> list){
		this.size = size;	//redundant given numItems?
		this.list = list;

	}

	/**
	 * Adds a single tweet to the Timeline in chronological order
	 * 
	 * @param tweet the tweet to add
	 */
	public void add(Tweet tweet) 
	{
		list.add(tweet);	
		size++;
	}

	/**
	 * Adds an ordered list of tweets to the Timeline
	 * @param tweets the list of tweets to add
	 */
	public void add(List<Tweet> tweets) 	//modifies a timeline's list; it's tweet list
	{
		List <Integer> time = new ArrayList<Integer>();

		for (int i=0;i<tweets.size();i++)
		{
			time.add(tweets.get(i).getTime());	

		}
		size += time.size();	//increment # tweets in timeline:
		int min = time.get(0);	
		int tweetsOriginalSize = tweets.size();
		for (int j =0;j<tweetsOriginalSize;j++){
			for (int i=0;i<time.size();i++){
				if(time.size() == 1)
				{
					min=time.get(0);
				}
				if (time.get(i)<min){
					min = time.get(i);

				}
			}
			//find the highest time
			//get the tweets with the highest time, and add it to the database
			//remove and go on

			list.add(tweets.get(time.indexOf(min)));	
			tweets.remove(tweets.get(time.indexOf(min)));	
			time.remove(time.indexOf(min));	
			if (time.size() != 0)
			{
				min = time.get(0);
			}
		}

	}



	/**
	 * Removes all tweets by user from the Timeline
	 * 
	 * @param user the user whose tweets should be removed
	 */
	public void remove(String user)	
	{
		int originalListSize = list.size(); 
		int p = 0;	
		while (p<originalListSize)
		{	

			if (list.get(p).getUser().equals(user)){

				list.remove(p);	
				p = 0;	
			}
			else
			{
				if(p < list.size()-1)
				{p++;
				}
				else
				{		break;
				}
			}
		}
	}
	/**
	 * Create a new Timeline containing only the tweets containing keyword
	 * 
	 * @param keyword the keyword to search for
	 * @return a Timeline of tweets containing keyword
	 */
	public Timeline search(String keyword)
	{
		SimpleLinkedList <Tweet> empty = new SimpleLinkedList <Tweet> ();
		Timeline a = new Timeline(0,empty);

		for (int i=0;i<list.size();i++)
		{
			Scanner in = new Scanner(list.get(i).getMessage());
			while (in.hasNext() ){
				String yoyo = in.next();	//for debug
				if (checkKeywords (keyword, yoyo) == true){	//TODO: fails for char case
					a.add(list.get(i));
				}
				
			}
			in.close();
		}
		return a;
	}

	/**
	 * Print each tweet in the timeline
	 */
	public void print()
	{
		if(this.size != 0)	
		{
			for (int i=0;i<list.size();i++){	//b/c null counts in size?
				list.get(i).print();
			}
		}
		else
		{
			System.out.println("Search failed");	//TODO: match output
		}
	}


	/**
	 * Print each tweet in the timeline since time
	 * 
	 * @param time the largest time to print tweets
	 */
	public void print(int time)
	{
		for (int i=0;i<list.size();i++){
			if (list.get(i).getTime() < time) {
				list.get(i).print();

			}
		}
	}
	private boolean checkKeywords (String keyword, String check)
	{
	
		int lengthKey = keyword.length();
		int lengthCheck = check.length();
		int count =0;
		if(lengthKey > lengthCheck)
		{
			return false;
		}
		if(lengthKey == lengthCheck)
		{
			if (keyword.equals(check)){
				return true;
			}
			else {
				return false;
			}
		}
		else	// does not work for varying cases
		{ 
			
			for (int i=0;i<lengthCheck-lengthKey + 1;i++)	
			{
				String gg = check.substring(i,i+lengthKey-1);	//for debug
				if (check.substring(i,i+lengthKey).equals(keyword))
						{
							count++;
						}
			}
			if (count !=0){
				return true;
			}
			else{
				return false;
			}
		}
	}
}
