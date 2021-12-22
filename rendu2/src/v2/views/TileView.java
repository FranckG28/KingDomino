package v2.views;

import v2.models.Tile;

import javax.swing.*;
import java.awt.*;

public class TileView extends JPanel {

    public static final int tileSize = 30;

    private Tile tile;

    public TileView(Tile tile) {

        this.tile = tile;

        setPreferredSize(new Dimension(tileSize, tileSize));

        setBackground(Color.BLUE);

    }

    public Tile getTile() {
        return this.tile;
    }

}
