package models;

public class Kingdom {

    private final Player parent;

    public Tile[][] board;

    public Kingdom(Player parent) {
        this.parent = parent;
    }

    public Player getParent() {
        return parent;
    }

}
