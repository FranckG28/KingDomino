package v2.controllers;

import v2.models.Game;
import v2.views.GameView;

public class GameController {

    private Game game;
    private GameView view;

    public GameController(Game game) {
        this.game = game;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void makeDraw() {

    }

    public void playerPlaceCastle() {

    }

    public void playerChooseDomino() {

    }

    public void playerPlaceDomino() {

    }
}
