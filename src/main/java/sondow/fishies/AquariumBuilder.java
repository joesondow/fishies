package sondow.fishies;

import java.util.ArrayList;
import java.util.List;

/**
 * Logic for making a semi-random emoji aquarium string.
 *
 * @author @JoeSondow
 */
public class AquariumBuilder {

    /**
     * Custom randomizer wrapper class allows for deterministic unit tests.
     */
    Randomizer random;

    AquariumBuilder() {
        random = new Randomizer();
    }

    AquariumBuilder(Randomizer randomizer) {
        random = randomizer;
    }

    private int lower(int a, int b) {
        return (a < b) ? a : b;
    }

    /**
     * Rolls five dice and averages the results.
     *
     * @param upperBound one greater than the highest possible value
     * @return a random number
     */
    int midFavoringRandom(int upperBound) {
        int runningTotal = 0;
        int rolls = 3;
        for (int i = 0; i < rolls; i++) {
            int result = random.nextInt(upperBound);
            runningTotal += result;
        }
        int mean = (int) Math.round((double) runningTotal / rolls);
        return mean;
    }

    int lowFavoringRandom(int upperBound) {
        int a = random.nextInt(upperBound);
        int b = random.nextInt(upperBound);
        return lower(a, b);
    }

    /**
     * Builds a semi-random new aquarium string.
     */
    public String build() {

        Types types = new Types(random);
        types.chooseTypes();

        List<String> fishes = types.fishTypes;

        // // There will be about 8 tweets a day. Something should be special about
        // // many of them but not all of them. Only once a week should something
        // // exceedingly rare show up. 8 tweets * 7 days = 56 tweets per week
        // boolean exceedinglyRareBottomTime = (random.nextInt(56) == 37);
        //
        // // A rare bottom dweller should show up about once every 8 tweets.
        // boolean rareBottomDwellerTime = (random.nextInt(8) == 2);

        int maxLineLength = 10;
        List<String> bottom = new ArrayList<String>();
        if (types.rareBottomDwellerTypes.size() >= 1) {
            bottom.add(types.rareBottomDwellerTypes.get(0));
        }
        if (types.exceedinglyRareBottomDwellerTypes.size() >= 1) {
            bottom.add(types.exceedinglyRareBottomDwellerTypes.get(0));

        }
        int plantCount = midFavoringRandom(maxLineLength - bottom.size() - 1);
        if (plantCount < 1) {
            plantCount = 1;
        }
        for (int i = 0; i < plantCount; i++) {
            bottom.add(random.oneOf(Chars.PLANT_TYPES));
        }
        while (bottom.size() < maxLineLength) {
            bottom.add(Chars.IDEOGRAPHIC_SPACE);
        }
        random.shuffle(bottom);
        String bottomLine = String.join("", bottom);

        // For each swimmer line, choose a random number of fish, then random small whitespace
        // in front of some.
        int swimLineCount = 5;
        List<List<String>> swimLines = new ArrayList<List<String>>();
        int previousSwimmerCount = 0;
        for (int s = 0; s < swimLineCount; s++) {
            List<String> swimLine = new ArrayList<String>();

            // Lines should tend to have similar swimmer densities. How crowded in general is
            // this aquarium?
            int maxPerLine = random.nextInt((int) Math.round((maxLineLength * 0.6))) + 1;
            int swimmerCount = midFavoringRandom(maxPerLine);

            // At least one swimmer on first line so first lines aren't trimmed.
            if (previousSwimmerCount == 0 && swimmerCount == 0) {
                swimmerCount++;
            }

            for (int i = 0; i < swimmerCount; i++) {
                swimLine.add(getSmallPersonalSpace() + random.oneOf(fishes));
            }
            while (swimLine.size() < maxLineLength) {
                swimLine.add(Chars.IDEOGRAPHIC_SPACE);
            }
            random.shuffle(swimLine);
            swimLines.add(swimLine);
            previousSwimmerCount = swimmerCount;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < swimLines.size(); i++) {
            List<String> swimLine = swimLines.get(i);
            stringBuilder.append(String.join("", swimLine)).append("\n");
        }
        stringBuilder.append(bottomLine);

        return stringBuilder.toString();
    }

    private String getSmallPersonalSpace() {
        int jitter = random.nextInt(4);
        String personalSpace = "";
        if (jitter == 1) {
            personalSpace = Chars.THIN_SPACE;
        } else if (jitter == 2) {
            personalSpace = Chars.THREE_PER_EM_SPACE;
        } else if (jitter == 3) {
            personalSpace = Chars.EN_SPACE;
        }
        return personalSpace;
    }
}
