package main;

import sudoku.*;
import sudoku.solver.Solver;

public class Main {

    public static void main(String[] args) {
	    GameField sudoku = new GameField();

        Solver solver = new Solver();
        solver.solve(sudoku);

        System.out.println();
    }
}
