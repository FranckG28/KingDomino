package v2.views;

import v2.controllers.DominoController;
import v2.models.Domino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DominoPlacement extends JPanel {

    public DominoPlacement(DominoController controller, Domino domino) {

        setLayout(new GridBagLayout());
        setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,0,10,0);
        gbc.gridheight = 2;

        // Création du domino
        JPanel dominoWrapper = new JPanel();
        dominoWrapper.setOpaque(false);
        int wrapperSize = (TileView.tileSize * 2) + 10;
        dominoWrapper.setPreferredSize(new Dimension(wrapperSize, wrapperSize));
        DominoView dominoView = new DominoView(domino);
        domino.addObserver(dominoView);
        dominoWrapper.add(dominoView);
        add(dominoWrapper, gbc);

        // Ajout des boutons de controles
        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10,30,0,0);
        KingButton directionBtn = new KingButton("Tourner");
        class DirectionButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.rotate();
            }
        }
        directionBtn.addActionListener(new DirectionButtonListener());
        directionBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(directionBtn, gbc);

        gbc.gridy = 1;
        KingButton invertButton = new KingButton("Inverser");
        class InvertButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.invert();
            }
        }
        invertButton.addActionListener(new InvertButtonListener());
        invertButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(invertButton, gbc);

        // Ajout de l'instruction
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.gridy = 2;
        JLabel label = new JLabel("Tournez votre domino comme vous le souhaitez puis placez le sur votre grille");
        label.setForeground(Color.WHITE);
        label.setFont(KingDominoDesign.getInstance().textFont.deriveFont(KingDominoDesign.textBase));
        gbc.insets = new Insets(10,0,10,0);
        add(label, gbc);

    }

}
