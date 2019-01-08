package gui.components.mainMenu;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenu {
    private OnMenuItemClickListener clickListener;

    public void setMenuItemClickListener(OnMenuItemClickListener listener) {
        clickListener = listener;
    }

    private void onMenuItemClick(MenuItem menuItem) {
        if (clickListener != null) {
            clickListener.onClick(menuItem);
        }
    }


    public VBox create() {
        VBox menuLayout = new VBox();

        for (MenuItem item : MenuItem.values()) {
            String title = String.join(" ", item.name().split("_"));
            Button menuButton = new Button(title);
            menuButton.setOnAction((e) -> onMenuItemClick(item));
            menuLayout.getChildren().add(menuButton);
        }

        return menuLayout;
    }
}
