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

        for (int i = 0; i < size; i++) {
            ColumnConstraints column = new ColumnConstraints(50);
            RowConstraints row = new RowConstraints(50);
            grid.getColumnConstraints().add(column);
            grid.getRowConstraints().add(row);

            for (int j = 0; j < size; j++) {
                String text = Integer.toString(gameField.get(i, j));
                Text textNode = new Text(text);
                GridPane.setValignment(textNode, VPos.CENTER);
                GridPane.setHalignment(textNode, HPos.CENTER);
                textNode.setFont(new Font(20));
                GridPane.setConstraints(textNode, i, j);
                grid.getChildren().add(textNode);
            }
        }

        return grid;
    }
}
