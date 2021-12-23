package v2.controllers;

import v2.models.Kingdom;
import v2.models.MovableInterface;
import v2.views.CastlePlacement;
import v2.views.DominoPlacement;

public class CastleController implements MovableInterface {

    private final Kingdom kingdom;
    private CastlePlacement view;

    private int x;
    private int y;

    public CastleController(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public void moveRight() {

    }

    public void moveLeft() {

    }

    public void moveUp() {

    }

    public void moveDown() {

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

    public void place() {

    }

    public void setView(CastlePlacement view) {
        this.view = view;
    }

}
