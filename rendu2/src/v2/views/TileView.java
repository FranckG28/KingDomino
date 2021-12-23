package v2.views;

import v2.models.Tile;

import javax.swing.*;
import java.awt.*;

public class TileView extends JPanel {

    public static final int tileSize = 40;

    private Tile tile;

    public TileView(Tile tile) {

        this.tile = tile;

        // TAILLE DE LA TUILE
        setPreferredSize(new Dimension(tileSize, tileSize));

        // COULEUR DE LA TUILE
        setBackground(
                tile == null
                ? KingDominoDesign.BLACK
                : switch (tile.getLand()) {
                    case MINE -> new Color(106,102,96);
                    case WATER -> new Color(0,146,213);
                    case FOREST -> new Color(112,130,50);
                    case WHEAT -> new Color(255,216,2);
                    case GRASS -> new Color(192,207,11);
                    case SWAMP -> new Color(208,202,180);
                    case CASTLE -> Color.white;
                    default -> KingDominoDesign.BLACK;
                }
        );

        // AFFICHAGE DU NOMBRE DE COURONNE SI IL Y EN A
        if (tile != null && tile.getCrowns() != 0) {
            JLabel c = new JLabel(String.valueOf(tile.getCrowns()));
            c.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.getInstance().textSm));
            c.setForeground(KingDominoDesign.BLACK);
            add(c);
        }

    }

    public Tile getTile() {
        return this.tile;
    }

}
