package v2.views;

import v2.models.Colors;

import javax.swing.*;
import java.awt.*;

public class KingView extends JPanel {

    public KingView(Colors color) {

        setPreferredSize(new Dimension(20,20));
        setBackground(KingDominoDesign.getColor(color));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

}
