package gui.pages;

import gui.components.sudokuInputFields.SudokuInputFields;
import gui.components.sudokuView.SudokuView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.sudoku.GameField;
import logic.sudoku.solver.SudokuSolver;

import java.util.HashMap;

public class SolveSudokuPage implements PageInterface {
    private HBox pageContent = new HBox(50);
    private GameField gameField = new GameField();
    private Pane solvedSudokuView;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);


    public Pane create(HashMap<String, Double> config, Stage mainStage) {
        pageContent.getStyleClass().add("solve-sudoku-page-content");

        SudokuInputFields inputFields = new SudokuInputFields(gameField.getSize());
        inputFields.addObserver(((row, column, value) -> gameField.set(row, column, value)));

        Button solveBtn = new Button("Solve");
        solveBtn.getStyleClass().addAll("action-button", "solve-btn");

        solveBtn.setOnAction((e) -> {
            if (solvedSudokuView != null) {
                pageContent.getChildren().remove(solvedSudokuView);
            }


            try {
                GameField solvedSudoku = SudokuSolver.solve(gameField);
                solvedSudokuView = new SudokuView(solvedSudoku).create();
                pageContent.getChildren().add(solvedSudokuView);
            } catch (Exception error) {
                error.printStackTrace();
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(error.getMessage());
                errorAlert.show();
            }
        });

        VBox sudokuInputContainer = new VBox(10);
        sudokuInputContainer.getChildren().addAll(inputFields.create(), solveBtn);
        pageContent.getChildren().add(sudokuInputContainer);

        return pageContent;
    }
}
