package sondow.fishies;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AquariumBuilderTest {

    @Test
    public void testBuildSimpleAquarium() {
        Randomizer random = new TestRandomizer();
        AquariumBuilder builder = new AquariumBuilder(random);
        String aquarium = builder.build();
        assertEquals("      🦑🐡🦑\n      🐡🦑🐡\n      🦑🐡🦑\n      🐡🦑🐡\n      🦑🐡🦑\n   🌾🌿🌾🌿🌾🌿", aquarium);
    }
}
