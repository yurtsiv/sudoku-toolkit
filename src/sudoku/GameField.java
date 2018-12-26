package sudoku;

import arrayUtils.ArrayUtils;

public class GameField {
    private int[][] field = new int[9][9];

    /*
        Indicates which coordinates has each subGrid.

        subGridsMap[subGridNum] = { fromRow, toRow, fromColumn, toColumn }
     */
    private final int[][] subGridsMap = new int[][]{
        { 0, 2, 0, 2 },
        { 0, 2, 3, 5 },
        { 0, 2, 6, 8 },
        { 3, 5, 0, 2 },
        { 3, 5, 3, 5 },
        { 3, 5, 6, 8 },
        { 6, 8, 0, 2 },
        { 6, 8, 3, 5 },
        { 6, 8, 6, 8 }
    };

    public void set (int row, int column, int value) {
        field[row][column] = value;
    }

    public int get (int row, int column) {
        return field[row][column];
    }

    private boolean isRowValid (int rowNum) {
        return ArrayUtils.areAllElemsDistinct(field[rowNum], 0);
    }

    private int[] getColumn (int columnNum) {
        int[] columnArr = new int[field.length];

        for (int i = 0; i < field.length; i++) {
            columnArr[i] = field[i][columnNum];
        }

        return columnArr;
    }

    private boolean isColumnValid (int columnNum) {
        return ArrayUtils.areAllElemsDistinct(getColumn(columnNum), 0);
    }

    private int[] getSubGrid (int subGridNum) {
        int[] subGridMap = subGridsMap[subGridNum];
        return ArrayUtils.getSubset(field, subGridMap[0], subGridMap[1], subGridMap[2], subGridMap[3]);
    }

    private boolean isSubGridValid (int subGridNum) {
        return ArrayUtils.areAllElemsDistinct(getSubGrid(subGridNum), 0);
    }

    public boolean isValid () {
        for (int i = 0; i < field.length; i++) {
            if (!isColumnValid(i) || !isRowValid(i) || !isSubGridValid(i)) {
                return false;
            }
        }

        return true;
    }
}