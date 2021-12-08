package models;

import java.util.List;

public class Game {

    private int round;

    public List<Player> players;

    public boolean middle;
    public boolean harmony;

    private List<GameObserver> observers;
    public List<Domino> deck;
    private List<Domino> draw;
    private List<Domino> Lastdraw;
    private Player currentPlayer;


    public Game(List<Player> players, boolean middle, boolean harmony) {
        this.players = players;
        this.middle = middle;
        this.harmony = harmony;
    }

    public Player getCurrentPlayer() {
        return new Player();
    }

    public void setCurrentPlayer(Player player) {

    }
    
    public void nextRound() {
        round ++;
    }

    public int getRound() {
        return this.round;
    }

    public List<Domino> getDraw() {

    }

    public List<Domino> getLastDraw() {

    }

    public void setDraw(List<Domino> draw) {

    }

    public void notifyObservers() {

    }

    public void addObserver(GameObserver observer) {

    }
}
