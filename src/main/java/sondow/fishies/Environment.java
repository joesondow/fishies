package sondow.fishies;

/**
 * AWS has an encryption bug right now in their free tier services right now. See
 * https://twitter.com/JoeSondow/status/856638770182881280
 *
 *
 * The main reason I would like to encrypt some secret values set in environment variables so that I can live
 * stream some coding and show the web page where I'm doing the deployment without accidentally broadcasting the
 * secret values in the environment variables. Since the AWS lambda page only shows about the first 20 characters
 * of each env var, this particular secrecy problem can be solved by prepending space-filling characters onto
 * each value at deploy time, and then stripping those characters at runtime if the characters are present. In
 *
 * @author @JoeSondow
 */
public class Environment {

    public static final String SPACE_FILLER = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String get(String key) {
        String rawValue = System.getenv(key);
        String value;
        if (rawValue != null && rawValue.startsWith(SPACE_FILLER)) {
            value = rawValue.substring(SPACE_FILLER.length());
        } else {
            value = rawValue;
        }

        return value;
    }
}
