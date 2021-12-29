package v2.controllers;

import v2.models.Domino;
import v2.models.Game;
import v2.models.Player;
import v2.views.GameView;
import v2.views.PlayerEditor;
import v2.views.StartMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class GameCreator {

    private final Stack<PlayerEditor> players;
    private StartMenu view;

    public GameCreator() {
        this.players = new Stack<>();
        players.push(new PlayerEditor());
        players.push(new PlayerEditor());
    }

    public void setView(StartMenu view) {
        this.view = view;
        view.refreshPlayers(this.players);
    }

    public void addPlayer() {
        this.players.push(new PlayerEditor());
        view.refreshPlayers(this.players);
    }

    public void removePlayer() {
        this.players.pop();
        view.refreshPlayers(this.players);
    }

    public boolean canAddPlayer() {
        return (this.players.size() < 4);
    }

    public boolean canRemovePlayer() {
        return (this.players.size() > 2);
    }

    public java.util.List<Player> getPlayers() {
        ArrayList<Player> list = new ArrayList<>();
        for (PlayerEditor item:players) {
            list.add(new Player(item.getPlayerName(), item.getPlayerColor(), (players.size() == 2) ? 2 : 1));
        }
        return list;
    }

    public void createGame(StartMenu view) {

        // Copie de la pioche :
        Stack<Domino> gameDeck = new Stack<>();
        gameDeck.addAll(CSVReader.getDominos());

        // Mélange de la pioche
        Collections.shuffle(gameDeck);

        // TODO: ATTENTION A NE PAS DONNER TOUS LES DOMINOS SELON LE NB DE JOUEURS

        // Obtention des joueurs
        List<Player> playersList = getPlayers();

        // On vérifie que tous les joueurs ont une couleur différente
        for (Player p: playersList) {
            for (Player p2: playersList) {
                if (!p.equals(p2)) {
                    if (p.getColor().equals(p2.getColor())) {
                        // On affiche un message d'erreur et on annule la création de la partie
                        JOptionPane.showMessageDialog(null, "Veuillez choisir une couleur différente pour chaque joueur !");
                        return;
                    }
                }
            }
        }

        // Créer le jeu (Game)
        Game game = new Game(playersList, gameDeck, view.isMiddle(), view.isHarmony());

        // Créer son controller (GameController)
        GameController controller = new GameController(game);

        // Créer la vue (GameView)
        GameView gameView = new GameView(controller, game);

        // Ajout de la vue au controlleur
        controller.setView(gameView);

        // Ajout de la vue en observer de Game
        game.addObserver(gameView);

        // Fermeture du menu principal
        view.setVisible(false);

        // Démarrage du jeu
        controller.play();

        // Libération des ressources du menu
        view.dispose();
        players.clear();

    }

}
