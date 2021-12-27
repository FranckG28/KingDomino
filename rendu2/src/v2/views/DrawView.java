package v2.views;

import v2.controllers.GameController;
import v2.models.Domino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DrawView extends JPanel {

    private java.util.List<JButton> buttons = new ArrayList<>();

    public DrawView(Queue dominos, String title, GameController controller) {

        // LAYOUT
        setLayout(new GridBagLayout());
        setOpaque(false);
        setAlignmentY(Component.TOP_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();

        // TITRE
        if (dominos != null && !dominos.isEmpty()) {
            gbc.gridwidth = (controller == null) ? 2 : 3;
            JLabel titleLabel = new JLabel(title);
            titleLabel.setForeground(KingDominoDesign.PINK);
            AffineTransform transform = new AffineTransform();
            titleLabel.setFont(KingDominoDesign.getInstance().titleFont);
            add(titleLabel, gbc);
        }

        // DOMINOS
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(10,10,10,10);
        int i = 1;
        for (Object o:dominos) {

            Domino d = (Domino)o;

            gbc.gridy = i;

            if(d.king != null) {
                gbc.gridx = 0;
                add(new KingView(d.king.getParent().getColor()), gbc);
            }

            gbc.gridx = 1;
            add(new DominoView(d), gbc);

            if (controller != null && d.king == null) {
                gbc.gridx = 2;
                JButton button = new KingButton("Choisir");
                class ChooseButtonListener implements ActionListener {
                    @Override
                    public void actionPerformed( ActionEvent actionEvent ) {
                        controller.dominoChosen(d);
                    }
                }
                button.addActionListener(new ChooseButtonListener());
                buttons.add(button);
                button.setVisible(false);
                add(button, gbc);
            }

            i++;
        }
        
    }

    public void showButtons() {
        for (JButton b:buttons) {
            b.setVisible(true);
        }
    }

}
