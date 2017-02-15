package sondow.fishies;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Tweeter {

	public Status tweet(String message) {
		Twitter twitter = TwitterFactory.getSingleton();
		try {
			Status status = twitter.updateStatus(message);
			System.out.println("Successfully tweeted message: " + message + " with status " + status);
			return status;
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return null;
	}
}
