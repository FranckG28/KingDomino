package v2.models;

public class Preview {

    private final Tile tile;
    private final int x;
    private final int y;

    public Preview(Tile t, int x, int y) {
        this.tile = t;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Tile getTile() {
        return this.tile;
    }

}
