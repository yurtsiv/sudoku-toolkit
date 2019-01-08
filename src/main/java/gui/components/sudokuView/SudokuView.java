package gui.components.sudokuView;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import logic.sudoku.GameField;

public class SudokuView {
    public static GridPane create(GameField gameField) {
        GridPane grid = new GridPane();
        int size = gameField.getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String text = Integer.toString(gameField.get(i, j));
                Text textNode = new Text(text);
                GridPane.setConstraints(textNode, i, j);
                grid.getChildren().add(textNode);
            }
        }

        return grid;
    }
}
