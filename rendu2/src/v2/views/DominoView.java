package v2.views;

import v2.models.Domino;
import v2.models.Kingdom;

import javax.swing.*;
import java.awt.*;

public class DominoView extends JPanel {

    private Domino domino;

    private final int borderWidth = 1;
    private final Color borderColor = Color.lightGray;

    public DominoView(Domino domino) {
        this.domino = domino;

        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        setLayout(new GridLayout(2, 1));

        JPanel tile1 = new TileView(this.domino.getTile1());
        JPanel tile2 = new TileView(this.domino.getTile2());

        // Première tuile, toutes les bordures
        tile1.setBorder(BorderFactory.createLineBorder(borderColor));

        // 2ème tuile, toutes les bordures sauf celle, de gauche
        tile2.setBorder(BorderFactory.createMatteBorder(borderWidth,
                0,
                borderWidth,
                borderWidth,
                Color.BLACK));

        add(tile1);
        add(tile2);

    }

    public Domino getDomino() {
        return domino;
    }

}
