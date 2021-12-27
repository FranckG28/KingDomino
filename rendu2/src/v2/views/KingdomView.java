package v2.views;

import v2.controllers.GameController;
import v2.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class KingdomView extends JPanel implements KingdomObserver, GameObserver {

    private static final int borderWidth = 1;
    private static Color borderColor = Color.lightGray;
    private static Integer margins = 10;

    private final GameController controller;
    private final Player player;

    private final JPanel gridPanel = new JPanel(new GridLayout(Kingdom.gridSize, Kingdom.gridSize));

    public KingdomView(Kingdom kingdom, GameController controller) {

        this.controller = controller;
        this.player = kingdom.getParent();

        // Configuration du Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gridPanel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        setBackground(KingDominoDesign.BLACK);
        gridPanel.setOpaque(false);
        gbc.insets = new Insets(margins, 0,0,0);
        add(gridPanel, gbc);

        // Nom du joueur
        JLabel playerName = new JLabel(kingdom.getParent().getName());
        playerName.setFont(KingDominoDesign.getInstance().titleFont);
        playerName.setForeground(KingDominoDesign.getColor(kingdom.getParent().getColor()));

        gbc.gridy=1;
        gbc.insets = new Insets(margins,0,margins,0);
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

                    // Action au clic
                    int finalCol = col;
                    int finalRow = row;
                    MouseListener ml = new MouseAdapter()
                    {
                        @Override
                        public void mousePressed(MouseEvent e)
                        {
                            controller.kingdomClicked(kingdom, finalCol, finalRow);
                        }
                    };
                    tile.addMouseListener(ml);

                    // Affichage des bordures
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

                    // Ajout de la tuile
                    gridPanel.add(tile);
                }
            }
        }

        updateUI();

    }

    @Override
    public void reactGame(Game game) {
        // Modifier la couleur de fond si c'est le joueur actuel
        boolean isActive = game.getCurrentPlayer().equals(this.player);
        System.out.println("IsActive " + player.getName() + " : " + isActive);
        setBackground(isActive ? KingDominoDesign.GRAY : KingDominoDesign.BLACK);
        updateUI();
    }
}
