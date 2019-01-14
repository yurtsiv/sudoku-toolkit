package gui.components.mainMenu;

import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.util.HashMap;

public class MainMenu {
    private OnMenuItemClickListener clickListener;
    private HashMap<MenuItem, Button> menuButtons = new HashMap<>();
    private PseudoClass activePseudoClass = PseudoClass.getPseudoClass("active");

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


    public VBox create(HashMap<String, Double> config, MenuItem defaultActie) {
        VBox menuLayout = new VBox();
        menuLayout.setMaxHeight(config.get("screenHeight"));
        menuLayout.getStyleClass().add("main-menu-container");
        menuLayout.setAlignment(Pos.TOP_CENTER);
        menuLayout.setPadding(new Insets(30, 0, 0, 0));

        VBox buttonsContainer = new VBox();
        buttonsContainer.setPadding(new Insets(30, 0, 0, 0));

        ImageView logo = new ImageView(new Image("sudoku.png"));
        logo.getStyleClass().add("logo");
        logo.setPreserveRatio(true);
        logo.setFitWidth(100);
        menuLayout.getChildren().add(logo);
        for (MenuItem item : MenuItem.values()) {

            String title = String.join(" ", item.name().split("_"));
            Button menuButton = new Button(title);
            if (item == defaultActie) {
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
