package v2.controllers;

import v2.models.Kingdom;
import v2.views.DominoPlacement;

public class DominoController {

    private Kingdom kingdom;
    private DominoPlacement view;

    public DominoController(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public void rotate() {

    }

    public void invert() {

    }

    public void setView(DominoPlacement view) {
        this.view = view;
    }

}
