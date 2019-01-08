package gui.mainScene;

import gui.components.mainMenu.MainMenu;
import gui.screens.GenerateScreen;
import gui.screens.SolveScreen;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainScene {
    private BorderPane mainLayout = new BorderPane();

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
        mainLayout.setLeft(mainMenu.create());
        mainStage.setScene(new Scene(mainLayout, 800, 800));
        mainStage.show();
    }
}
