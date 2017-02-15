/**
 * 
 */
package sondow.fishies;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * @author joesondow
 *
 */
public class LambdaRequestHandler implements RequestHandler<Object, Object> {

	/* (non-Javadoc)
	 * @see com.amazonaws.services.lambda.runtime.RequestHandler#handleRequest(java.lang.Object, com.amazonaws.services.lambda.runtime.Context)
	 */
	public Object handleRequest(Object input, Context context) {
		AquariumBuilder builder = new AquariumBuilder();
		Tweeter tweeter = new Tweeter();
		return tweeter.tweet(builder.build());
	}

}
