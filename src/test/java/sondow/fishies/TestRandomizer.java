package sondow.fishies;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A deterministic mock Randomizer for unit tests.
 * 
 * @author joesondow
 */
public class TestRandomizer extends Randomizer {

	Queue<Integer> results = new LinkedList<Integer>();
	
	public TestRandomizer(Iterable<Integer> values) {
		for (Integer i : values) {
			results.add(i);
		}
	}
	
	/**
	 * Returns the next result that this mock randomizer is supposed to return for a given
	 * unit test.
	 */
	@Override
	public int nextInt(int bound) {
		return results.poll();
	}

	/**
	 * Mock implementation of shuffle just reverses the list, so it's altered but the order
	 * is deterministic.
	 */
	@Override
	public void shuffle(List<String> list) {
		Collections.reverse(list);
	}

}
