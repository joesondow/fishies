package sondow.fishies;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

public class AquariumBuilderTest {

    @Test
    public void testBuildSimpleAquarium() {
        Randomizer random = new TestRandomizer();
        AquariumBuilder builder = new AquariumBuilder(random);
        String actual = builder.build();
        String expected = "　　　　　　　 🦑 🦑 🦑\n　　　　　　　 🦑 🦑 🦑\n　　　　　　　 🦑 🦑 🦑\n"
                + "　　　　　　　 🦑 🦑 🦑\n　　　　　　　 🦑 🦑 🦑\n" + "　　　🌿🌾🌿🌾🌿🌾🌿";
        assertEquals(expected, actual);
    }

    @Ignore("Only run in development, not in build.")
    @Test
    public void testLotsOfRandomOnesToSeeHowTheyLook() {
        for (int i = 0; i < 100; i++) {
            System.out.println("-------------------------");
            String aquarium = new AquariumBuilder().build();
            System.out.println(aquarium);
            int byteCount = aquarium.getBytes().length;
            assert (byteCount < 280);
            System.out.println("Bytes: " + byteCount);
        }
    }
}
