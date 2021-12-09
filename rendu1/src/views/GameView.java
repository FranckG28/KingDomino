package views;

import controllers.GameController;
import models.*;

public class GameView implements GameObserver, DominoPreviewsObserver {

    private GameController controller;

    public GameView(GameController controller) {
        this.controller = controller;
    }

    public void reactGame(Game game) {

    }

    @Override
    public void reactPreview(DominoPreview preview) {

    }
}
