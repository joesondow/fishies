package sondow.fishies;

import java.util.ArrayList;
import java.util.Collection;
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

    public <T> T oneOf(Collection<T> things) {
        List<T> list;
        if (things instanceof List) {
            list = (List<T>) things;
        } else {
            list = new ArrayList<T>(things);
        }
        return list.get(nextInt(list.size()));
    }

    public void shuffle(List<String> list) {
        Collections.shuffle(list);
    }
}
