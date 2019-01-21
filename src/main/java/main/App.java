package main;

import javafx.application.Application;
import javafx.stage.Stage;
import gui.mainScene.MainScene;


public class App extends Application {
    @Override
    public void start(Stage mainStage) {
        new MainScene(mainStage).start();
    }

    public void main(String[] args) {
        launch(args);
    }
}
