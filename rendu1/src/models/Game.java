package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {

    private int round;

    private List<Player> players;
    private Player currentPlayer;

    private boolean middle;
    private boolean harmony;

    private Stack<Domino> deck;
    private Stack<Domino> draw;
    private Stack<Domino> lastDraw;

    private List<GameObserver> observers = new ArrayList<>();

    public Game(List<Player> players, Stack<Domino> deck, boolean middle, boolean harmony) {
        this.players = players;
        this.deck = deck;
        this.middle = middle;
        this.harmony = harmony;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
        notifyObservers();
    }

    public List<Player> getAllPlayers() {
        return this.players;
    }
    
    public void nextRound() {
        round ++;
        notifyObservers();
    }

    public int getRound() {
        return this.round;
    }
    public Stack<Domino> getDraw() {
        return this.draw;
    }
    public Stack<Domino> getLastDraw() {
        return this.lastDraw;
    }

    public void setDraw(Stack<Domino> draw) {
        this.lastDraw = this.draw;
        this.draw = draw;
        notifyObservers();
    }

    public void notifyObservers() {
        for (GameObserver item: this.observers) {
            item.reactGame(this);
        }
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public boolean isMiddle() {
        return this.middle;
    }

    public boolean isHarmony() {
        return this.harmony;
    };

    public Stack<Domino> getDeck() {
        return this.deck;
    }
}
