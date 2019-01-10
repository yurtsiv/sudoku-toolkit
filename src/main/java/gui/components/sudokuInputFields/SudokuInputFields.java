package gui.components.sudokuInputFields;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

    public GridPane create() {
        GridPane grid = new GridPane();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int row = i;
                final int column = j;

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

                GridPane.setConstraints(input, column, row);
                grid.getChildren().add(input);
            }
        }

        return grid;
    }
}
