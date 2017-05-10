package sondow.fishies;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * The function that AWS Lambda will invoke.
 *
 * @author joesondow
 */
public class LambdaRequestHandler implements RequestHandler<Object, Object> {

    /*
     * (non-Javadoc)
     *
     * @see com.amazonaws.services.lambda.runtime.RequestHandler#handleRequest(java. lang.Object,
     * com.amazonaws.services.lambda.runtime.Context)
     */
    public Object handleRequest(Object input, Context context) {
        AquariumBuilder builder = new AquariumBuilder();

        // AWS Lambda only allows underscores in environment variables, not dots, so the default ways twitter4j
        // finds keys aren't possible. Instead, write your own code that gets the configuration either from
        // Lambda-friendly environment variables. If those variables are missing, default to using the regular
        // twitter4j algorithm of getting keys from a default twitter4j.properties file at the project root,
        // or on the classpath, or in WEB-INF.
        ConfigurationBuilder cb = new ConfigurationBuilder();

        // If just one set of keys is provided in environment variables, use that key set.
        String consumerKey = Environment.get("twitter4j_oauth_consumerKey");
        String consumerSecret = Environment.get("twitter4j_oauth_consumerSecret");
        String accessToken = Environment.get("twitter4j_oauth_accessToken");
        String accessTokenSecret = Environment.get("twitter4j_oauth_accessTokenSecret");

        // Override with a specific account if available. This mechanism allows us to provide multiple key sets
        // in the AWS Lambda configuration, and switch which Twitter account to target by retyping just the
        // account name in the configuration.
        String account = Environment.get("twitter_account");
        if (account != null) {
            String specificConsumerKey = Environment.get(account + "_twitter4j_oauth_consumerKey");
            consumerKey = (specificConsumerKey != null) ? specificConsumerKey : consumerKey;
            String specificConsumerSecret = Environment.get(account + "_twitter4j_oauth_consumerSecret");
            consumerSecret = (specificConsumerSecret != null) ? specificConsumerSecret : consumerSecret;
            String specificAccessToken = Environment.get(account + "_twitter4j_oauth_accessToken");
            accessToken = (specificAccessToken != null) ? specificAccessToken : accessToken;
            String specificAccTokSecret = Environment.get(account + "_twitter4j_oauth_accessTokenSecret");
            accessTokenSecret = (specificAccTokSecret != null) ? specificAccTokSecret : accessTokenSecret;
        }
        if (consumerKey != null) {
            cb.setOAuthConsumerKey(consumerKey);
        }
        if (consumerSecret != null) {
            cb.setOAuthConsumerSecret(consumerSecret);
        }
        if (accessToken != null) {
            cb.setOAuthAccessToken(accessToken);
        }
        if (accessTokenSecret != null) {
            cb.setOAuthAccessTokenSecret(accessTokenSecret);
        }
        Configuration config = cb.setTrimUserEnabled(true).build();

        Tweeter tweeter = new Tweeter(config);
        return tweeter.tweet(builder.build());
    }

}
