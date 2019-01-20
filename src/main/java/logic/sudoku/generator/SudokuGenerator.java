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

    public static GameField generate(SudokuDifficulty difficulty) {
        GameField result = SudokuSolver.solve(new GameField());
        SudokuGenerator.makePermutations(result);
        return result;
    }
}
