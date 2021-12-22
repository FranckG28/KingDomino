package views;

import models.Colors;
import javax.swing.*;
import java.awt.*;

public class PlayerEditor extends JPanel {

    private final JTextField name;
    private Colors color;

    private final JLabel colorLabel = new JLabel("•");

    public PlayerEditor() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // INDICATEUR DE COULEUR
        add(colorLabel);

        // ENTREE DU NOM DU JOUEUR
        name = new JTextField("", 10);
        add(name);


    }

    public String getPlayerName() {
        return name.getText();
    }

    public Colors getPlayerColor() {
        return this.color;
    }

    public void setPlayerColor(Colors color) {
        this.color = color;
        colorLabel.setForeground(KingDominoDesign.getColor(this.color));
    }

}
