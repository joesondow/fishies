package sondow.fishies;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AquariumBuilderTest {

    @Test
    public void testBuildSimpleAquarium() {
        Randomizer random = new TestRandomizer(TestRandomizer.Setting.QUARTER_MAX_HALF_MIN);
        AquariumBuilder builder = new AquariumBuilder(random);
        String actual = builder.build();
        String expected = "　　　　　　　　　🐠\n" + "　　　　　　　　　 🐠\n" + "　　　　　　　　　　\n" + "　　　　　　　　　🐠\n" + "　　　　　　　　　 🐠\n"
                + "　　　　　　　🌾🌿☘️";
        assertEquals(expected, actual);
    }

    @Test
    public void testMidFavoringRandom() {
        Randomizer random = new TestRandomizer(TestRandomizer.Setting.QUARTER_MAX_HALF_MIN);
        AquariumBuilder builder = new AquariumBuilder(random);
        assertEquals(5, builder.midFavoringRandom(10));
        assertEquals(4, builder.midFavoringRandom(10));
        assertEquals(2, builder.midFavoringRandom(10));
        assertEquals(4, builder.midFavoringRandom(9));
        assertEquals(5, builder.midFavoringRandom(9));
        assertEquals(3, builder.midFavoringRandom(9));
        assertEquals(2, builder.midFavoringRandom(8));
        assertEquals(4, builder.midFavoringRandom(8));
        assertEquals(4, builder.midFavoringRandom(8));
        assertEquals(3, builder.midFavoringRandom(8));
        assertEquals(1, builder.midFavoringRandom(7));
        assertEquals(3, builder.midFavoringRandom(7));
        assertEquals(3, builder.midFavoringRandom(7));
        assertEquals(2, builder.midFavoringRandom(6));
        assertEquals(1, builder.midFavoringRandom(6));
        assertEquals(3, builder.midFavoringRandom(6));
        assertEquals(2, builder.midFavoringRandom(5));
        assertEquals(2, builder.midFavoringRandom(5));
        assertEquals(1, builder.midFavoringRandom(5));
        assertEquals(2, builder.midFavoringRandom(4));
        assertEquals(2, builder.midFavoringRandom(4));
        assertEquals(1, builder.midFavoringRandom(4));
        assertEquals(0, builder.midFavoringRandom(3));
        assertEquals(1, builder.midFavoringRandom(3));
        assertEquals(1, builder.midFavoringRandom(3));
        assertEquals(0, builder.midFavoringRandom(2));
        assertEquals(0, builder.midFavoringRandom(2));
        assertEquals(1, builder.midFavoringRandom(2));
        assertEquals(0, builder.midFavoringRandom(1));
        assertEquals(0, builder.midFavoringRandom(1));
        assertEquals(0, builder.midFavoringRandom(1));
    }

    @Test()
    public void testLotsOfRandomOnesToSeeHowTheyLook() {
        for (int i = 0; i < 100; i++) {
            System.out.println("-------------------------");
            String aquarium = new AquariumBuilder().build();
            System.out.println(aquarium);
            int length = aquarium.length();
            assert (length < 140);
            System.out.println("length: " + length);
        }
    }
}
