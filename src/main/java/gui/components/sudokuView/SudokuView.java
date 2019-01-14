package gui.components.sudokuView;

import javafx.css.PseudoClass;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.sudoku.GameField;

public class SudokuView {
    public static GridPane create(GameField gameField) {
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
                GridPane.setValignment(textNode, VPos.CENTER);
                GridPane.setHalignment(textNode, HPos.CENTER);
                cell.getChildren().add(textNode);
                grid.getChildren().add(cell);
            }
        }

        return grid;
    }
}
