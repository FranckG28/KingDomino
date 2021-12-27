package v2.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KingButton extends JButton {

    public static Color defaultColor = KingDominoDesign.PURPLE;
    public static Color hoveredColor = KingDominoDesign.PURPLE.brighter();

    public KingButton(String text) {

        super(text);

        // Couleur de fond
        setBackground(defaultColor);

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
                setBackground(hoveredColor);
                updateUI();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColor);
                updateUI();
            }
        });

    }

}
