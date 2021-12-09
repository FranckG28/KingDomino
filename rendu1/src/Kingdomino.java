import controllers.CSVReader;
import controllers.GameCreator;
import views.StartMenu;

public class Kingdomino {

    public static void main(String[] args) {

        GameCreator controller = new GameCreator(CSVReader.getDominos());
        StartMenu startMenu = new StartMenu(controller);
    }

}
