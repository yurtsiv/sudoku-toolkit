package gui.components.mainMenu;

import javafx.css.PseudoClass;
import javafx.scene.control.Button;
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


    public VBox create(HashMap<String, Double> config) {
        VBox menuLayout = new VBox();
        menuLayout.setMaxHeight(config.get("screenHeight"));
        menuLayout.getStyleClass().add("main-menu-container");

        for (MenuItem item : MenuItem.values()) {
            String title = String.join(" ", item.name().split("_"));
            Button menuButton = new Button(title);
            menuButtons.put(item, menuButton);
            menuButton.getStyleClass().add("main-menu-item");
            menuButton.setOnAction((e) -> onMenuItemClick(item));
            menuLayout.getChildren().add(menuButton);
        }

        return menuLayout;
    }
}
