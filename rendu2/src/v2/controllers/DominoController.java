package v2.controllers;

import v2.models.Domino;
import v2.models.Kingdom;

public class DominoController {

    private Kingdom kingdom;
    private Domino domino;

    public DominoController(Kingdom kingdom, Domino domino) {
        this.kingdom = kingdom;
        this.domino = domino;
    }

    public void rotate() {
        this.domino.setVertical(!domino.isVertical());
    }

    public void invert() {
        this.domino.setInverted(!domino.isInverted());
    }

}
