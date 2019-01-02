package main;

import sudoku.*;
import sudoku.solver.Solver;

public class Main {

    public static void main(String[] args) {
	    GameField sudoku = new GameField();
        sudoku.set(0, 2, 6);
        sudoku.set(0, 3, 8);
        sudoku.set(0, 7, 9);
        sudoku.set(0, 8, 4);

        sudoku.set(1, 1, 2);
        sudoku.set(1, 4, 6);
        sudoku.set(1, 6, 7);

        sudoku.set(2, 0, 7);
        sudoku.set(2, 3, 4);
        sudoku.set(2, 5, 2);

        sudoku.set(3, 7, 1);

        sudoku.set(4, 0, 6);
        sudoku.set(4, 1, 4);
        sudoku.set(4, 4, 2);
        sudoku.set(4, 5, 8);
        sudoku.set(4, 6, 3);
        sudoku.set(4, 7, 5);

        sudoku.set(5, 1, 9);
        sudoku.set(5, 3, 5);
        sudoku.set(5, 5, 1);
        sudoku.set(5, 8, 2);

        sudoku.set(6, 0, 4);
        sudoku.set(6, 2, 2);
        sudoku.set(6, 3, 6);
        sudoku.set(6, 5, 3);
        sudoku.set(6, 8, 5);

        sudoku.set(7, 4, 1);
        sudoku.set(7, 8, 3);

        sudoku.set(8, 0, 8);
        sudoku.set(8, 2, 9);
        sudoku.set(8, 6, 1);
        sudoku.set(8, 7, 2);
        sudoku.print();

        Solver solver = new Solver();
        solver.solve(sudoku);


        System.out.println("SOLVED");
        sudoku.print();
    }
}
