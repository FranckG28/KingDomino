package models;

import java.util.List;
import java.util.Stack;

public class Game {

    private int round;

    public List<Player> players;

    public boolean middle;
    public boolean harmony;

    private List<GameObserver> observers;
    public Stack<Domino> deck;
    private Stack<Domino> draw;
    private Stack<Domino> Lastdraw;
    private Player currentPlayer;


    public Game(List<Player> players, boolean middle, boolean harmony) {
        this.players = players;
        this.middle = middle;
        this.harmony = harmony;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {

    }
    
    public void nextRound() {
        round ++;
    }

    public int getRound() {
        return this.round;
    }

    public Stack<Domino> getDraw() {
        return null;
    }

    public Stack<Domino> getLastDraw() {
        return null;
    }

    public void setDraw(List<Domino> draw) {

    }

    public void notifyObservers() {

    }

    public void addObserver(GameObserver observer) {

    }
}
