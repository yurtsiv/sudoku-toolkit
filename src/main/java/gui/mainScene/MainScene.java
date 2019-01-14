package gui.mainScene;

import gui.components.mainMenu.MainMenu;
import gui.components.mainMenu.MenuItem;
import gui.pages.GenerateSudokuPage;
import gui.pages.SolveSudokuPage;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.HashMap;

public class MainScene {
    private Stage mainStage;
    private BorderPane mainLayout = new BorderPane();
    private HashMap<String, Double> uiConfig = new HashMap<>();

    public MainScene(Stage mainStage) {
        this.mainStage = mainStage;
        Rectangle2D primaryScreenBound = Screen.getPrimary().getVisualBounds();
        uiConfig.put("screenHeight", primaryScreenBound.getHeight());
        uiConfig.put("screenWidth", primaryScreenBound.getWidth());
    }

    private void loadAssets(Scene scene) {
        scene.getStylesheets().addAll("main.css", "specific.css");
    }

    public void start() {
        mainStage.setTitle("Sudoku Toolkit");

        MainMenu mainMenu = new MainMenu(uiConfig, MenuItem.SOLVE_SUDOKU);
        Pane solveSudokuPage = new SolveSudokuPage().create(uiConfig);
        Pane generateSudokuPage = new GenerateSudokuPage().create(uiConfig);

        mainMenu.setMenuItemClickListener((menuItem -> {
            switch (menuItem) {
                case SOLVE_SUDOKU:
                    mainLayout.setCenter(solveSudokuPage);
                    break;

                case GENERATE_SUDOKU:
                    mainLayout.setCenter(generateSudokuPage);
            }
        }));

        mainLayout.setCenter(solveSudokuPage);
        mainLayout.setLeft(mainMenu.create());

        Scene scene = new Scene(mainLayout, uiConfig.get("screenWidth"), uiConfig.get("screenHeight"));
        loadAssets(scene);

        mainStage.setScene(scene);
        mainStage.show();
    }
}
