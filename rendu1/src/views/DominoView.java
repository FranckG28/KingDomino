package views;

import models.Domino;

import javax.swing.*;
import java.awt.*;

public class DominoView extends JPanel {

    private Domino domino;

    public DominoView(Domino domino) {
        this.domino = domino;

        setLayout(new GridLayout(2, 1));

        add(new TileView(this.domino.getTile1()));
        add(new TileView(this.domino.getTile2()));

    }

    public Domino getDomino() {
        return domino;
    }

}
