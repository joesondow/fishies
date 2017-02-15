package sondow.fishies;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Custom randomizer wrapper class allows for deterministic unit tests.
 * 
 * @author joesondow
 */
public class Randomizer {
	
	Random random = new Random();
	
	public int nextInt(int bound) {
		return random.nextInt(bound);
	}
	
	public <T> T oneOf(List<T> things) {
		return things.get(nextInt(things.size()));
	}

	public void shuffle(List<String> list) {
		Collections.shuffle(list);
	}
}
