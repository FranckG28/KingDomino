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
        gbc.insets = new Insets(0,30,0,0);
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

        gbc.gridy = 0;
        gbc.gridx = 2;
        KingButton discardButton = new KingButton("Défausser");
        class DiscardButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.discard();
            }
        }
        discardButton.addActionListener(new DiscardButtonListener());
        discardButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        discardButton.setBackgroundColor(KingDominoDesign.RED);
        discardButton.setForeground(Color.WHITE);
        add(discardButton, gbc);

        // Ajout de l'instruction
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.gridy = 2;
        gbc.weightx = 1;
        JLabel label = new JLabel("Tournez votre domino comme vous le souhaitez puis placez le sur votre grille");
        label.setForeground(Color.WHITE);
        label.setFont(KingDominoDesign.getInstance().textFont.deriveFont(KingDominoDesign.textBase));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        gbc.insets = new Insets(10,0,0,0);
        add(label, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0,0,0,0);
        JLabel label2 = new JLabel("Défaussez votre domino si vous ne pouvez pas le placer");
        label2.setForeground(KingDominoDesign.RED);
        label2.setAlignmentX(Component.LEFT_ALIGNMENT);
        label2.setFont(KingDominoDesign.getInstance().textFont.deriveFont(KingDominoDesign.textBase));
        add(label2, gbc);

    }

}
