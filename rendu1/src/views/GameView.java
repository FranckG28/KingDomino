package views;

import controllers.GameController;
import models.Game;
import models.GameObserver;

public class GameView implements GameObserver {

    private GameController controller;

    public GameView(GameController controller) {
        this.controller = controller;
    }

    public void react(Game game) {

    }

}
