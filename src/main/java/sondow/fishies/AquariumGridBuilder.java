package sondow.fishies;

import java.util.HashSet;
import java.util.Set;

/**
 * Algorithm for placing things in an aquarium based on a coordinate system grid.
 *
 * @author @JoeSondow
 */
public class AquariumGridBuilder {

    /**
     * Custom randomizer wrapper class allows for deterministic unit tests.
     */
    Randomizer random;

    AquariumGridBuilder() {
        random = new Randomizer();
    }

    public static final int ROW_COUNT = 6;
    public static final int COL_COUNT = 9;

    public static void main(String[] args) {
        AquariumGridBuilder builder = new AquariumGridBuilder();
        System.out.println(builder.build());
    }

    public String build() {
        Grid grid = new Grid(ROW_COUNT, COL_COUNT, Chars.IDEOGRAPHIC_SPACE);

        // How many swimmers and bottom dwellers?
        int swimmerCount = random.nextInt(13) + 3; // 3-15
        int plantCount = random.nextInt(7) + 2; // 2-8

        Types types = new Types(random);
        types.chooseTypes();

        // Second and third swimmers must go in row 1,3 or 2,4, or 2,3, to ensure no consecutive blank lines
        // that Twitter would remove. To choose which valid arrangement to use, pick one of the three.
        // 0 = 1,3
        // 1 = 2,4
        // 2 = 2,3
        int arrangementChoice = random.nextInt(3);

        for (int s = 0; s < swimmerCount; s++) {

            // Pick a location for a swimmer.
            int row = -1;
            int col = -1;

            // First swimmer always goes in first column of row 0, to avoid truncating by Twitter.
            if (s == 0) {
                row = 0;
                col = 0;
            } else if (s == 1) {
                if (arrangementChoice == 0) {
                    row = 1;
                } else {
                    row = 2;
                }
            } else if (s == 2) {
                if (arrangementChoice == 1) {
                    row = 4;
                } else {
                    row = 3;
                }
            }
            if (row <= -1) {
                row = random.nextInt(ROW_COUNT - 1); // Not on bottom row
            }
            if (col <= -1) {
                col = random.nextInt(COL_COUNT);
            }

            // Which swimmer?
            String swimmerType = random.oneOf(types.fishTypes);
            grid.put(row, col, Chars.getSmallPersonalSpace(random) + swimmerType);
        }

        // System.out.println("plant count: " + plantCount);
        Set<Integer> plantColumns = new HashSet<Integer>();
        for (int b = 0; b < plantCount; b++) {
            while (plantColumns.size() <= b) {
                Integer col = random.nextInt(COL_COUNT);
                if (!plantColumns.contains(col)) {
                    plantColumns.add(col);
                }
            }
        }
        for (Integer plantCol : plantColumns) {
            String plant = random.oneOf(Chars.PLANT_TYPES);
            grid.put(ROW_COUNT - 1, plantCol, plant);
        }

        // Which columns have no plants? Put other junk there.
        Set<Integer> emptyColumns = new HashSet<Integer>();
        for (int c = 0; c < COL_COUNT; c++) {
            if (!plantColumns.contains(c)) {
                emptyColumns.add(c);
            }
        }
        if (types.exceedinglyRareBottomDwellerTypes.size() >= 1) {
            String exceedinglyRareThing = types.exceedinglyRareBottomDwellerTypes.get(0);
            // Pick a column that doesn't have a plant, if possible.
            if (emptyColumns.size() >= 1) {
                Integer emptyColumn = random.oneOf(emptyColumns);
                grid.put(ROW_COUNT - 1, emptyColumn, exceedinglyRareThing);
                emptyColumns.remove(emptyColumn);
            }
        }
        if (types.rareBottomDwellerTypes.size() >= 1) {
            String rareBottomDweller = types.rareBottomDwellerTypes.get(0);
            if (emptyColumns.size() >= 1) {
                Integer emptyColumn = random.oneOf(emptyColumns);
                grid.put(ROW_COUNT - 1, emptyColumn, rareBottomDweller);
                emptyColumns.remove(emptyColumn);
            }
        }

        return grid.toString();
    }
}
