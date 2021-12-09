package models;

import java.util.ArrayList;
import java.util.List;

public class DominoPreview {

    private int x = 0;
    private int y = 0;

    // EAST par défaut
    private Orientations orientation = Orientations.EAST;

    private Domino domino;
    private List<DominoPreviewsObserver> observers = new ArrayList<>();

    public DominoPreview(Domino domino) {
        this.domino = domino;
    }

    public void setPositionX(int x) {
        this.x = x;
        notifyObservers();
    }

    public void setPositionY(int y) {
        this.y = y;
        notifyObservers();
    }

    public int getPositionX() {
        return this.x;
    }

    public int getPositionY() {
        return this.y;
    }

    public void setOrientation(Orientations orientation) {
        this.orientation = orientation;
        notifyObservers();
    }

    public Orientations getOrientation() {
        return this.orientation;
    }

    public void addObserver(DominoPreviewsObserver observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        for (DominoPreviewsObserver item: this.observers) {
            item.reactPreview(this);
        }
    }
}
