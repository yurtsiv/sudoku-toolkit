package gui.components;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.sudoku.GameField;

public class GameFieldComponent  {
    public static GridPane create(GameField gameField) {
        GridPane grid = new GridPane();

        for (int i = 0; i < gameField.getSize(); i++) {
            for (int j = 0; j < gameField.getSize(); j++) {
                TextField input = new TextField();
                GridPane.setConstraints(input, i, j);
                grid.getChildren().add(input);
            }
        }

        return grid;
    }
}
