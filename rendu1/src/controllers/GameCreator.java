package controllers;

import models.Domino;
import models.Game;
import views.GameView;
import views.StartMenu;

import java.util.Collections;
import java.util.Stack;

public class GameCreator {

    private final Stack<Domino> dominos;

    public GameCreator(Stack<Domino> dominos) {
        this.dominos = dominos;
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
