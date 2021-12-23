package v2.controllers;

import v2.models.Game;
import v2.models.Player;
import v2.views.CastlePlacement;
import v2.views.GameView;

public class GameController {

    private Game game;
    private GameView view;

    private boolean placedCastles = false;

    public GameController(Game game) {
        this.game = game;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void play() {

        // Placer les chateaux
        if (!placedCastles) {
            for (Player p:game.getAllPlayers()) {
                if (!p.getKingdom().hasCastle()) {
                    // Si le joueur n'a pas de chateau, on lui demande de le placer
                    game.setCurrentPlayer(p);
                    playerPlaceCastle();
                    return;
                }
            }
        }

        placedCastles = true;
        System.out.println("Tout les chateaux sont placés");
        // Rounds


        // Fin de partie

    }

    public void makeDraw() {

    }

    public void playerPlaceCastle() {

        // Création de l'interface de placement du chateau
        CastleController controller = new CastleController(game.getCurrentPlayer().getKingdom(), this);
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
