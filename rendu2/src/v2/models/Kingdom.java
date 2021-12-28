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
                throw new IllegalArgumentException("Emplacement invalide");
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

        // On vérifie que le domino est bien entièrement dans la grille
        if (domino.getTile2X(x) >= gridSize || domino.getTile2Y(y) >= gridSize) {
            throw new IndexOutOfBoundsException("Le domino ne peut pas être placé hors de la grille");
        }

        // On vérifie d'abord que les deux cases sont libres
        if (isFree(x, y) && isFree(domino.getTile2X(x), domino.getTile2Y(y))) {

            // Si les cases sont libres, on vérifie qu'au moins l'une d'entre elle est adjacente à une case déjà occupée
            return hasNeighbors(x, y) || hasNeighbors(domino.getTile2X(x), domino.getTile2Y(y));

        } else {
            throw new IllegalArgumentException("Cet emplacement est déjà occupé");
        }
    }

    private boolean hasNeighbors(int x, int y) {

        // Haut
        if (y > 0) {
            if (!isFree(x, y-1)) return true;
        }

        // Bas
        if (y < Kingdom.gridSize-1) {
            if (!isFree(x, y+1)) return true;
        }
        
        // Gauche
        if (x > 0) {
            if (!isFree(x-1, y)) return true;
        }
        
        // Droite
        if (x < Kingdom.gridSize-1) {
            if (!isFree(x+1, y)) return true;
        }
        
        // Si aucun d'entre eux n'avait de voisins, retourner false
        return false;
        
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
