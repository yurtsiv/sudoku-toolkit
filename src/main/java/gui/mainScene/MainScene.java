package gui.mainScene;

import gui.components.GameFieldComponent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import logic.sudoku.GameField;

public class MainScene {
    public void start(Stage mainStage) {
        BorderPane mainLayout = new BorderPane();

        GameField gameField = new GameField();

        mainLayout.setCenter(GameFieldComponent.create(gameField));
        mainStage.setScene(new Scene(mainLayout, 800, 800));
        mainStage.show();
    }
}
