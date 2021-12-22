package v2.views;

import v2.models.Tile;

import javax.swing.*;
import java.awt.*;

public class TileView extends JPanel {

    private Tile tile;

    public TileView(Tile tile) {

        this.tile = tile;

        setSize(30, 30);

        setBackground(Color.BLUE);

    }

    public Tile getTile() {
        return this.tile;
    }

}
