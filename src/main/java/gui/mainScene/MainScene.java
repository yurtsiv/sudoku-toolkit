package gui.mainScene;

import gui.components.mainMenu.MainMenu;
import gui.components.mainMenu.MenuItem;
import gui.screens.GenerateScreen;
import gui.screens.SolveScreen;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.awt.*;
import java.util.HashMap;

public class MainScene {
    private BorderPane mainLayout = new BorderPane();
    private HashMap<String, Double> config = new HashMap<>();

    public MainScene() {
        Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
        config.put("screenHeight", primaryScreenBound.getHeight());
        config.put("screenWidth", primaryScreenBound.getWidth());
    }

    public void start(Stage mainStage) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setMenuItemClickListener((menuItem -> {
            switch (menuItem) {
                case SOLVE_SUDOKU:
                    mainLayout.setCenter(new SolveScreen().create());
                    break;

                case GENERATE_SUDOKU:
                    mainLayout.setCenter(GenerateScreen.create());
            }
        }));

        mainLayout.setCenter(new SolveScreen().create());
        mainLayout.setLeft(mainMenu.create(config, MenuItem.SOLVE_SUDOKU));

        Scene scene = new Scene(mainLayout, config.get("screenWidth"), config.get("screenHeight"));

        scene.getStylesheets().add("main.css");
        mainStage.setTitle("Sudoku Toolkit");
        mainStage.setScene(scene);
        mainStage.show();
    }
}
