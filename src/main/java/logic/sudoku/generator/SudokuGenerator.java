package logic.sudoku.generator;

import logic.sudoku.GameField;
import logic.sudoku.solver.SudokuSolver;
import logic.utils.RandomNums;


public class SudokuGenerator {
    private static void makePermutations(GameField gameField) {
        int[][] subGridsMap = gameField.getSubgridsMap();

        for (int[] subGridMap : subGridsMap) {
            int[] rowsToSwitch = RandomNums.getIntPair(subGridMap[0], subGridMap[1]);
            int[] columnsToSwitch = RandomNums.getIntPair(subGridMap[2], subGridMap[3]);

            gameField.switchRows(rowsToSwitch[0], rowsToSwitch[1]);
            gameField.switchColumns(columnsToSwitch[0], columnsToSwitch[1]);
        }
    }

    private static void unsetFields(GameField gameField, int[][] mask) {
       for (int[] coord : mask) {
           gameField.set(coord[0], coord[1], 0);
       }
    }

    public static GameField generate(SudokuDifficulty difficulty) {
        GameField result = SudokuSolver.solve(new GameField());
        SudokuGenerator.makePermutations(result);
        SudokuGenerator.unsetFields(result, SudokuMasks.getMask(difficulty));
        result.print();
        return result;
    }
}
