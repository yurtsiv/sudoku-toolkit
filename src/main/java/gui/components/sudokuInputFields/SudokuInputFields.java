package gui.components.sudokuInputFields;

import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.util.ArrayList;


public class SudokuInputFields {
    final private int size;
    private ArrayList<OnCellInputChangeObserver> observers = new ArrayList<>();

    public SudokuInputFields(int size) {
        this.size = size;
    }

    public void addObserver(OnCellInputChangeObserver observer) {
        observers.add(observer);
    }

    private void onCellChange(int row, int column, int value) {
        for (OnCellInputChangeObserver observer : observers) {
            observer.onChange(row, column, value);
        }
    }


    private TextField createInput(int row, int column) {
        TextField input = new TextField();
        input.setPrefSize(50, 50);
        input.setFont(new Font(20));
        input.setAlignment(Pos.CENTER);

        input.textProperty().addListener(
            (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (newValue.length() == 0) {
                    onCellChange(row, column, 0);
                    return;
                }

                if (
                    newValue.length() != 1 ||
                    !Character.isDigit(newValue.charAt(0)) ||
                    Integer.parseInt(newValue) == 0
                ) {
                   input.setText(oldValue);
                   return;
                }

                onCellChange(row, column, Integer.parseInt(newValue));
            }
        );

        return input;
    }

    public GridPane create() {
        GridPane grid = new GridPane();
        grid.getStyleClass().add("game-field-container");

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                TextField input = createInput(row, column);
                StackPane cell = new StackPane();
                GridPane.setConstraints(cell, column, row);
                cell.getStyleClass().add("field-cell");
                cell.pseudoClassStateChanged(right, column == 2 || column == 5);
                cell.pseudoClassStateChanged(bottom, row == 2  || row == 5);

                cell.getChildren().add(input);
                grid.getChildren().add(cell);
            }
        }

        return grid;
    }
}
