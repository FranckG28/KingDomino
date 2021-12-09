package models;

import java.util.List;

public class Player {

    private final String name;
    private final List<King> kings;
    private final Colors color;

    public Player(String name, Colors color, List<King> kings) {
        this.name = name;
        this.color = color;
        this.kings = kings;
    }

    public String getName() {
        return this.name;
    }

    public Colors getColor() {
        return this.color;
    }

    public List<King> getKings() {
        return this.kings;
    }

}
