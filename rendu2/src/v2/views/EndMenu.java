package v2.views;

import v2.Kingdomino;
import v2.models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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

        contentGbc.insets = new Insets(15, 0, 15, 0);

        JLabel endTitle = new JLabel("Partie terminée !");
        endTitle.setForeground(KingDominoDesign.YELLOW);
        endTitle.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(Font.BOLD).deriveFont(KingDominoDesign.textXl));

        contentGbc.gridwidth = 3;
        contentPanel.add(endTitle, contentGbc);

        contentGbc.gridwidth = 1;

        int i = 1;
        for (Player p: players) {

            JLabel rank = new JLabel(""+i);
            JLabel name = new JLabel(p.getName());
            JLabel score = new JLabel(p.score+"pts");
            
            name.setPreferredSize(new Dimension(400, 40));

            java.util.List<JLabel> labelList =  new ArrayList<>(Arrays.asList(rank, name, score));

            for (JLabel label:labelList) {
                label.setForeground(KingDominoDesign.getColor(p.getColor()));
                label.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.textMd));
            }

            contentGbc.gridy = i;
            contentGbc.gridx = 0;
            contentPanel.add(rank, contentGbc);
            contentGbc.gridx = 1;
            contentGbc.insets = new Insets(15, 20, 15, 0);
            contentPanel.add(name, contentGbc);
            contentGbc.insets = new Insets(15, 0, 15, 0);
            contentGbc.gridx = 2;
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
