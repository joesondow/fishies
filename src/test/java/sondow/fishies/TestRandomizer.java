package sondow.fishies;

import java.util.Collections;
import java.util.List;

/**
 * A deterministic mock Randomizer for unit tests.
 *
 * @author joesondow
 */
public class TestRandomizer extends Randomizer {

    boolean addOne = true;

    /**
     * Returns the next result that this mock randomizer is supposed to return for a given unit test.
     */
    @Override
    public int nextInt(int bound) {
        int result = (bound / 2) + (addOne ? 1 : 0);
        addOne = addOne ? false : true; // toggle
        return result;
    }

    /**
     * Mock implementation of shuffle just reverses the list, so it's altered but the order is deterministic.
     */
    @Override
    public void shuffle(List<String> list) {
        Collections.reverse(list);
    }

}
