package gui.components.sudokuView;

import gui.components.ComponentInterface;
import javafx.css.PseudoClass;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import logic.sudoku.GameField;

public class SudokuView implements ComponentInterface {
    private GameField gameField;

    public SudokuView(GameField gameField) {
        this.gameField = gameField;
    }

    public Pane create() {
        GridPane grid = new GridPane();
        int size = gameField.getSize();

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                StackPane cell = new StackPane();
                GridPane.setConstraints(cell, column, row);
                cell.getStyleClass().add("field-cell");
                cell.pseudoClassStateChanged(right, column == 2 || column == 5);
                cell.pseudoClassStateChanged(bottom, row == 2  || row == 5);

                String text = Integer.toString(gameField.get(row, column));
                Text textNode = new Text(text);
                cell.getChildren().add(textNode);
                grid.getChildren().add(cell);
            }
        }

        return grid;
    }
}
