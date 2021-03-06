package v1.models;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {

    private final Player parent;

    public Tile[][] board;

    private DominoPreview preview;

    private List<KingdomObserver> observers = new ArrayList<>();

    public Kingdom(Player parent) {
        this.parent = parent;
    }
    public Player getParent() {
        return parent;
    }

    public Tile[][] getKingdom() {
        return this.board;
    }

    public DominoPreview createPreview(Domino domino) {
        return null;
    }

    public DominoPreview getPreview() {
        return null;
    }

    public void applyPreview() {
    }

    public void addObserver(KingdomObserver observer) {
        this.observers.add(observer);
    }
    public void removeObserver(KingdomObserver observer) {

    }
    public void notifyObservers() {
        for (KingdomObserver item: this.observers) {
            item.updateKingdom(this);
        }
    }
}
