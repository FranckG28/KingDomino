package v2.controllers;

import v2.models.Kingdom;
import v2.models.MovableInterface;
import v2.models.Orientations;
import v2.views.DominoPlacement;

public class DominoController implements MovableInterface {

    private Kingdom kingdom;
    private DominoPlacement view;

    public DominoController(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public void moveRight() {

    }

    public void moveLeft() {

    }

    @Override
    public boolean canGoUp() {
        return false;
    }

    @Override
    public boolean canGoDown() {
        return false;
    }

    @Override
    public boolean canGoRight() {
        return false;
    }

    @Override
    public boolean canGoLeft() {
        return false;
    }

    public void moveUp() {

    }

    public void moveDown() {

    }

    public void rotate() {

    }

    public void invert() {

    }

    public void place() {

    }

    public void setView(DominoPlacement view) {
        this.view = view;
    }

}
