package sondow.fishies;

/**
 * Data structure for holding a grid of strings, mostly for emoji and whitespace.
 *
 * @author @JoeSondow
 */
public class Grid {

    private String[][] table;

    /**
     * Creates a grid of cells based on the specified dimensions, with a starting value in every cell.
     *
     * @param rows the number of rows the grid should have
     * @param cols the number of columns the grid should have
     * @param init the initial value to put in every cell
     */
    Grid(int rows, int cols, String init) {
        table = new String[rows][cols];
        for (int r = 0; r < table.length; r++) {
            String[] row = table[r];
            for (int c = 0; c < row.length; c++) {
                table[r][c] = init;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < table.length; r++) {
            String[] row = table[r];
            for (int c = 0; c < row.length; c++) {
                builder.append(row[c]);
            }
            if (r < table.length - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Puts a string in the grid at specific coordinates.
     *
     * @param row the zero-indexed row number
     * @param col the zero-indexed column number
     * @param value the string to put in the grid
     */
    public void put(int row, int col, String value) {
        table[row][col] = value;
    }

    /**
     * Gets a value from the grid at specific coordinates.
     *
     * @param row the zero-indexed row number
     * @param col the zero-indexed column number
     * @return the value stored at the specific grid cell
     */
    public String get(int row, int col) {
        return table[row][col];
    }
}
