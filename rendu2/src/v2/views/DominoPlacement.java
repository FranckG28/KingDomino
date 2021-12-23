package v2.views;

import v2.controllers.DominoController;

import javax.swing.*;

public class DominoPlacement extends JPanel {

    private PositionArrows arrows = new PositionArrows();
    private JButton placeButton = new KingButton("Placer");
    private final DominoController controller;

    public DominoPlacement(DominoController controller) {
        this.controller = controller;
    }

    public PositionArrows getArrows() {
        return arrows;
    }

    public JButton getPlaceButton() {
        return placeButton;
    }

}
