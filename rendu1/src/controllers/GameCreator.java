package controllers;

import models.Domino;
import views.GameView;

import java.util.Stack;

public class GameCreator {

    private Stack<Domino> dominos;

    public GameCreator(Stack<Domino> dominos) {
        this.dominos = dominos;
    }

    public void createGame(GameView view) {

    }
}
