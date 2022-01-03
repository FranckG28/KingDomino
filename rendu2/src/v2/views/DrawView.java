package v2.views;

import v2.controllers.GameController;
import v2.models.Domino;
import v2.models.Draw;
import v2.models.DrawObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


public class DrawView extends JPanel implements DrawObserver {

    private final int drawSize;

    private final java.util.List<KingView> kings = new ArrayList<>();
    private final java.util.List<DominoView> dominos = new ArrayList<>();
    private final java.util.List<JButton> buttons = new ArrayList<>();

    private final JLabel titleLabel;

    public DrawView(String title, int drawSize, GameController controller) {

        this.drawSize = drawSize;

        // LAYOUT
        setLayout(new GridBagLayout());
        setOpaque(false);
        setAlignmentY(Component.TOP_ALIGNMENT);
        GridBagConstraints gbc = new GridBagConstraints();

        // TITRE
        gbc.gridwidth = (controller == null) ? 2 : 3;
        this.titleLabel = new JLabel(title);
        titleLabel.setForeground(KingDominoDesign.PINK);
        titleLabel.setFont(KingDominoDesign.getInstance().titleFont);
        add(titleLabel, gbc);
        titleLabel.setVisible(false);

        // Création des éléments :
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(10,10,10,10);
        for(int i = 0; i < drawSize; i++) {

            gbc.gridy = i+1;

            // KingView
            gbc.gridx = 0;
            KingView king = new KingView(null);
            add(king, gbc);
            kings.add(king);

            // Domino
            gbc.gridx = 1;
            DominoView domino = new DominoView(null);
            add(domino, gbc);
            dominos.add(domino);

            // Bouton choisir :
            gbc.gridx = 2;
            JButton button = new KingButton("Choisir");
            class ChooseButtonListener implements ActionListener {
                @Override
                public void actionPerformed( ActionEvent actionEvent ) {
                    controller.dominoChosen(domino.getDomino());
                }
            }
            button.addActionListener(new ChooseButtonListener());
            buttons.add(button);
            button.setVisible(false);
            add(button, gbc);

        }

    }

    public void showButtons(Boolean state) {
        if (!state) {
            // TOUT MASQUER :
            for (JButton b : buttons) {
                b.setVisible(false);
            }
        } else {
            // AFFICHER LES BOUTONS POUR LES DOMINO NON NULS QUI N'ONT PAS DE ROI
            for(int i = 0; i<drawSize; i++) {

                Domino d = dominos.get(i).getDomino();

                buttons.get(i).setVisible(
                        d != null && d.king == null
                );

            }
        }
    }

    @Override
    public void updateDraw(Draw draw) {

        // Mise à jour de la pioche
        for(int i = 0; i<drawSize; i++) {

            Domino d = draw.getDomino(i);

            // King
            kings.get(i).setKing(d != null ? d.king : null);

            // Domino
            dominos.get(i).updateDomino(d);

        }

        titleLabel.setVisible(draw.getContent().size() != 0);

        updateUI();

    }
}
