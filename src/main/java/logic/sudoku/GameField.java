package logic.sudoku;

import logic.utils.ArrayUtils;

public class GameField {
    private int[][] field = new int[9][9];

    /*
        Indicates which coordinates has each sub-grid.

        subGridsMap[subGridNum] = { fromRow, toRow, fromColumn, toColumn }
     */
    private final static int[][] subGridsMap = new int[][]{
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

    public int[][] getSubgridsMap() {
        return subGridsMap;
    }

    private void checkCoordsValidity (int rowNum, int columnNum) {
        if (rowNum < 0 || rowNum >= 9 || columnNum < 0 || columnNum >= 9) {
            throw new IllegalArgumentException("Incorrect coordinates provided");
        }
    }

    public void set (int rowNum, int columnNum, int value) {
        checkCoordsValidity(rowNum, columnNum);

        field[rowNum][columnNum] = value;
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

    public GameField cloneField() {
        GameField cloned = new GameField();
        for (int i = 0; i < field.length; i++) {
           for (int j = 0; j < field[0].length; j++) {
               cloned.set(i, j, field[i][j]);
           }
        }

        return cloned;
    }

    public void print () {
        for (int[] row : field) {
            System.out.println();
            for (int cell : row) {
                System.out.print(cell + " | ");
            }
        }

        System.out.println();
    }

    public void switchRows(int row1, int row2) {
        checkCoordsValidity(row1, row2);
        if (row1 == row2) {
            return;
        }

        int[] tempRow1 = field[row1];
        field[row1] = field[row2];
        field[row2] = tempRow1;
    }

    public void switchColumns(int column1, int column2) {
        checkCoordsValidity(column1, column2);
        if (column1 == column2) {
            return;
        }

        for (int[] row : field) {
            int tempColumn1 = row[column1];
            row[column1] = row[column2];
            row[column2] = tempColumn1;
        }
    }
}