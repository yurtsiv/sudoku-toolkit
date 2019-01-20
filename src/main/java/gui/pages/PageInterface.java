package gui.pages;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public interface PageInterface {
    Pane create(HashMap<String, Double> config, Stage mainStage);
}
