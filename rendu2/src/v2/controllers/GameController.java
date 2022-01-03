package v2.controllers;

import v2.Kingdomino;
import v2.models.*;
import v2.views.DominoPlacement;
import v2.views.GameView;
import v2.views.KingDominoDesign;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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

    public void exitGame() {
        // FERMETURE DE LA FENETRE
        view.setVisible(false);
        view.dispose();

        // LIBERATION DES RESSOURCES :
        this.game = null;
        this.view = null;

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
        if (game.getRound() == 0 && game.getDraw().getContent().isEmpty() || game.getRound() > 0 && game.getLastDraw().getContent().isEmpty()) {

            // Si le tirage n'a pas été fait, l'effectuer :
            makeDraw();

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

            } else {
                // Au premier domino de la pioche précédente de placer son roi
                playerChooseDomino(game.getLastDraw().getContent().peek().king);
            }

        }
        return;

    }

    public void makeDraw() {
        LinkedList<Domino> draw = new LinkedList<>();
        int dominoToDraw =  game.getDraw().getSize();
        for (int i = 0; i < dominoToDraw; i++) {
            draw.add(game.getDeck().pop());
        }

        // Tri des dominos dans l'ordre croissant
        Collections.sort(draw, new DominoComparator());

        // La pioche précédente prend le contenu de celle actuelle
        game.getLastDraw().setContent(game.getDraw().getContent());

        // La pioche actuelle prend le nouveau tirage
        game.getDraw().setContent((Queue<Domino>) draw);

        // Lancer l'action suivante :
        play();
    }

    public void playerPlaceCastle() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        JLabel label = new JLabel("Cliquez sur l'emplacement sur lequel vous souhaitez placer votre chateau dans votre rouyaume");
        label.setForeground(Color.WHITE);
        label.setFont(KingDominoDesign.getInstance().textFont.deriveFont(KingDominoDesign.textBase));
        panel.add(label);

        // Affichage à l'utilisateur
        this.view.setAction("Placez votre chateau !", panel);

    }

    public void playerChooseDomino(King king) {
        
        // Définir le joueur à qui appartient le roi comme joueur actuel
        game.setCurrentPlayer(king.getParent());

        // Définir le roi actuel
        king.getParent().currentKing = king;

        // Afficher les instructions
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        this.view.setAction(game.getRound() == 0 ? "Choisissez un domino" : "Choisissez votre prochain domino", empty);

        // Afficher les boutons de choix :
        this.view.getDrawView().showButtons(true);

    }

    public void dominoChosen(Domino domino) {

        // Masquer les boutons
        this.view.getDrawView().showButtons(false);

        // Définir le roi comme posé
        King k = game.getCurrentPlayer().currentKing;
        k.isPlaced = true;

        // Placer le roi sur le domino demandé
        domino.king = k;

        // Rafraichir l'affichage de la pioche:
        game.getDraw().notifyObservers();

        // Lancer l'action suivante :
        if (game.getRound() == 0) {
            // Au premier round, on rejoue
            play();
        } else {
            playerPlaceDomino(game.getLastDraw().pickDomino());
        }

    }

    public void kingdomClicked(Kingdom kingdom, int x, int y) {

        // On vérifie que c'est bien le joueur à qui c'est le tour qui a cliqué dans son royaume
        if (game.getCurrentPlayer().equals(kingdom.getParent())) {

            // On vérifie si c'est le moment de placer le chateau
            if (!placedCastles && !kingdom.hasCastle()) {
                // Placement du chateau
                kingdom.addTile(new Tile(Lands.CASTLE, 0), x, y);
                // Action suivante
                play();
            }

            // Sinon, on vérifie si il a un domino à placer
            if (kingdom.getParent().dominoToPlace != null) {
                // On essaie de placer le domino
                Domino dominoToPlace = kingdom.getParent().dominoToPlace;

                try {

                    kingdom.addDomino(dominoToPlace,x, y);

                    // Le placement a réussi :
                    kingdom.getParent().dominoToPlace = null;

                    // Si c'étais le dernier de la pioche précédente, on entre dans le round suivant
                    if (game.getLastDraw().getContent().isEmpty()) {
                        game.nextRound();
                    }

                    // Lancer l'action suivante
                    play();

                } catch (IllegalArgumentException e) {
                    // Le placement a échoué :
                    e.printStackTrace();
                }

            }

        }

    }

    public void discardDomino() {
        if (game.getCurrentPlayer().dominoToPlace != null) {

            // On retire le domino a placer des mains du joueur
            game.getCurrentPlayer().dominoToPlace = null;

            // Si c'étais le dernier de la pioche précédente, on entre dans le round suivant
            if (game.getLastDraw().getContent().isEmpty()) {
                game.nextRound();
            }

            // Lancer l'action suivante
            play();

        }
    }

    public void playerPlaceDomino(Domino domino) {

        DominoController controller = new DominoController(domino, this);
        DominoPlacement view = new DominoPlacement(controller, domino);

        game.getCurrentPlayer().dominoToPlace = domino;

        this.view.setAction("Placez le domino", view);

    }
}
