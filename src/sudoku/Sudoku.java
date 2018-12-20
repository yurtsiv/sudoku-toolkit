package sudoku;

public class Sudoku {
    int[][] sudoku;

    public Sudoku (int size) {
        sudoku = new int[size][size];
    }

    public void set (int x, int y, int value) {
        sudoku[x][y] = value;
    }

    public int get (int x, int y) {
        return sudoku[x][y];
    }

    private boolean isRowValid (int row) {
        return true;
    }

    public boolean isValid () {
        return true;
    }
}