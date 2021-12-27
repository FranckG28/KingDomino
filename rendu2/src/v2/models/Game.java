package v2.models;

import java.util.*;

public class Game {

    private int round = 0;

    private List<Player> players;
    private Player currentPlayer;

    private boolean middle;
    private boolean harmony;

    private Stack<Domino> deck;
    private Queue<Domino> draw = new LinkedList<>();
    private Queue<Domino> lastDraw = new LinkedList<>();

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
    public Queue<Domino> getDraw() {
        return this.draw;
    }
    public Queue<Domino> getLastDraw() {
        return this.lastDraw;
    }

    public Domino pickDominoToPlace() {
        Domino result = this.lastDraw.poll();
        notifyObservers();
        return result;
    }

    public void setDraw(Queue<Domino> draw) {
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
    }

    public Stack<Domino> getDeck() {
        return this.deck;
    }
}
