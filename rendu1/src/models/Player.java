package models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String name;
    private final List<King> kings;
    private final Colors color;
    private Kingdom kingdom;

    public Player(String name, Colors color, int numberOfKings) {
        this.name = name;
        this.color = color;

        // Création des rois
        ArrayList<King> kingList = new ArrayList<>();
        for (int i = 0; i<numberOfKings; i++) {
            kingList.add(new King(this));
        }
        this.kings = kingList;
        this.kingdom = new Kingdom(this);
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
