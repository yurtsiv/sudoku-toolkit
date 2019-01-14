package gui.screens;

import gui.components.sudokuInputFields.SudokuInputFields;
import gui.components.sudokuView.SudokuView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.sudoku.GameField;
import logic.sudoku.solver.Solver;

public class SolveScreen {
    private HBox content = new HBox(50);
    private GameField gameField = new GameField();
    private GridPane solvedSudokuView;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);


    public HBox create() {

        SudokuInputFields inputFields = new SudokuInputFields(gameField.getSize());
        inputFields.addObserver(((row, column, value) -> gameField.set(row, column, value)));

        Button solveBtn = new Button("Solve");
        solveBtn.getStyleClass().addAll("action-button", "solve-btn");
        solveBtn.setOnAction((e) -> {
            if (solvedSudokuView != null) {
                content.getChildren().remove(solvedSudokuView);
            }

            System.out.println("Entered Matrix:");
            gameField.print();
            Solver solver = new Solver();


            try {
                GameField solved = solver.solve(gameField);
                System.out.println("Solved:");
                solved.print();
                solvedSudokuView = SudokuView.create(solved);
                content.getChildren().add(solvedSudokuView);
            } catch (Exception error) {
                error.printStackTrace();
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(error.getMessage());
                errorAlert.show();
            }
        });

        VBox sudokuInputContainer = new VBox(10);
        sudokuInputContainer.getChildren().addAll(inputFields.create(), solveBtn);
        content.getChildren().add(sudokuInputContainer);

        return content;
    }
}
