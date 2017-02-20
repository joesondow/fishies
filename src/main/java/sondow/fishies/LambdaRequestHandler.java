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
        // finds keys aren't
        // possible. Instead, write your own code that gets the configuration either from Lambda-friendly
        // environment variables
        // or from a default twitter4j.properties file at the project root, or on the classpath, or in WEB-INF.
        ConfigurationBuilder cb = new ConfigurationBuilder();
        String consumerKeyEnvVar = System.getenv("twitter4j_oauth_consumerKey");
        String consumerSecretEnvVar = System.getenv("twitter4j_oauth_consumerSecret");
        String accessTokenEnvVar = System.getenv("twitter4j_oauth_accessToken");
        String accessTokenSecretEnvVar = System.getenv("twitter4j_oauth_accessTokenSecret");
        if (consumerKeyEnvVar != null) {
            cb.setOAuthConsumerKey(consumerKeyEnvVar);
        }
        if (consumerSecretEnvVar != null) {
            cb.setOAuthConsumerSecret(consumerSecretEnvVar);
        }
        if (accessTokenEnvVar != null) {
            cb.setOAuthAccessToken(accessTokenEnvVar);
        }
        if (accessTokenSecretEnvVar != null) {
            cb.setOAuthAccessTokenSecret(accessTokenSecretEnvVar);
        }
        Configuration config = cb.build();

        Tweeter tweeter = new Tweeter(config);
        return tweeter.tweet(builder.build());
    }

}
