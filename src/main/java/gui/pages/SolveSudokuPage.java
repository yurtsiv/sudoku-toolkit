package gui.pages;

import gui.components.sudokuInputFields.SudokuInputFields;
import gui.components.sudokuView.SudokuView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.imageGenerator.ImageGenerator;
import logic.sudoku.GameField;
import logic.sudoku.solver.SudokuSolver;

import java.util.HashMap;

public class SolveSudokuPage implements PageInterface {
    private static SolveSudokuPage instance;
    private HBox pageContent = new HBox(50);
    private GameField gameField = new GameField();
    private Pane solvedSudokuView;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);


    private SolveSudokuPage() {}

    private void showError(String message) {
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText(message);
        errorAlert.show();
    }

    public Pane create(HashMap<String, Double> config, Stage mainStage) {
        pageContent.getStyleClass().add("solve-sudoku-page-content");

        SudokuInputFields inputFields = new SudokuInputFields(gameField.getSize());
        inputFields.addObserver(((row, column, value) -> gameField.set(row, column, value)));

        Button solveBtn = new Button("Solve");
        solveBtn.getStyleClass().add("primary-button");
        solveBtn.setOnAction((e) -> {
            if (solvedSudokuView != null) {
                pageContent.getChildren().remove(solvedSudokuView);
            }


            try {
                GameField solvedSudoku = SudokuSolver.solve(gameField);
                solvedSudokuView = new SudokuView(solvedSudoku, mainStage).create();
                pageContent.getChildren().add(solvedSudokuView);
            } catch (Exception error) {
                showError(error.getMessage());
            }
        });

        Button exportBtn = new Button("Export");
        exportBtn.getStyleClass().add("secondary-button");
        exportBtn.setOnAction((e) -> {
            if (!gameField.isValid()) {
                showError("Game field is invalid");
                return;
            }

            ImageGenerator.generateAndSave(gameField, mainStage);
        });

        VBox sudokuInputContainer = new VBox(20);
        HBox buttonsContainer = new HBox(10);
        buttonsContainer.getChildren().addAll(solveBtn, exportBtn);
        sudokuInputContainer.getChildren().addAll(inputFields.create(), buttonsContainer);
        pageContent.getChildren().add(sudokuInputContainer);

        return pageContent;
    }

    public static SolveSudokuPage getInstance() {
        if (instance == null) {
            instance = new SolveSudokuPage();
            return instance;
        }

        return instance;
    }
}
