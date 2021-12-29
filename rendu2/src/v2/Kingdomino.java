package v2;

import v2.controllers.GameCreator;
import v2.views.StartMenu;

public class Kingdomino {

    public static void main(String[] args) {
        openMainMenu();
    }

    public static void openMainMenu() {
        GameCreator controller = new GameCreator();
        StartMenu startMenu = new StartMenu(controller);
        controller.setView(startMenu);
    }

}
