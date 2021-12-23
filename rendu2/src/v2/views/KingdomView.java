package v2.views;

import v2.models.Kingdom;
import v2.models.KingdomObserver;
import v2.models.Tile;

import javax.swing.*;
import java.awt.*;

public class KingdomView extends JPanel implements KingdomObserver {

    private final int borderWidth = 1;
    private final Color borderColor = Color.lightGray;

    private final JPanel gridPanel = new JPanel(new GridLayout(Kingdom.gridSize, Kingdom.gridSize));

    public KingdomView(Kingdom kingdom) {

        // Configuration du Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gridPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        setOpaque(false);
        gridPanel.setOpaque(false);

        add(gridPanel, gbc);

        // Nom du joueur
        JLabel playerName = new JLabel(kingdom.getParent().getName());
        playerName.setFont(KingDominoDesign.getInstance().titleFont);
        playerName.setForeground(KingDominoDesign.getColor(kingdom.getParent().getColor()));

        gbc.gridy=1;
        gbc.insets = new Insets(10,0,0,0);
        add(playerName, gbc);

        // Affichage initial du royaume
        updateKingdom(kingdom);

    }

    public void updateKingdom(Kingdom kingdom) {

        // On obtient les Tiles du royaume
        Tile[][] tiles = kingdom.getKingdom();

        // Si le tableau de tile existe
        if (tiles != null) {

            // On vide le panel
            gridPanel.removeAll();

            for (int row = 0; row < Kingdom.gridSize; row++) {
                for (int col = 0; col < Kingdom.gridSize; col++) {
                    final JPanel tile = new TileView(tiles[row][col]);
                    if (row == 0) {
                        if (col == 0) {
                            // Top left corner, draw all sides
                            tile.setBorder(BorderFactory.createLineBorder(borderColor));
                        }
                        else {
                            // Top Edge, draw all sides except left Edge
                            tile.setBorder(BorderFactory.createMatteBorder(borderWidth,
                                    0,
                                    borderWidth,
                                    borderWidth,
                                    borderColor));
                        }
                    }
                    else {
                        if (col == 0) {
                            // Left-hand Edge, draw all sides except top
                            tile.setBorder(BorderFactory.createMatteBorder(0,
                                    borderWidth,
                                    borderWidth,
                                    borderWidth,
                                    borderColor));
                        }
                        else {
                            // Neither top Edge nor left Edge, skip both top and left lines
                            tile.setBorder(BorderFactory.createMatteBorder(0,
                                    0,
                                    borderWidth,
                                    borderWidth,
                                    borderColor));
                        }
                    }
                    gridPanel.add(tile);
                }
            }
        }

    }

}
