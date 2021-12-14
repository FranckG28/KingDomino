package models;

import java.util.List;

public class Kingdom {

    private final Player parent;

    public Tile[][] board;

    private DominoPreview preview;

    private List<KingdomObserver> observers;

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

    }
    public DominoPreview getPreview() {

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
