package v2.controllers;

import v2.models.Domino;

public class DominoController {

    private final Domino domino;
    private final GameController controller;

    public DominoController(Domino domino, GameController controller) {
        this.domino = domino;
        this.controller = controller;
    }

    public void rotate() {
        this.domino.setVertical(!domino.isVertical());
    }

    public void invert() {
        this.domino.setInverted(!domino.isInverted());
    }

    public void discard() {this.controller.discardDomino();}

}
