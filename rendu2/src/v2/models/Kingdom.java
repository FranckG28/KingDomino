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
        try {
            if (isFree(x, y)) {
                board[y][x] = tile;
                notifyObservers();
            } else {
                throw new IllegalArgumentException("Cet emplacement est déjà occupé");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void addDomino(Domino domino, int x, int y) {
        try {
            if (canPlaceDomino(domino, x, y)) {
                board[y][x] = domino.getTile1();
                board[domino.getTile2Y(y)][domino.getTile2X(x)] = domino.getTile2();
                notifyObservers();

            } else {
                throw new IllegalArgumentException("Cet emplacement est déjà occupé");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean isFree(int x, int y) {
        if (x < 0 || x >= gridSize || y < 0 || y >= gridSize) {
            return false;
        } else {
            return board[y][x] == null;
        }
    }

    public boolean canPlaceDomino(Domino domino, int x, int y) {

        if (domino.getTile2X(x) >= gridSize || domino.getTile2Y(y) >= gridSize) {
            throw new IndexOutOfBoundsException("Le domino ne peut pas être placé hors de la grille");
        }

        // TODO: Vérifier que le domino est adjacent à une case déjà utilisé

        return isFree(x, y) && isFree(domino.getTile2X(x), domino.getTile2Y(y));
    }

    public void addObserver(KingdomObserver observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        for (KingdomObserver item: this.observers) {
            item.updateKingdom(this);
        }
    }

}
