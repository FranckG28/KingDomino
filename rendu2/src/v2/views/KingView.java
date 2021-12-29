package v2.views;

import v2.models.King;

import javax.swing.*;
import java.awt.*;

public class KingView extends JPanel {

    public static final int borderSize = 2;
    public static final Color borderColor = KingDominoDesign.GRAY;

    public KingView(King king) {

        setPreferredSize(new Dimension(20,20));

        setKing(king);

    }

    public void setKing(King king) {

        setBackground(king == null
                        ? KingDominoDesign.BLACK
                        : KingDominoDesign.getColor(king.getParent().getColor())
        );

        setBorder(king == null
                        ? BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize)
                        : BorderFactory.createLineBorder(borderColor, borderSize)
        );

        setOpaque(king != null);

        updateUI();
    }

}
