package gui.screens;

import gui.components.SudokuInputFields.SudokuInputFields;
import javafx.scene.layout.Pane;
import logic.sudoku.GameField;

public class SolveScreen {
    public static Pane create() {
        GameField gameField = new GameField();
        return new SudokuInputFields(gameField.getSize()).create();
    }
}
