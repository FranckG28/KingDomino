package v2.views;

import v2.models.Domino;
import v2.models.DominoObserver;
import v2.models.Kingdom;

import javax.swing.*;
import java.awt.*;

public class DominoView extends JPanel implements DominoObserver {

    private Domino domino;

    private final int borderWidth = 1;
    private final Color borderColor = Color.lightGray;

    public DominoView(Domino domino) {
        this.domino = domino;

        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        setBackground(Color.black);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);

        updateDomino(domino);

    }

    public Domino getDomino() {
        return domino;
    }

    @Override
    public void updateDomino(Domino domino) {
        removeAll();

        // Réglage de la direction du domino
        setLayout(new GridLayout(domino.isVertical() ? 2 : 1, domino.isVertical() ? 1 : 2));

        // Création des tuiles
        JPanel tile1 = new TileView(this.domino.getTile1());
        JPanel tile2 = new TileView(this.domino.getTile2());

        // Première tuile, toutes les bordures
        tile1.setBorder(BorderFactory.createLineBorder(borderColor));

        if (domino.isVertical()) {
            // 2ème tuile, toutes les bordures sauf celle du haut si vertical
            tile2.setBorder(BorderFactory.createMatteBorder(0,
                    borderWidth,
                    borderWidth,
                    borderWidth,
                    borderColor));
        } else {
            // 2ème tuile, toutes les bordures sauf celle de gauche si horizontale
            tile2.setBorder(BorderFactory.createMatteBorder(borderWidth,
                    0,
                    borderWidth,
                    borderWidth,
                    borderColor));
        }

        add(tile1);
        add(tile2);

        updateUI();
    }
}
