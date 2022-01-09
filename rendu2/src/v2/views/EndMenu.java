package v2.views;

import v2.Kingdomino;
import v2.models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EndMenu extends JFrame {

    public EndMenu(java.util.List<Player> players) {

        // CONTENEUR PRINCIPAL

        JPanel mainContainer = new JPanel(new BorderLayout());
        mainContainer.setBackground(KingDominoDesign.BLACK);
        mainContainer.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        // HEADER

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("KingDomino");
        title.setForeground(KingDominoDesign.YELLOW);
        title.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.textMd));
        header.add(title);
        header.add(Box.createHorizontalGlue());

        mainContainer.add(header, BorderLayout.PAGE_START);

        // CONTENU

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints contentGbc = new GridBagConstraints();

        contentGbc.insets = new Insets(20, 0, 20, 0);

        JLabel endTitle = new JLabel("Partie terminée !");
        endTitle.setForeground(KingDominoDesign.YELLOW);
        endTitle.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(Font.BOLD).deriveFont(KingDominoDesign.textXl));

        contentGbc.gridwidth = players.size();
        contentPanel.add(endTitle, contentGbc);

        contentGbc.gridwidth = 1;
        contentGbc.insets = new Insets(5, 15, 5, 15);

        int i = 0;
        for (Player p: players) {

            JLabel name = new JLabel((i+1) + "");
            KingdomView kingdom = new KingdomView(p.getKingdom(), null);
            kingdom.updateKingdom(p.getKingdom());
            JLabel score = new JLabel(p.score+"pts");

            name.setForeground(KingDominoDesign.getColor(p.getColor()));
            name.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.textMd));

            score.setForeground(Color.white);
            score.setFont(KingDominoDesign.getInstance().textFont.deriveFont(KingDominoDesign.textMd));

            contentGbc.gridx = i;
            contentGbc.gridy = 1;
            contentPanel.add(name, contentGbc);
            contentGbc.gridy = 2;
            contentPanel.add(kingdom,contentGbc);
            contentGbc.gridy = 3;
            contentPanel.add(score, contentGbc);
            i++;

        }

        mainContainer.add(contentPanel, BorderLayout.CENTER);


        // CREATION DES KINGDOMVIEWS QUI OBSERVENT LEURS ROYAUMES

        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new BoxLayout(buttonBar, BoxLayout.X_AXIS));

        KingButton exitBtn = new KingButton("Quitter");
        class ExitButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                System.exit(0);
            }
        }
        exitBtn.addActionListener(new ExitButtonListener());
        buttonBar.add(exitBtn);

        buttonBar.add(Box.createHorizontalGlue());

        KingButton newGameBtn = new KingButton("Nouvelle partie");
        class NewGameButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                Kingdomino.openMainMenu();
                setVisible(false);
            }
        }
        newGameBtn.addActionListener(new NewGameButtonListener());
        buttonBar.add(newGameBtn);
        buttonBar.setOpaque(false);

        mainContainer.add(buttonBar, BorderLayout.PAGE_END);


        // PARAMETRES ET OUVERTURE

        add(mainContainer);

        setResizable(false);
        setSize(1200, 800);
        setTitle("KingDomino - Partie terminée !");
        setVisible(true);

    }
}
