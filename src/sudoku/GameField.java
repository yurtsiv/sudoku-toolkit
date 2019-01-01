package sudoku;

import arrayUtils.ArrayUtils;

import java.util.ArrayList;

public class GameField {
    private int[][] field = new int[9][9];

    /*
        Indicates which coordinates has each sub-grid.

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

    public int getSize () {
        return 9;
    }

    private void checkCoordsValidity (int rowNum, int columnNum) {
        if (rowNum < 0 || rowNum >= 9 || columnNum < 0 || columnNum >= 9) {
            throw new IllegalArgumentException("Incorrect coordinates provided");
        }
    }

    public boolean tryToSet (int rowNum, int columnNum, int value) {
        checkCoordsValidity(rowNum, columnNum);

        field[rowNum][columnNum] = value;

        if (isRowValid(rowNum) && isColumnValid(columnNum) && isSubGridValid(rowNum, columnNum)) {
            return true;
        }

        field[rowNum][columnNum] = 0;
        return false;
    }

    public int get (int rowNum, int columnNum) {
        return field[rowNum][columnNum];
    }


    private int[] getRow (int rowNum) { return field[rowNum]; }

    private boolean isRowValid (int rowNum) {
        return ArrayUtils.areAllElemsDistinct(getRow(rowNum), 0);
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

    private int[] getSubGrid (int rowNum, int columnNum) {
        checkCoordsValidity(rowNum, columnNum);

        int subGridNum = 0;
        for (int i = 0; i < subGridsMap.length; i++) {
            int[] subGridMap = subGridsMap[i];
            if (
                rowNum >= subGridMap[0] &&
                rowNum <= subGridMap[1] &&
                columnNum >= subGridMap[2] &&
                columnNum <= subGridMap[3]
            ) {
                subGridNum = i;
            }
        }

        return getSubGrid(subGridNum);
    }

    private int[] getSubGrid (int subGridNum) {
        int[] subGridMap = subGridsMap[subGridNum];
        return ArrayUtils.getSubset(field, subGridMap[0], subGridMap[1], subGridMap[2], subGridMap[3]);
    }

    private boolean isSubGridValid (int subGridNum) {
        return ArrayUtils.areAllElemsDistinct(getSubGrid(subGridNum), 0);
    }

    private boolean isSubGridValid (int rowNum, int columnNum) {
        return ArrayUtils.areAllElemsDistinct(getSubGrid(rowNum, columnNum), 0);
    }

    public boolean isValid () {
        for (int i = 0; i < field.length; i++) {
            if (!isColumnValid(i) || !isRowValid(i) || !isSubGridValid(i)) {
                return false;
            }
        }

        return true;
    }

    public boolean isSolved () {
        if (!isValid()) {
            return false;
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public int[] getFirstUnassignedPosition () {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == 0) {
                    return new int[] {i, j};
                }
            }
        }

        return null;
    }
}