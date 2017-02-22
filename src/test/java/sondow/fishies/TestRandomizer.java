package sondow.fishies;

import java.util.Collections;
import java.util.List;

/**
 * A deterministic mock Randomizer for unit tests.
 *
 * @author joesondow
 */
public class TestRandomizer extends Randomizer {

    enum Setting {
        MIN, MAX, HALF, HALF_OR_HALF_PLUS_ONE, QUARTER_MAX_HALF_MIN
    }

    enum Phase {
        ALPHA, BETA, GAMMA, DELTA
    }

    private Setting setting;
    private Phase phase = Phase.ALPHA;

    TestRandomizer(Setting setting) {
        this.setting = setting;
    }

    /**
     * Returns the next result that this mock randomizer is supposed to return for a given unit test.
     */
    @Override
    public int nextInt(int bound) {
        assert (bound >= 1);
        if (setting == Setting.HALF_OR_HALF_PLUS_ONE) {
            int result = (bound / 2) + (phase == Phase.ALPHA ? 1 : 0);
            if (phase == Phase.ALPHA) {
                phase = Phase.BETA;
            } else if (phase == Phase.BETA) {
                phase = Phase.ALPHA;
            }
            return result;
        } else if (setting == Setting.MIN) {
            return 0;
        } else if (setting == Setting.MAX) {
            return bound - 1;
        } else if (setting == Setting.HALF) {
            return bound / 2;
        } else if (setting == Setting.QUARTER_MAX_HALF_MIN) {
            if (phase == Phase.ALPHA) {
                phase = Phase.BETA;
                return (int) (bound * 0.25);
            } else if (phase == Phase.BETA) {
                phase = Phase.GAMMA;
                return bound - 1;
            } else if (phase == Phase.GAMMA) {
                phase = Phase.DELTA;
                return bound / 2;
            } else if (phase == Phase.DELTA) {
                phase = Phase.ALPHA;
                return 0;
            }
        }
        throw new RuntimeException(
                "TestRandomizer problem. Setting: " + setting + ", bound: " + bound + ", phase: " + phase);
    }

    /**
     * Mock implementation of shuffle just reverses the list, so it's altered but the order is deterministic.
     */
    @Override
    public void shuffle(List<String> list) {
        Collections.reverse(list);
    }

}
