package v1.models;

import java.util.ArrayList;
import java.util.List;

public class DominoPreview {

    private int x = 0;
    private int y = 0;

    // EAST par défaut
    private Orientations orientation = Orientations.EAST;

    private Kingdom kingdom;

    private Domino domino;
    private List<DominoPreviewsObserver> observers = new ArrayList<>();

    public DominoPreview(Domino domino, Kingdom kingdom) {
        this.domino = domino;
        this.kingdom = kingdom;
    }
    public void setPositionX(int x) {
        this.x = x;
        kingdom.notifyObservers();
    }
    public void setPositionY(int y) {
        this.y = y;
        kingdom.notifyObservers();
    }
    public void setOrientation(Orientations orientation) {
        this.orientation = orientation;
        kingdom.notifyObservers();
    }

    public boolean canGoUp() {
        return true;
    }

    public boolean canGoDown() {
        return true;
    }

    public boolean canGoLeft() {
        return true;
    }

    public boolean canGoRight() {
        return true;
    }

    public boolean canBeOriented(Orientations orientation) {
        return true;
    }

    public int getPositionX() {
        return this.x;
    }
    public int getPositionY() {
        return this.y;
    }
    public Orientations getOrientation() {
        return this.orientation;
    }

}
