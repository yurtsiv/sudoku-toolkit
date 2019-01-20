package gui.components.generateSudokuParams;

import gui.components.ComponentInterface;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import logic.sudoku.generator.SudokuDifficulty;

public class GenerateSudokuParams implements ComponentInterface {
    private HBox layout = new HBox(20);
    private OnGenerateBtnClickListener generateClickListener;

    public void onGenerateBtnClick(OnGenerateBtnClickListener listener) {
        generateClickListener = listener;
    }

    public Pane create() {
        layout.getStyleClass().add("generate-sudoku-options");

        ComboBox difficultySelect = new ComboBox<SudokuDifficulty>();
        for (SudokuDifficulty difficulty : SudokuDifficulty.values()) {
            difficultySelect.getItems().add(difficulty);
        }
        difficultySelect.setValue(SudokuDifficulty.EASY.name());

        CheckBox showSolutionCheckBox = new CheckBox("Show solution");
        showSolutionCheckBox.setSelected(false);

        Button generateBtn = new Button("Generate");
        generateBtn.getStyleClass().add("action-button");
        generateBtn.setOnAction((e) -> {
           if (generateClickListener != null) {
               difficultySelect.valueProperty().get();
               generateClickListener.onClick(
                   (SudokuDifficulty)difficultySelect.getValue(),
                   showSolutionCheckBox.isSelected()
               );
           }
        });

        layout.getChildren().addAll(difficultySelect, showSolutionCheckBox, generateBtn) ;
        return layout;
    }
}
