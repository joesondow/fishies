package sondow.fishies;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;

public class Tweeter {

    private Configuration config;

    public Tweeter(Configuration config) {
        this.config = config;
    }

    public Status tweet(String message) {
        Twitter twitter = new TwitterFactory(config).getInstance();
        try {
            Status status = twitter.updateStatus(message);
            String msg = "Successfully tweeted message: " + message + " with status " + status;
            System.out.println(msg);
            return status;
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
