package logic.sudoku.generator;

import logic.utils.RandomNums;

public class SudokuMasks {
    private static int[][][] easyMasks = new int[][][]{
        {
            { 0, 2 }, { 0, 4 }, { 0, 5 },
            { 1, 0 }, { 1, 1 }, { 1, 4 }, { 1, 6 }, { 1, 7 }, { 1, 8 },
            { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 6 }, { 2, 8 },
            { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 8 },
            { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 4, 8 },
            { 5, 0 }, { 5, 4 }, { 5, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 },
            { 6, 0 }, { 6, 2 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 },
            { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 4 }, { 7, 7 }, { 7, 8 },
            { 8, 3 }, { 8, 4 }, { 8, 6 },
        },
    };

    private static int[][][] mediumMasks = new int[][][]{
        {
            { 0, 2 }, { 0, 4 }, { 0, 5 },
            { 1, 0 }, { 1, 1 }, { 1, 4 }, { 1, 6 }, { 1, 7 }, { 1, 8 },
            { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 6 }, { 2, 8 },
            { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 8 },
            { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 4, 8 },
            { 5, 0 }, { 5, 4 }, { 5, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 },
            { 6, 0 }, { 6, 2 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 },
            { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 4 }, { 7, 7 }, { 7, 8 },
            { 8, 3 }, { 8, 4 }, { 8, 6 },
        },
    };

    private static int[][][] hardMasks = new int[][][]{
        {
            { 0, 2 }, { 0, 4 }, { 0, 5 },
            { 1, 0 }, { 1, 1 }, { 1, 4 }, { 1, 6 }, { 1, 7 }, { 1, 8 },
            { 2, 1 }, { 2, 2 }, { 2, 3 }, { 2, 4 }, { 2, 6 }, { 2, 8 },
            { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 3, 4 }, { 3, 8 },
            { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 4, 8 },
            { 5, 0 }, { 5, 4 }, { 5, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 },
            { 6, 0 }, { 6, 2 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 6, 7 },
            { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 4 }, { 7, 7 }, { 7, 8 },
            { 8, 3 }, { 8, 4 }, { 8, 6 },
        },
    };

    private static int shiftCoord(int coord, int shift) {
        if ((coord + shift) > 8) {
            return shift - (8 - coord) - 1;
        }

        return coord + shift;
    }

    private static int[][] shiftMask(int[][] initialMask) {
        int[][] shiftedMask = new int[initialMask.length][2];

        int horizontalShift = RandomNums.randomInt(1, 8);
        int verticalShift = RandomNums.randomInt(1, 8);

        for (int i = 0; i < shiftedMask.length; i++) {
            shiftedMask[i][0] = SudokuMasks.shiftCoord(initialMask[i][0], verticalShift);
            shiftedMask[i][1] = SudokuMasks.shiftCoord(initialMask[i][1], horizontalShift);
        }

        return shiftedMask;
    }

    private static int[][] getRandomMask(int[][][] masks) {
       int index = RandomNums.randomInt(0, masks.length - 1);
       return masks[index];
    }

    public static int[][] getMask(SudokuDifficulty difficulty) {
        int[][] randomMask = null;
        switch (difficulty) {
            case EASY:
               randomMask = SudokuMasks.getRandomMask(easyMasks);
               break;

            case MEDIUM:
                randomMask = SudokuMasks.getRandomMask(mediumMasks);
                break;

            case HARD:
                randomMask = SudokuMasks.getRandomMask(hardMasks);
                break;
        }


        return shiftMask(randomMask);
    }
}
