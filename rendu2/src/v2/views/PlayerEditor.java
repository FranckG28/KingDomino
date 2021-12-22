package v2.views;

import v2.models.Colors;

import javax.swing.*;
import java.awt.*;

public class PlayerEditor extends JPanel {

    private final PlaceholderTextField name;
    private Colors color;

    private final JPanel colorIndicator = new JPanel();

    public PlayerEditor() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(KingDominoDesign.BLACK);

        // INDICATEUR DE COULEUR
        colorIndicator.setSize(60,60);
        add(colorIndicator);

        add(Box.createRigidArea(new Dimension(5,0)));

        // ENTREE DU NOM DU JOUEUR
        name = new PlaceholderTextField("", 10);
        name.setPlaceholder("Entrez un pseudo");
        name.setFont(KingDominoDesign.getInstance().titleFont);
        name.setForeground(Color.WHITE);
        name.setOpaque(false);
        name.setBorder(BorderFactory.createEmptyBorder());
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
        colorIndicator.setBackground(KingDominoDesign.getColor(this.color));
    }

}
