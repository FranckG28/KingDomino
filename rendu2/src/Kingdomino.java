import controllers.GameCreator;
import views.StartMenu;

import static controllers.CSVReader.getDominos;

public class Kingdomino {

    public static void main(String[] args) {
        GameCreator controller = new GameCreator(getDominos());
        StartMenu startMenu = new StartMenu(controller);
        controller.setView(startMenu);
    }

}
