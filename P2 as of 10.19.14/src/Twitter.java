// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class  Twitter{

	public static void main(String[] args) throws FileNotFoundException, TweetTooLongException {	

		List <String> follow = new ArrayList<String>();
		SimpleLinkedList<Tweet> original = new SimpleLinkedList<Tweet>();
		Timeline timeline = new Timeline(0,original);
		int time =0;
		List <Tweet> unorganizedTweets = new ArrayList<Tweet>();
		List<Tweet> unorganizedCopy = new ArrayList <Tweet>();

		for (int i=2;i<args.length;i++)
		{
			//remove the ".txt"
			follow.add(args[i].substring(0, args[i].length() -4));	
			File file = new File (args[i]);
			if (file.exists() && file.canRead()){	
				//TODO: Ask for what to do

				Scanner in = new Scanner(file);

				while (in.hasNextLine())
				{
					String[] line = in.nextLine().split(":");
					time = Integer.parseInt(line[0]);
					String message = "";
					message = line[1];	//TODO: ctach long tweet

					Tweet tweet = new Tweet(time,message,args[i].substring(0,args[i].length()-4));
					try
					{
					unorganizedTweets.add(tweet);
					}
					catch(TweetTooLongException e1)
					{
					continue;
					}
						unorganizedCopy = new ArrayList <Tweet> (unorganizedTweets);
					
					//				catch (TweetTooLongException ex)
					//				{
					//
					//				}
				}
				//				catch (TweetTooLongException)
				//				{
				//					//TODO: dont add tweet
				//					return;
				//				}
				in.close();
			}

		}
		timeline.add(unorganizedTweets);	
		boolean done = false;
		while (!done) {	

			Scanner stdin = new Scanner(System.in);  //for console input
			if (args.length<3){
				//Ask what do we do: throw ex?
			}

			//call to add makes null

			System.out.print("Enter option : ");

			List <String> users = new ArrayList<String>();

			for (int x=2;x<args.length;x++){
				//remove the ".txt"
				users.add(args[x].substring(0, args[x].length() -4));
			}
			String input = stdin.nextLine();
			//only do something if the user enters at least one character
			if (input.length() > 0) {
				String[] commands = input.split(" ");//split on space
				switch(commands[0]){	
				case "list":
					if(commands.length == 2)
					{
						if (commands[1].equals("users") ){	
							for (int k=0;k<users.size();k++){	
								System.out.println(users.get(k));
							}
						}
						else if (commands[1].equals("following")){
							for (int j=0;j<follow.size();j++){
								System.out.println(follow.get(j));
							}
						}
					}
					else {
						System.out.println("Invalid Command");
					}
					break;
				case "follow":	//TODO:invalid users (ints?) does take an int as a user 
					if (follow.contains(commands[1])){
						System.out.println("Warning: User already followed");
					}

					else{

						List <Tweet> copyOfCopyTweets = new ArrayList <Tweet> (unorganizedCopy);
						List <Tweet> toAdd = new ArrayList <Tweet> ();

						for (int i=2;i<args.length;i++)	//gets list of tweets for everyone already followed
						{	//TODO: try catch long tweets
							//remove the ".txt"

							File file = new File (args[i]);
							if (file.exists() && file.canRead()){	
								//TODO: Ask for what to do

								Scanner in = new Scanner(file);
								while (in.hasNextLine())	//could be made into method thats passed in
								{
									String[] line = in.nextLine().split(":");
									time = Integer.parseInt(line[0]);
									String message = "";
									message = line[1];
									//TODO: try catch here too
									Tweet tweet = new Tweet(time,message,args[i].substring(0,args[i].length()-4));

									for(int j=0; j<follow.size();j++)	
									{

										if(tweet.getUser().equals(follow.get(j)))
										{
											toAdd.add(tweet);
										}									
									}	
								}
								in.close();
							}
							else
							{
								System.out.println("File note readable");	//TODO: match output? print empty timeline?

							}
						}
						follow.add(commands[1]);
						//adds new user to be followed tweets to list
						for(int o = 0; o < unorganizedCopy.size(); o++)	
						{	

							if(unorganizedCopy.get(o).getUser().equals(commands[1]))	
							{
								toAdd.add(unorganizedCopy.get(o));	
							}

						}
						//unorganizedCopy = new ArrayList <Tweet> (copyOfCopyTweets);	//keeps the original timeline tweets
						SimpleLinkedList<Tweet> blank = new SimpleLinkedList <Tweet> ();
						Timeline t = new Timeline (0,blank);
						t.add(toAdd);
						timeline = t;	
						timeline.print();

					}

					break;
				case "unfollow":	
					if (!follow.contains(commands[1])){
						System.out.println("Warning: User not followed");
					}
					else{
						follow.remove(commands[1]);	

						timeline.remove(commands[1]);

					}
					break;
				case "search":	
					Timeline search = timeline.search(commands[1]);

					search.print();
					break;
				case "print":	//TODO: breaks if passed print + non int? 
					if(commands.length == 2)
					{
						timeline.print(Integer.parseInt(commands[1]));
					}
					else {
						timeline.print();
					}
					//	}
					break;
				case "quit":
					done = true;
					System.out.println("exit");
					break;
				default:  //a command with no argument
					System.out.println("Invalid Command");
					break;
				}
			} //end if
		} //end while
	} //end main

}


