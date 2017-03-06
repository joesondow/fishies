package sondow.fishies;

import java.util.ArrayList;
import java.util.List;

/**
 * Chooses what types of swimmers and bottom dwellers will be in an aquarium.
 *
 * @author @JoeSondow
 */
public class Types {

    Randomizer random;

    List<String> fishTypes = new ArrayList<String>();
    // List<String> rareSwimmerTypes = new ArrayList<String>();
    // List<String> plantTypes = new ArrayList<String>();
    List<String> rareBottomDwellerTypes = new ArrayList<String>();
    List<String> exceedinglyRareBottomDwellerTypes = new ArrayList<String>();

    public Types(Randomizer random) {
        this.random = random;
    }

    public void chooseTypes() {
        int fishTypeCount = random.nextInt(Chars.FISH_TYPES.size()) + 1;
        if (fishTypeCount == Chars.FISH_TYPES.size()) {
            fishTypes.addAll(Chars.FISH_TYPES);
        } else {
            while (fishTypes.size() < fishTypeCount) {
                String fishType = random.oneOf(Chars.FISH_TYPES);
                if (!fishTypes.contains(fishType)) {
                    fishTypes.add(fishType);
                }
            }
        }

        // A rare swimmer should show up about once every 8 tweets.
        if (random.nextInt(8) == 5) {
            fishTypes.add(random.oneOf(Chars.RARE_SWIMMER_TYPES));
        }

        // There will be about 8 tweets a day. Something should be special about
        // many of them but not all of them. Only once a week should something
        // exceedingly rare show up. 8 tweets * 7 days = 56 tweets per week
        boolean exceedinglyRareBottomTime = (random.nextInt(56) == 37);
        if (exceedinglyRareBottomTime) {
            exceedinglyRareBottomDwellerTypes.add(random.oneOf(Chars.EXCEEDINGLY_RARE_JUNK));
        }

        // A rare bottom dweller should show up about once every 8 tweets.
        boolean rareBottomDwellerTime = (random.nextInt(8) == 2);
        if (rareBottomDwellerTime) {
            rareBottomDwellerTypes.add(random.oneOf(Chars.RARE_BOTTOM_DWELLERS));
        }

    }
}
