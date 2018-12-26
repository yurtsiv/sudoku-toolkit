package sudoku.solver;

import sudoku.GameField;

public class BacktrackingSolver implements SudokuSolver {
    public void solve (GameField gameField) {
        if (!gameField.isValid()) {
            throw new IllegalArgumentException("Invalid game field provided");
        }
    }
}
