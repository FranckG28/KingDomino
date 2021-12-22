package v2.views;

import javax.swing.*;
import java.awt.*;

public class KingButton extends JButton {

    public KingButton(String text) {

        super(text);

        setBackground(KingDominoDesign.PURPLE);
        setFont(KingDominoDesign.getInstance().textFont.deriveFont(Font.BOLD));
        setForeground(KingDominoDesign.PINK);

        setBorderPainted(false);
        setFocusPainted(false);

    }

}
