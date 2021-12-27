package v2.models;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {
    
    public static final int gridSize = 5;

    private final Player parent;

    public Tile[][] board = new Tile[gridSize][gridSize];

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

    public boolean hasCastle() {
        for (Tile line[]:board) {
            for(Tile t:line) {
                if (t != null && t.getLand() == Lands.CASTLE) {
                    return true;
                }
            }
        }

        // Si aucun chateau a été trouvé :
        return false;
    }

    public void addTile(Tile tile, int x, int y) {
        if (isFree(x, y)) {
            board[y][x] = tile;
            notifyObservers();
        } else {
            throw new IllegalArgumentException("Cet emplacement est déjà occupé");
        }
    }

    public void addObserver(KingdomObserver observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        for (KingdomObserver item: this.observers) {
            item.updateKingdom(this);
        }
    }

    public boolean isFree(int x, int y) {
        return board[y][x] == null;
    }

}
