package gui.screens;

import gui.components.sudokuInputFields.SudokuInputFields;
import gui.components.sudokuView.SudokuView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.sudoku.GameField;
import logic.sudoku.solver.Solver;

public class SolveScreen {
    private VBox content = new VBox();
    private GameField gameField = new GameField();
    private GridPane solvedSudokuView;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);


    public VBox create() {
        SudokuInputFields inputFields = new SudokuInputFields(gameField.getSize());
        content.getChildren().add(inputFields.create());
        inputFields.addObserver(((row, column, value) -> gameField.set(row, column, value)));

        Button solveBtn = new Button("SOLVE");
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
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(error.getMessage());
                errorAlert.showAndWait();
            }
        });

        content.getChildren().add(solveBtn);

        return content;
    }
}
