package logic.sudoku.solver;

import logic.sudoku.GameField;

public class SudokuSolver {
    private static boolean solveRecursively(GameField gameField) {
        int[] unassignedPosition = gameField.getFirstUnassignedPosition();
        if (unassignedPosition == null) {
            return true;
        }

        for (int i = 1; i <= gameField.getSize(); i++) {
            if (gameField.tryToSet(unassignedPosition[0], unassignedPosition[1], i)) {
                if (SudokuSolver.solveRecursively(gameField)) {
                    return true;
                }

                gameField.tryToSet(unassignedPosition[0], unassignedPosition[1], 0);
            }
        }

        return false;
    }

    public GameField solve (GameField gameField) {
        if (!gameField.isValid()) {
            throw new IllegalArgumentException("Invalid game field provided");
        }

        GameField clonedField = gameField.cloneField();
        SudokuSolver.solveRecursively(clonedField);
        return clonedField;
    }
}
