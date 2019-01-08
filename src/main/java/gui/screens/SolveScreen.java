package gui.screens;

import gui.components.sudokuInputFields.SudokuInputFields;
import gui.components.sudokuView.SudokuView;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.sudoku.GameField;
import logic.sudoku.solver.Solver;

public class SolveScreen {
    private VBox content = new VBox();
    private GameField gameField = new GameField();
    private Text errorMessage = new Text();
    private GridPane solvedSudokuView;

    public VBox create() {
        content.getChildren().add(errorMessage);
        SudokuInputFields inputFields = new SudokuInputFields(gameField.getSize());
        content.getChildren().add(inputFields.create());
        inputFields.addObserver(((row, column, value) -> gameField.set(row, column, value)));

        Button solveBtn = new Button("SOLVE");
        solveBtn.setOnAction((e) -> {
            if (solvedSudokuView != null) {
                content.getChildren().remove(solvedSudokuView);
            }

            errorMessage.setText("");
            gameField.print();
            Solver solver = new Solver();


            try {
                GameField solved = solver.solve(gameField);
                solvedSudokuView = SudokuView.create(solved);
                content.getChildren().add(solvedSudokuView);
            } catch (Exception error) {
                errorMessage.setText(error.getMessage());
            }
        });

        content.getChildren().add(solveBtn);

        return content;
    }
}
