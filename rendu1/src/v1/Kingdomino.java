package v1;

import v1.controllers.GameCreator;
import v1.views.StartMenu;

import static v1.controllers.CSVReader.getDominos;

public class Kingdomino {

    public static void main(String[] args) {
        GameCreator controller = new GameCreator(getDominos());
        StartMenu startMenu = new StartMenu(controller);
        controller.setView(startMenu);
    }

}
