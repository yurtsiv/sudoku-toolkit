package gui.pages;

import gui.components.generateSudokuParams.GenerateSudokuParams;
import gui.components.sudokuView.SudokuView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.sudoku.GameField;
import logic.sudoku.generator.SudokuDifficulty;
import logic.sudoku.generator.SudokuGenerator;
import logic.sudoku.solver.SudokuSolver;

import java.util.HashMap;


public class GenerateSudokuPage implements PageInterface {
    private static GenerateSudokuPage instance;
    private GameField generatedSudoku;

    private GenerateSudokuPage() {}
    private BorderPane mainLayout = new BorderPane();

    public Pane create(HashMap<String, Double> config, Stage mainStage) {
        HBox resultContainer = new HBox(50);
        VBox generatedSudokuContainer = new VBox();

        resultContainer.getStyleClass().add("align-center");
        GenerateSudokuParams sudokuParamsBar = new GenerateSudokuParams();
        sudokuParamsBar.onGenerateBtnClick((SudokuDifficulty difficulty, boolean showSolution) -> {
            resultContainer.getChildren().clear();
            generatedSudokuContainer.getChildren().clear();

            generatedSudoku = SudokuGenerator.generate(difficulty);
            SudokuView generatedSudokuView = new SudokuView(generatedSudoku, mainStage);
            generatedSudokuContainer.getChildren().add(generatedSudokuView.create());
            resultContainer.getChildren().add(generatedSudokuContainer);

            if (showSolution) {
                GameField solvedSudoku = SudokuSolver.solve(generatedSudoku);
                SudokuView solvedSudokuView = new SudokuView(solvedSudoku, mainStage);
                resultContainer.getChildren().add(solvedSudokuView.create());
            }
        });

        mainLayout.setCenter(resultContainer);
        mainLayout.setTop(sudokuParamsBar.create());

        return mainLayout;
    }

    public static GenerateSudokuPage getInstance() {
        if (instance == null) {
            instance = new GenerateSudokuPage();
            return instance;
        }

        return instance;
    }
}
