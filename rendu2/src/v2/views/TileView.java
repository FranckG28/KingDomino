package v2.views;

import v2.models.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TileView extends JPanel {

    public static final int tileSize = 40;

    private Tile tile;

    private final JLabel crownLabel = new JLabel("");

    public TileView(Tile tile) {

        // TAILLE DE LA TUILE
        setPreferredSize(new Dimension(tileSize, tileSize));

        // Création du MouseListener
        MouseListener hoverListener = new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                setHover(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setHover(false);
            }
        };
        addMouseListener(hoverListener);

        // Affichage du nombre de couronnes
        crownLabel.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.textSm));
        crownLabel.setForeground(KingDominoDesign.BLACK);
        add(crownLabel);

        // Affichage de la tuile
        setTile(tile);

    }

    public Tile getTile() {
        return this.tile;
    }

    private void setHover(Boolean state) {
        if (tile == null) {
            // Effet de survol seulement si la tuile est vide
            setBackground(state ? KingDominoDesign.GRAY : KingDominoDesign.BLACK);
            updateUI();
        }
    }

    public void setTile(Tile tile) {
        this.tile = tile;

        // AFFICHAGE DU NOMBRE DE COURONNE
        crownLabel.setText(
                (tile != null && tile.getCrowns() != 0)
                        ? String.valueOf(tile.getCrowns())
                        : ""
        );

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
                }
        );

        updateUI();

    }

}
