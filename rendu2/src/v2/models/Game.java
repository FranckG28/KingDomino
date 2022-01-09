package v2.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {

    private int round = 0;

    private final List<Player> players;
    private Player currentPlayer;

    private final boolean middle;
    private final boolean harmony;

    private final Stack<Domino> deck;
    private final Draw draw;
    private final Draw lastDraw;

    private final List<GameObserver> observers = new ArrayList<>();

    public Game(List<Player> players, Stack<Domino> deck, boolean middle, boolean harmony) {
        this.players = players;
        this.deck = deck;
        this.middle = middle;
        this.harmony = harmony;

        // Création des pioches :
        int drawSize = getAllPlayers().size() == 3 ? 3 : 4;
        this.draw = new Draw(drawSize);
        this.lastDraw = new Draw(drawSize);

        // On enlève les dominos en trop selon le nombre de joueurs
        int dominoToRemove;
        switch (players.size()) {
            case 2:
                dominoToRemove = 24;
                break;
            case 3:
                dominoToRemove = 12;
                break;
            default:
                dominoToRemove = 0;
                break;
        };

        for(int i = 0; i<dominoToRemove; i++) {
            this.deck.pop();
        }

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

    public Draw getDraw() {
        return this.draw;
    }
    public Draw getLastDraw() {
        return this.lastDraw;
    }
    public Stack<Domino> getDeck() {
        return this.deck;
    }

    public boolean isMiddle() {
        return this.middle;
    }
    public boolean isHarmony() {
        return this.harmony;
    }

    public void notifyObservers() {
        for (GameObserver item: this.observers) {
            item.reactGame(this);
        }
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }




}
