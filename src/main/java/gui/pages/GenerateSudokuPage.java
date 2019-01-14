package gui.pages;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.HashMap;


public class GenerateSudokuPage implements PageInterface {
    public Pane create(HashMap<String, Double> config) {
        HBox layout = new HBox();
        Button btn = new Button("Hello");
        layout.getChildren().add(btn);
        return layout;
    }
}
