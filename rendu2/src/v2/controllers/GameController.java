package v2.controllers;

import v2.models.Game;
import v2.models.Player;
import v2.views.CastlePlacement;
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

    public void play() {

        // Placer les chateaux
        for (Player p:game.getAllPlayers()) {
            game.setCurrentPlayer(p);
            playerPlaceCastle();
        }

        // Boucle de parties


        // Fin de partie

    }

    public void makeDraw() {

    }

    public void playerPlaceCastle() {

        // Création de l'interface de placement du chateau
        CastleController controller = new CastleController(game.getCurrentPlayer().getKingdom());
        CastlePlacement view = new CastlePlacement(controller);
        controller.setView(view);

        // Affichage à l'utilisateur
        this.view.setAction("Placez votre chateau !", view);

    }

    public void playerChooseDomino() {

    }

    public void playerPlaceDomino() {

    }
}
