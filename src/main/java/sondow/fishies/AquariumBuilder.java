package sondow.fishies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AquariumBuilder {

    List<String> fishTypes = Arrays.asList("🐟", "🐡", "🐠");
    List<String> rareSwimmers = Arrays.asList("🐙", "🐬", "🦑", "🦈");
    List<String> plants = Arrays.asList("🌱", "🌾", "🌿");
    List<String> rareBottomDwellers = Arrays.asList("🐌", "🏰", "🦀", "🐚", "⚓️", "☘️");
    List<String> exceedinglyRareJunk = Arrays.asList("🎱", "🎲", "🎮", "🗿", "🔱", "🎷", "🗽", "💎", "💰", "🔔", "💀",
            "💩");
    String emSpace = "\u2003";

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

        // 140 char budget

        // There will be about 8 tweets a day. Something should be special about
        // many of them but not all of them. Only once a week should something
        // exceedingly rare show up. 8 tweets * 7 days = 56 tweets per week
        boolean exceedinglyRareBottomTime = (random.nextInt(56) == 37);

        // A rare bottom dweller should show up about once every 10 tweets.
        boolean rareBottomDwellerTime = (random.nextInt(10) == 2);

        // Bottom line character count should not exceed 9 characters
        int maxLineLength = 9;
        List<String> bottom = new ArrayList<String>();
        if (rareBottomDwellerTime) {
            bottom.add(random.oneOf(rareBottomDwellers));
        }
        if (exceedinglyRareBottomTime) {
            bottom.add(random.oneOf(exceedinglyRareJunk));
        }
        int plantCount = lowFavoringRandom(maxLineLength - bottom.size()) + 1;
        for (int i = 0; i < plantCount; i++) {
            bottom.add(random.oneOf(plants));
        }
        while (bottom.size() < maxLineLength) {
            bottom.add(emSpace);
        }
        random.shuffle(bottom);
        String bottomLine = String.join("", bottom);

        // For each swimmer line, choose a random number of fish, then a random
        // number
        // of leading spaces for each fish.
        List<List<String>> swimLines = new ArrayList<List<String>>();
        // boolean buildingSwimLines = true
        for (int s = 0; s < 5; s++) {
            List<String> swimLine = new ArrayList<String>();
            int swimmerCount = lowFavoringRandom(6);
            for (int i = 0; i < swimmerCount; i++) {
                swimLine.add(random.oneOf(fishes));
            }
            while (swimLine.size() < maxLineLength) {
                swimLine.add(emSpace);
            }
            random.shuffle(swimLine);
            swimLines.add(swimLine);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < swimLines.size(); i++) {
            List<String> swimLine = swimLines.get(i);
            stringBuilder.append(String.join("", swimLine)).append("\n");
        }
        stringBuilder.append(bottomLine);

        return stringBuilder.toString();
    }
}
