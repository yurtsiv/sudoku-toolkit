package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Main extends Application {
    @Override
    public void start(Stage scene) throws Exception {
        Button button = new Button("Hello");
        StackPane stack = new StackPane();
        stack.getChildren().add(button);
        scene.setScene(new Scene(stack, 800, 800));
        scene.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
