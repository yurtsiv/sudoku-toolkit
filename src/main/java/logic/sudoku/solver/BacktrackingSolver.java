package logic.sudoku.solver;

import logic.sudoku.GameField;

public class BacktrackingSolver implements SudokuSolver {
    public boolean solve (GameField gameField) {
        if (!gameField.isValid()) {
            throw new IllegalArgumentException("Invalid game field provided");
        }

        int[] unassignedPosition = gameField.getFirstUnassignedPosition();
        if (unassignedPosition == null) {
            return true;
        }

        for (int i = 1; i <= gameField.getSize(); i++) {
            if (gameField.tryToSet(unassignedPosition[0], unassignedPosition[1], i)) {
                if (solve(gameField)) {
                    return true;
                }

                gameField.tryToSet(unassignedPosition[0], unassignedPosition[1], 0);
            }
        }

        return false;
    }
}
