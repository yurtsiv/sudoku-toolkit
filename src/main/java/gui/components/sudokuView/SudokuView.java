package gui.components.sudokuView;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.sudoku.GameField;

public class SudokuView {
    public static GridPane create(GameField gameField) {
        GridPane grid = new GridPane();
        int size = gameField.getSize();

        for (int row = 0; row < size; row++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(50);
            RowConstraints rowConstraints = new RowConstraints(50);
            grid.getColumnConstraints().add(columnConstraints);
            grid.getRowConstraints().add(rowConstraints);

            for (int column = 0; column < size; column++) {
                String text = Integer.toString(gameField.get(row, column));
                Text textNode = new Text(text);
                GridPane.setValignment(textNode, VPos.CENTER);
                GridPane.setHalignment(textNode, HPos.CENTER);
                textNode.setFont(new Font(20));
                GridPane.setConstraints(textNode, column, row);
                grid.getChildren().add(textNode);
            }
        }

        return grid;
    }
}
