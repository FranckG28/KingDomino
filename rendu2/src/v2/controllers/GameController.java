package v2.controllers;

import v2.Kingdomino;
import v2.models.*;
import v2.views.DominoPlacement;
import v2.views.DrawView;
import v2.views.GameView;
import v2.views.KingDominoDesign;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

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
        if (game.getRound() == 0 && game.getDraw().isEmpty() || game.getRound() > 0 && game.getLastDraw().isEmpty()) {

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
                playerChooseDomino(game.getLastDraw().peek().king);
                return;
            }

        }

    }

    public void makeDraw() {
        Queue<Domino> draw = new LinkedList<>();
        int dominoToDraw = (game.getAllPlayers().size() == 3) ? 3 : 4;
        for (int i = 0; i < dominoToDraw; i++) {
            draw.add(game.getDeck().pop());
        }
        // TODO: Trier la pioche par numéro de domino croissant
        game.setDraw(draw);

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
            playerPlaceDomino(game.pickDominoToPlace());
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
                    if (game.getLastDraw().isEmpty()) {
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

    public void playerPlaceDomino(Domino domino) {

        DominoController controller = new DominoController(game.getCurrentPlayer().getKingdom(), domino);
        DominoPlacement view = new DominoPlacement(controller, domino);

        game.getCurrentPlayer().dominoToPlace = domino;

        this.view.setAction("Placez le domino", view);

    }
}
