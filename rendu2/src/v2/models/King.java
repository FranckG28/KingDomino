package v2.models;

public class King {

    private final Player parent;

    public boolean isPlaced = false;

    public King(Player player) {
        this.parent = player;
    }

    public Player getParent() {
        return parent;
    }

}
