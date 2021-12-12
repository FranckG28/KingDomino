package controllers;

import models.Domino;
import models.Game;
import views.GameView;
import views.PlayerEditor;
import views.StartMenu;

import java.util.Collections;
import java.util.Stack;

public class GameCreator {

    private final Stack<Domino> dominos;
    private final Stack<PlayerEditor> players;
    private StartMenu view;

    public GameCreator(Stack<Domino> dominos) {
        this.dominos = dominos;
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

    public void createGame(StartMenu view) {

        // Définition de la pioche :
        Stack<Domino> gameDeck = (Stack<Domino>) dominos.clone();
        Collections.shuffle(gameDeck);

        // TODO: ATTENTION A NE PAS DONNER TOUS LES DOMINOS SELON LE NB DE JOUEURS

        // Créer le jeu (Game)
        Game game = new Game(view.getPlayers(), gameDeck,view.isMiddle(), view.isHarmony());

        // Créer son controller (GameController)
        GameController controller = new GameController(game);

        // Créer la vue (GameView)
        GameView gameView = new GameView(controller);

        // Ajout de la vue en observer de Game
        game.addObserver(gameView);


    }

}
