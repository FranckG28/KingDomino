package v2.models;

import java.util.ArrayList;
import java.util.Arrays;
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

    private List<Preview> previews;

    public Tile[][] getKingdom() {

        if (this.previews != null && previews.size() != 0) {

            Tile[][] result = new Tile[gridSize][gridSize];

            for (int ligne = 0; ligne<gridSize; ligne++) {
                for(int col = 0; col<gridSize; col++) {

                    result[ligne][col] = this.board[ligne][col];

                    for (Preview p:previews) {
                        if (col == p.getX() && ligne == p.getY()) {
                            result[ligne][col] = p.getTile();
                        }
                    }
                }
            }

            return result;
        } else {
            return this.board;
        }
    }

    public void setPreviews(List<Preview> p) {
        this.previews = p;
        notifyObservers();
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

    public void applyPreview() {
        for (Preview p:this.previews) {
            this.board[p.getY()][p.getX()] = p.getTile();
        }
        this.previews = null;
        notifyObservers();
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
        // TODO : Implement Kingdom.isFree
        return true;
    }

}
