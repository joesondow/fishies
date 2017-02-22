package sondow.fishies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Logic for making a semi-random emoji aquarium string.
 *
 * @author @JoeSondow
 */
public class AquariumBuilder {

    List<String> fishTypes = Arrays.asList("🐟", "🐡", "🐠");
    List<String> rareSwimmers = Arrays.asList("🐙", "🐬", "🦑", "🦈");
    List<String> plants = Arrays.asList("🌱", "🌾", "🌿");
    List<String> rareBottomDwellers = Arrays.asList("🐌", "🏰", "🦀", "🐚", "⚓️", "☘️");
    List<String> exceedinglyRareJunk = Arrays.asList("🎱", "🎲", "🎮", "🗿", "🔱", "🎷", "🗽", "💎", "💰", "🔔", "💀",
            "💩");
    String ideographicSpace = "\u3000";
    String enSpace = "\u2002";
    String emSpace = "\u2003";
    String threePerEmSpace = "\u2004";
    String thinSpace = "\u2009";
    String hairSpace = "\u200a";

    // Custom randomizer wrapper class allows for deterministic unit tests.
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
            // System.out.println(result);
            runningTotal += result;
        }
        int mean = (int) Math.round((double) runningTotal / rolls);
        // System.out.println("runningTotal: " + runningTotal + ", rolls: " + rolls + ", mean: " + mean);
        return mean;
    }

    private int lowFavoringRandom(int upperBound) {
        int a = random.nextInt(upperBound);
        int b = random.nextInt(upperBound);
        return lower(a, b);
    }

    public String build() {
        List<String> fishes = new ArrayList<String>();
        int fishTypeCount = random.nextInt(fishTypes.size()) + 1;
        for (int i = 1; i <= fishTypeCount; i++) {
            fishes.add(random.oneOf(fishTypes));
        }

        // A rare swimmer should show up about once every 8 tweets.
        if (random.nextInt(8) == 5) {
            fishes.add(random.oneOf(rareSwimmers));
        }

        // 140 standard char budget = 280 bytes. Emojis are 4 bytes each.

        // There will be about 8 tweets a day. Something should be special about
        // many of them but not all of them. Only once a week should something
        // exceedingly rare show up. 8 tweets * 7 days = 56 tweets per week
        boolean exceedinglyRareBottomTime = (random.nextInt(56) == 37);

        // A rare bottom dweller should show up about once every 8 tweets.
        boolean rareBottomDwellerTime = (random.nextInt(8) == 2);

        int maxLineLength = 10;
        List<String> bottom = new ArrayList<String>();
        if (rareBottomDwellerTime) {
            bottom.add(random.oneOf(rareBottomDwellers));
        }
        if (exceedinglyRareBottomTime) {
            bottom.add(random.oneOf(exceedinglyRareJunk));
        }
        int plantCount = midFavoringRandom(maxLineLength - bottom.size() - 1);
        if (plantCount < 1) {
            plantCount = 1;
        }
        for (int i = 0; i < plantCount; i++) {
            bottom.add(random.oneOf(plants));
        }
        while (bottom.size() < maxLineLength) {
            bottom.add(ideographicSpace);
        }
        random.shuffle(bottom);
        String bottomLine = String.join("", bottom);

        // For each swimmer line, choose a random number of fish, then random small whitespace in front of some.
        int swimLineCount = 5;
        List<List<String>> swimLines = new ArrayList<List<String>>();
        int previousSwimmerCount = 0;
        for (int s = 0; s < swimLineCount; s++) {
            List<String> swimLine = new ArrayList<String>();

            // Lines should tend to have similar swimmer densities. How crowded in general is this aquarium?
            int maxPerLine = lowFavoringRandom((int) (maxLineLength * 0.8));
            if (maxPerLine < 1) {
                maxPerLine = 1;
            }
            int swimmerCount = midFavoringRandom(maxPerLine);

            // At least one swimmer on first line so first lines aren't trimmed.
            if (previousSwimmerCount == 0 && swimmerCount == 0) {
                swimmerCount++;
            }

            for (int i = 0; i < swimmerCount; i++) {
                swimLine.add(getSmallPersonalSpace() + random.oneOf(fishes));
            }
            while (swimLine.size() < maxLineLength) {
                swimLine.add(ideographicSpace);
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
            personalSpace = thinSpace;
        } else if (jitter == 2) {
            personalSpace = threePerEmSpace;
        } else if (jitter == 3) {
            personalSpace = enSpace;
        }
        return personalSpace;
    }
}
