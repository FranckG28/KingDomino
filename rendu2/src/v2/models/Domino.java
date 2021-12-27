package v2.models;

import java.util.ArrayList;
import java.util.List;

public class Domino {

    private final int number;

    private boolean isInverted = false;
    private boolean isVertical = false;

    private final Tile tile1;
    private final Tile tile2;

    private final List<DominoObserver> observers = new ArrayList<>();

    public King king;

    public Domino(int number, Tile tile1, Tile tile2) {
        this.number = number;
        this.tile1 = tile1;
        this.tile2 = tile2;
    }

    public int getNumber() {
        return number;
    }

    public Tile getTile1() {
        return isInverted ? tile2 : tile1;
    }

    public Tile getTile2() {
        return isInverted ? tile1 : tile2;
    }

    public boolean isInverted() {
        return this.isInverted;
    }

    public void setInverted(Boolean state) {
        this.isInverted = state;
        notifyObservers();
    }
    
    public boolean isVertical() {
        return this.isVertical;
    }

    public void setVertical(Boolean state) {
        this.isVertical = state;
        notifyObservers();
    }

    public void addObserver(DominoObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (DominoObserver o:observers) {
            o.updateDomino(this);
        }
    }

}
