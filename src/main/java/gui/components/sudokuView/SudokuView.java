package gui.components.sudokuView;

import gui.components.ComponentInterface;
import javafx.css.PseudoClass;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.imageGenerator.ImageGenerator;
import logic.sudoku.GameField;

public class SudokuView implements ComponentInterface {
    private GameField gameField;
    private Stage mainStage;

    public SudokuView(GameField gameField, Stage mainStage) {
        this.mainStage = mainStage;
        this.gameField = gameField;
    }

    public Pane create() {
        GridPane grid = new GridPane();
        VBox contentContainer = new VBox(20);

        int size = gameField.getSize();

        Button saveBtn = new Button("Save");
        saveBtn.getStyleClass().add("secondary-button");
        saveBtn.setOnAction((e) -> ImageGenerator.generateAndSave(gameField, mainStage));

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                StackPane cell = new StackPane();
                GridPane.setConstraints(cell, column, row);
                cell.getStyleClass().add("field-cell");
                cell.pseudoClassStateChanged(right, column == 2 || column == 5);
                cell.pseudoClassStateChanged(bottom, row == 2  || row == 5);

                int cellValue = gameField.get(row, column);
                String text = cellValue == 0 ? "·" : Integer.toString(gameField.get(row, column));
                Text textNode = new Text(text);
                cell.getChildren().add(textNode);
                grid.getChildren().add(cell);
            }
        }

        contentContainer.getChildren().addAll(grid, saveBtn);
        return contentContainer;
    }
}
