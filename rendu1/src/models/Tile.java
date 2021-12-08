package models;

public class Tile {

    private final int crowns;
    private final Lands type;

    public Tile(Lands lands, int crowns) {
        this.type = lands;
        this.crowns = crowns;
    }

    public int getCrowns() {
        return crowns;
    }

    public Lands getLand() {
        return type;
    }

}
