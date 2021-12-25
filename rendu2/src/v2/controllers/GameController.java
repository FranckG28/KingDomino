package v2.controllers;

import v2.Kingdomino;
import v2.models.Domino;
import v2.models.Game;
import v2.models.King;
import v2.models.Player;
import v2.views.CastlePlacement;
import v2.views.DrawView;
import v2.views.GameView;

import javax.swing.*;
import java.util.Stack;

public class GameController {

    private Game game;
    private GameView view;

    private DrawView drawView;

    private boolean placedCastles = false;

    public GameController(Game game) {
        this.game = game;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void setDrawView(DrawView drawView) {
        this.drawView = drawView;
    }

    public void exitGame() {
        // FERMETURE DE LA FENETRE
        view.setVisible(false);
        view.dispose();

        // LIBERATION DES RESSOURCES :
        this.game = null;
        this.view = null;
        this.drawView = null;

        // Réouverture du menu principal
        Kingdomino.openMainMenu();
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

        // Rounds
        if (game.getRound() == 0 && game.getDraw().empty() || game.getRound() > 0 && game.getLastDraw().empty()) {

            // Si le tirage n'a pas été fait, l'effectuer :
            makeDraw();
            return;

        } else {

            if (game.getRound() == 0) {
                // On veut que chaque roi choisisse son premier domino
                for (Player p:game.getAllPlayers()) {
                    for (King k:p.getKings()) {
                        // Pour chaque roi de chaque joueur
                        if (!k.isPlaced) {
                            // Si il n'est pas posé, on le pose
                            playerChooseDomino(k);
                            return;
                        }
                    }
                }

                // Si on arrive ici, tout le monde a choisi, on passe au round suivant et on lance l'action suivante
                game.nextRound();
                play();
                return;

            } else {
                // Au premier domino de la pioche précédente de placer son roi
                playerChooseDomino(game.getLastDraw().get(0).king);
                return;
            }

        }

    }

    public void makeDraw() {
        Stack draw = new Stack();
        int dominoToDraw = (game.getAllPlayers().size() == 3) ? 3 : 4;
        for (int i = 0; i < dominoToDraw; i++) {
            draw.push(game.getDeck().pop());
        }
        game.setDraw(draw);

        // Lancer l'action suivante :
        play();
    }

    public void playerPlaceCastle() {

        // Création de l'interface de placement du chateau
        CastleController controller = new CastleController(game.getCurrentPlayer().getKingdom(), this);
        CastlePlacement view = new CastlePlacement(controller);
        controller.setView(view);

        // Affichage à l'utilisateur
        this.view.setAction("Placez votre chateau !", view);

    }

    public void playerChooseDomino(King king) {
        
        // Définir le joueur à qui appartient le roi comme joueur actuel
        game.setCurrentPlayer(king.getParent());

        // Définir le roi actuel
        king.getParent().currentKing = king;

        // Afficher les instructions
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        this.view.setAction("Choisissez un domino", empty);

        // Afficher les boutons de choix :
        this.drawView.showButtons();

    }

    public void dominoChosen(Domino domino) {

        // Définir le roi comme posé
        King k = game.getCurrentPlayer().currentKing;
        k.isPlaced = true;

        // Placer le roi sur le domino demandé
        domino.king = k;

        // Rafraichir l'affichage :
        game.notifyObservers();

        // Lancer l'action suivante :
        if (game.getRound() == 0) {
            // Au premier round, on rejoue
            play();
        } else {
            // TODO: Sinon on place le domino précédent
        }

    }

    public void playerPlaceDomino() {
        // TODO: Si c'étais le dernier de la pioche, passer au round suivant

    }
}
