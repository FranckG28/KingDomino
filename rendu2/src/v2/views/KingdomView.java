package v2.views;

import v2.controllers.GameController;
import v2.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KingdomView extends JPanel implements KingdomObserver, GameObserver {

    private final static int borderWidth = 1;
    private final static Color borderColor = Color.lightGray;
    private final static Integer margins = 10;

    private final Kingdom kingdom;

    private int previewX = -1;
    private int previewY = -1;

    private final JPanel gridPanel = new JPanel(new GridLayout(Kingdom.gridSize, Kingdom.gridSize));

    public KingdomView(Kingdom kingdom, GameController controller) {

        this.kingdom = kingdom;

        // Configuration du Layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gridPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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

        // Supprimer la preview si la souris n'est plus dans le composant
        gridPanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                removePreview();
            }
        });

        // Creation des cases
        for (int row = 0; row < Kingdom.gridSize; row++) {
            for (int col = 0; col < Kingdom.gridSize; col++) {

                final JPanel tile = new TileView(null);

                // Ajout des actions aux cases
                int finalCol = col;
                int finalRow = row;
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        controller.kingdomClicked(kingdom, finalCol, finalRow);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        setPreview(finalCol, finalRow);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        removePreview();
                    }

                });

                // Ajout des bordures
                if (row == 0) {
                    if (col == 0) {
                        // Top left corner, draw all sides
                        tile.setBorder(BorderFactory.createLineBorder(borderColor));
                    } else {
                        // Top Edge, draw all sides except left Edge
                        tile.setBorder(BorderFactory.createMatteBorder(borderWidth,
                                0,
                                borderWidth,
                                borderWidth,
                                borderColor));
                    }
                } else {
                    if (col == 0) {
                        // Left-hand Edge, draw all sides except top
                        tile.setBorder(BorderFactory.createMatteBorder(0,
                                borderWidth,
                                borderWidth,
                                borderWidth,
                                borderColor));
                    } else {
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

    private TileView getTileView(int x, int y) {
        return (TileView)gridPanel.getComponent(y*Kingdom.gridSize+x);
    }

    public void updateKingdom(Kingdom kingdom) {

        // On obtient les Tiles du royaume
        Tile[][] tiles = kingdom.getKingdom();

        // On récupère le domino que le joueur aurait potentiellement à placer
        Domino dominoToPlace = kingdom.getParent().dominoToPlace;

        // Si le tableau de tile existe
        if (tiles != null) {

            // On passe dans toutes les cases
            for (int row = 0; row < Kingdom.gridSize; row++) {
                for (int col = 0; col < Kingdom.gridSize; col++) {

                    Tile tileToDisplay = tiles[row][col];

                    // Si il y a une preview, c'est peut être ici qu'elle doit être placé
                    if (dominoToPlace != null) {
                        if (previewY != -1 && previewX != -1) {

                            // Une preview existe, vérifier si elle doit être affiché ici :

                            if (previewX == col && previewY == row) {
                                // Si c'est la tuile 1
                                tileToDisplay = dominoToPlace.getTile1();
                            } else if (dominoToPlace.getTile2X(previewX) == col && dominoToPlace.getTile2Y(previewY) == row) {
                                // Si c'est la tuile 2
                                tileToDisplay = dominoToPlace.getTile2();
                            }

                        }
                    }

                    // Mise à jour de la tuile seulement si necessaire
                    TileView view = getTileView(col, row);
                    if (view.getTile() != tileToDisplay) {
                        view.setTile(tileToDisplay);
                    }

                }
            }

        }
    }

    @Override
    public void reactGame(Game game) {
        // Modifier la couleur de fond si c'est le joueur actuel
        boolean isActive = game.getCurrentPlayer().equals(this.kingdom.getParent());
        setBackground(isActive ? KingDominoDesign.GRAY : KingDominoDesign.BLACK);
        updateUI();
    }

    public void setPreview(int x, int y) {
        // Seulement si le joueur à un domino a placer

        Domino domino = kingdom.getParent().dominoToPlace;
        if (domino != null) {

            // Essaie d'ajout de la preview si l'emplacement est correct
            try {
                if (kingdom.canPlaceDomino(domino, x, y)) {

                    // Le domino peut être placé, on défini la preview
                    this.previewX = x;
                    this.previewY = y;

                    updateKingdom(kingdom);

                } else {
                    // Si c'est pas correct, pas de preview :
                    removePreview();
                }
            } catch (Exception e) {
                // Si le placement n'est pas correct, pas de preview
                removePreview();
            }

        }

    }

    public void removePreview() {
        // On supprime la preview uniquement si il y en avait une
        if (previewY != -1 && previewX != -1){
            this.previewX = -1;
            this.previewY = -1;
            updateKingdom(kingdom);
        }
    }
}
