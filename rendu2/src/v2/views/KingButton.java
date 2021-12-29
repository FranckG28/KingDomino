package v2.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KingButton extends JButton {

    private Color background = KingDominoDesign.PURPLE;

    public KingButton(String text) {

        super(text);

        // Couleur de fond
        setBackground(background);

        // Texte
        setFont(KingDominoDesign.getInstance().textFont.deriveFont(Font.BOLD));
        setForeground(KingDominoDesign.PINK);

        // Suppression des effets par default
        setBorderPainted(false);
        setFocusPainted(false);

        // Ajout d'un effet de passage de souris
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(background.brighter());
                updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(background);
                updateUI();
            }
        });

    }

    public void setBackgroundColor(Color color) {
        this.background = color;
        setBackground(background);
    }

}
