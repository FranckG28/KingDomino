package v2.views;

import v2.controllers.CastleController;
import v2.models.Lands;
import v2.models.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CastlePlacement extends JPanel {

    private PositionArrows arrows = new PositionArrows();
    private JButton placeButton = new KingButton("Placer");
    private final CastleController controller;

    public CastlePlacement(CastleController controller) {
        this.controller = controller;

        // Associations aux actions des flèches au controller
        arrows.bindAction(controller);

        // Associations du bouton placer au controller
        class PlaceButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.place();
            }
        }
        placeButton.addActionListener(new PlaceButtonListener());

        // LAYOUT
        setLayout(new GridBagLayout());
        setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,20,10,20);

        // AFFICHAGE DU CHATEAU
        add(new TileView(new Tile(Lands.CASTLE, 0)), gbc);

        // FLECHES
        gbc.gridx = 1;
        gbc.weightx = 1;
        add(arrows, gbc);

        // BOUTON PLACER
        placeButton.setBackground(KingDominoDesign.GREEN);
        placeButton.setForeground(Color.white);
        gbc.gridx = 2;
        gbc.weightx = 0;
        add(placeButton, gbc);

    }

    public PositionArrows getArrows() {
        return arrows;
    }

    public JButton getPlaceButton() {
        return placeButton;
    }

}
