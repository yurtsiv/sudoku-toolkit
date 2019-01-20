package gui.pages;

import gui.components.generateSudokuParams.GenerateSudokuParams;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;


public class GenerateSudokuPage implements PageInterface {
    private BorderPane mainLayout = new BorderPane();


    public Pane create(HashMap<String, Double> config) {
        GenerateSudokuParams sudokuParamsBar = new GenerateSudokuParams();
        mainLayout.setTop(sudokuParamsBar.create());

        return mainLayout;
    }
}
