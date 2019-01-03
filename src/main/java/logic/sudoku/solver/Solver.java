package logic.sudoku.solver;

import logic.sudoku.GameField;

public class Solver {
    private SudokuSolver solver;

    public Solver () {
        this.solver = new BacktrackingSolver();
    }

    public void solve (GameField gameField) {
        solver.solve(gameField);
    }
}
