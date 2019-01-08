package gui.screens;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class GenerateScreen {
    public static Pane create() {
        HBox layout = new HBox();
        Button btn = new Button("Hello");
        layout.getChildren().add(btn);
        return layout;
    }
}
