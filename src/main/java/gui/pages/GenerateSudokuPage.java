package gui.pages;

import gui.components.generateSudokuParams.GenerateSudokuParams;
import gui.components.sudokuView.SudokuView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import logic.sudoku.GameField;
import logic.sudoku.generator.SudokuDifficulty;
import logic.sudoku.generator.SudokuGenerator;
import logic.sudoku.solver.SudokuSolver;

import java.util.HashMap;


public class GenerateSudokuPage implements PageInterface {
    private BorderPane mainLayout = new BorderPane();

    public Pane create(HashMap<String, Double> config) {
        HBox resultContainer = new HBox(50);
        resultContainer.getStyleClass().add("align-center");

        GenerateSudokuParams sudokuParamsBar = new GenerateSudokuParams();
        sudokuParamsBar.onGenerateBtnClick((SudokuDifficulty difficulty, boolean showSolution) -> {
            resultContainer.getChildren().clear();
            GameField generatedSudoku = SudokuGenerator.generate(difficulty);
            SudokuView generatedSudokuView = new SudokuView(generatedSudoku);
            resultContainer.getChildren().add(generatedSudokuView.create());
            if (showSolution) {
                GameField solved = SudokuSolver.solve(generatedSudoku);
                SudokuView solvedSudokuView = new SudokuView(solved);
                resultContainer.getChildren().add(solvedSudokuView.create());
            }
        });

        mainLayout.setCenter(resultContainer);
        mainLayout.setTop(sudokuParamsBar.create());

        return mainLayout;
    }
}
