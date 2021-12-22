package v2.views;

import v2.models.Domino;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class DrawView extends JPanel {

    public DrawView(java.util.List<Domino> dominos, String title) {

        // LAYOUT
        setLayout(new GridBagLayout());
        setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        // TITRE
        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(KingDominoDesign.PINK);
        AffineTransform transform = new AffineTransform();
        transform.rotate(- Math.PI / 2);
        titleLabel.setFont(KingDominoDesign.getInstance().textFont.deriveFont(transform));

        gbc.gridheight = 4;
        add(titleLabel, gbc);

        // DOMINOS
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(10,10,10,10);
        int i = 0;
        for (Domino d:dominos) {
            gbc.gridy = i;
            add(new DominoView(d), gbc);
            i++;
        }
        
    }

}
