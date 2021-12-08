package models;

public class Domino {

    private final int number;

    private final Tile tile1;
    private final Tile tile2;

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
        return tile1;
    }

    public Tile getTile2() {
        return tile2;
    }

}
