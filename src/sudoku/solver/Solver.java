package sudoku.solver;

import sudoku.GameField;

public class Solver {
    private SudokuSolver solver;

    public Solver () {
        this.solver = new BacktrackingSolver();
    }

    public void solve (GameField gameField) {
        solver.solve(gameField);
    }
}
