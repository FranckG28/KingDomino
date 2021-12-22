package views;

import models.Kingdom;
import models.KingdomObserver;
import models.Tile;

import javax.swing.*;
import java.awt.*;

public class KingdomView extends JPanel implements KingdomObserver {

    final int rows = 5;
    final int cols = 5;
    final int borderWidth = 1;

    public KingdomView(Kingdom kingdom) {

        // Configuration du Layout
        setLayout(new GridLayout(rows, cols));
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        updateKingdom(kingdom);

    }

    public void updateKingdom(Kingdom kingdom) {

        // On obtient les Tiles du royaume
        Tile[][] tiles = kingdom.getKingdom();

        // Si le tableau de tile existe
        if (tiles != null) {

            // On vide la panel

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    final JPanel tile = new TileView(tiles[row][col]);
                    if (row == 0) {
                        if (col == 0) {
                            // Top left corner, draw all sides
                            tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        }
                        else {
                            // Top Edge, draw all sides except left Edge
                            tile.setBorder(BorderFactory.createMatteBorder(borderWidth,
                                    0,
                                    borderWidth,
                                    borderWidth,
                                    Color.BLACK));
                        }
                    }
                    else {
                        if (col == 0) {
                            // Left-hand Edge, draw all sides except top
                            tile.setBorder(BorderFactory.createMatteBorder(0,
                                    borderWidth,
                                    borderWidth,
                                    borderWidth,
                                    Color.BLACK));
                        }
                        else {
                            // Neither top Edge nor left Edge, skip both top and left lines
                            tile.setBorder(BorderFactory.createMatteBorder(0,
                                    0,
                                    borderWidth,
                                    borderWidth,
                                    Color.BLACK));
                        }
                    }
                    add(tile);
                }
            }
        }

    }

}
