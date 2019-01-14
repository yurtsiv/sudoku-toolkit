package main;

import javafx.application.Application;
import javafx.stage.Stage;
import gui.mainScene.MainScene;

public class Main extends Application {
    @Override
    public void start(Stage mainStage) {
        new MainScene(mainStage).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
