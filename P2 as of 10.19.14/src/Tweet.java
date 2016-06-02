/**
 * Stores the time, message and user of a Tweet
 */
class Tweet{
	int time;
	String message;
	String user;

    /**
     * Constructs a Tweet for user with message at time. 
     * Throws a TweetTooLongException if message over 140 characters.
     * 
     * @param time time at which tweet occured
     * @param message message of the tweet, <=140 characters
     * @param user the person who tweeted the tweet 
     * @throws TweetTooLongException if message over 140 characters 
     */
    public Tweet(int time, String message, String user){ 
    	this.time = time;
    	this.message = message;
    	this.user = user;
    }

    /** 
     * Returns the stored message of the Tweet
     * @return the message
     */
    public String getMessage(){
    	return message;
    }
    
    /** 
     * Returns the stored time of the Tweet
     * @return the stored time
     */
    public int getTime(){
    	return time;
    }

    /** 
     * Returns the user who tweeted the Tweet
     * @return the user
     */
    public String getUser(){
    	return user;
    }

    /** 
     * Print the Tweet with the following format: <TIME> <USER>:<MESSAGE>
     */
    public void print(){
    	System.out.println(time + " " + user + ":" + message );
    }
}

