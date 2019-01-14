package gui.components.mainMenu;

import gui.components.ComponentInterface;
import javafx.css.PseudoClass;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class MainMenu implements ComponentInterface {
    private OnMenuItemClickListener clickListener;
    private HashMap<MenuItem, Button> menuButtons = new HashMap<>();
    private PseudoClass activePseudoClass = PseudoClass.getPseudoClass("active");
    private HashMap<String, Double> uiConfig;
    private MenuItem defaultActiveMenuItem;

    public MainMenu(HashMap<String, Double> uiConfig, MenuItem defaultActiveMenuItem) {
        this.uiConfig = uiConfig;
        this.defaultActiveMenuItem = defaultActiveMenuItem;
    }

    public void setMenuItemClickListener(OnMenuItemClickListener listener) {
        clickListener = listener;
    }

    private void onMenuItemClick(MenuItem menuItem) {
        for (Button menuButton : menuButtons.values()) {
            menuButton.pseudoClassStateChanged(activePseudoClass, false);
        }

        menuButtons.get(menuItem).pseudoClassStateChanged(activePseudoClass, true);
        if (clickListener != null) {
            clickListener.onClick(menuItem);
        }
    }


    public VBox create() {
        VBox menuLayout = new VBox();
        menuLayout.setMaxHeight(uiConfig.get("screenHeight"));
        menuLayout.getStyleClass().add("main-menu-container");

        VBox buttonsContainer = new VBox();
        buttonsContainer.getStyleClass().add("main-menu-buttons-container");

        ImageView logo = new ImageView(new Image("sudoku.png"));
        logo.getStyleClass().add("logo");
        logo.setPreserveRatio(true);
        logo.setFitWidth(100);
        menuLayout.getChildren().add(logo);

        for (MenuItem item : MenuItem.values()) {

            String title = String.join(" ", item.name().split("_"));
            Button menuButton = new Button(title);
            if (item == defaultActiveMenuItem) {
                menuButton.pseudoClassStateChanged(activePseudoClass, true);
            }

            menuButtons.put(item, menuButton);
            menuButton.getStyleClass().add("main-menu-item");
            menuButton.setOnAction((e) -> onMenuItemClick(item));
            buttonsContainer.getChildren().add(menuButton);
        }

        menuLayout.getChildren().add(buttonsContainer);

        return menuLayout;
    }
}
