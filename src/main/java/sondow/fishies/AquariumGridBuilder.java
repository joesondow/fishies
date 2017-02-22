package sondow.fishies;

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

    public String build() {
        int rowCount = 6;
        int colCount = 9;
        Grid grid = new Grid(rowCount, colCount, Chars.IDEOGRAPHIC_SPACE);

        // How many swimmers and bottom dwellers?
        int swimmerCount = random.nextInt(13) + 3; // 3-15
        int bottomDwellerCount = random.nextInt(9) + 1; // 1-10

        for (int s = 0; s < swimmerCount; s++) {
            // Pick a location for a swimmer.
            int rowIndex = random.nextInt(rowCount) - 1; // Not on bottom row
            int colIndex = random.nextInt(colCount);

            // Which swimmer?

        }

        return "";
    }
}
