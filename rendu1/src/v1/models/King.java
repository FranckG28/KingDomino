package v1.models;

public class King {

    private final Player parent;

    public King(Player player) {
        this.parent = player;
    }

    public Player getParent() {
        return parent;
    }

}
