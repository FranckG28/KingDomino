package v2.views;

import v2.models.Colors;

import javax.swing.*;
import java.awt.*;

public class PlayerEditor extends JPanel {

    private final JTextField name;
    private Colors color;

    private final JPanel colorIndicator = new JPanel();

    public PlayerEditor() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(KingDominoDesign.BLACK);

        // INDICATEUR DE COULEUR
        colorIndicator.setPreferredSize(new Dimension(20,35));
        add(colorIndicator);

        add(Box.createRigidArea(new Dimension(10,0)));

        // ENTREE DU NOM DU JOUEUR
        name = new JTextField("Joueur", 10);
        name.setFont(KingDominoDesign.getInstance().titleFont);
        name.setForeground(Color.WHITE);
        name.setOpaque(false);
        name.setBorder(BorderFactory.createEmptyBorder());
        name.setAlignmentY(Component.CENTER_ALIGNMENT);
        name.setCaretColor(Color.white);
        add(name);

        setPreferredSize(new Dimension(-1, 35));


    }

    public String getPlayerName() {
        return name.getText();
    }

    public Colors getPlayerColor() {
        return this.color;
    }

    public void setPlayerColor(Colors color) {
        this.color = color;
        colorIndicator.setBackground(KingDominoDesign.getColor(this.color));
    }

}
