package gui.screens;

import gui.components.sudokuInputFields.SudokuInputFields;
import gui.components.sudokuView.SudokuView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.sudoku.GameField;
import logic.sudoku.solver.Solver;

public class SolveScreen {
    private VBox content = new VBox();
    private GameField gameField = new GameField();
    private Text errorMessage = new Text();

    public VBox create() {
        content.getChildren().add(errorMessage);
        SudokuInputFields inputFields = new SudokuInputFields(gameField.getSize());
        content.getChildren().add(inputFields.create());
        inputFields.addObserver(((row, column, value) -> gameField.set(row, column, value)));

        Button solveBtn = new Button("SOLVE");
        solveBtn.setOnAction((e) -> {
            errorMessage.setText("");
            Solver solver = new Solver();

            try {
                solver.solve(gameField);
            } catch (Exception error) {
                errorMessage.setText(error.getMessage());
                return;
            }

            content.getChildren().add(
               SudokuView.create(gameField)
            );
        });

        content.getChildren().add(solveBtn);

        return content;
    }
}
