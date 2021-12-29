package v2.views;


import v2.controllers.GameCreator;
import v2.models.Colors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class StartMenu extends JFrame {

    private final JPanel playersPanel = new JPanel();
    private final JButton addPlayerButton = new KingButton("+");
    private final JButton removePlayerButton = new KingButton("-");

    private final JCheckBox middleCheckBox = new JCheckBox("Empire du milieu");
    private final JCheckBox harmonyCheckBox = new JCheckBox("Harmonie");

    private final GameCreator controller;

    public StartMenu(GameCreator controller) {

        this.controller = controller;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(KingDominoDesign.BLACK);
        mainPanel.setLayout(new BorderLayout());

        // HEADER

        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Kingdomino", SwingConstants.CENTER);
        title.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.textXl));
        title.setForeground(KingDominoDesign.YELLOW);
        mainPanel.add(title, BorderLayout.PAGE_START);

        // REGLES PERSO

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(KingDominoDesign.BLACK);
        centerPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 0;

        constraints.insets = new Insets(10,0,0,0);
        JLabel rulesTitle = new JLabel("Règles :");
        rulesTitle.setFont(KingDominoDesign.getInstance().textFont.deriveFont(Font.BOLD));
        rulesTitle.setForeground(KingDominoDesign.RED);
        centerPanel.add(rulesTitle, constraints);

        middleCheckBox.setBackground(KingDominoDesign.BLACK);
        middleCheckBox.setFont(KingDominoDesign.getInstance().textFont);
        middleCheckBox.setForeground(Color.white);
        harmonyCheckBox.setBackground(KingDominoDesign.BLACK);
        harmonyCheckBox.setFont(KingDominoDesign.getInstance().textFont);
        harmonyCheckBox.setForeground(Color.white);

        constraints.insets = new Insets(0,0,0,0);
        constraints.gridy = 1;
        centerPanel.add(middleCheckBox, constraints);
        constraints.gridy = 2;
        constraints.insets = new Insets(0,0,10,0);
        centerPanel.add(harmonyCheckBox, constraints);

        // JOUEURS

        constraints.insets = new Insets(0, 0,0,0);
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.weightx = 1;
        JLabel playerText = new JLabel("Joueurs :");
        playerText.setFont(KingDominoDesign.getInstance().textFont.deriveFont(Font.BOLD));
        playerText.setForeground(KingDominoDesign.RED);
        centerPanel.add(playerText, constraints);

        JPanel playerBtnPanel = new JPanel();
        playerBtnPanel.setLayout(new BoxLayout(playerBtnPanel, BoxLayout.X_AXIS));

        class AddPlayerListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.addPlayer();
            }
        }
        addPlayerButton.addActionListener( new AddPlayerListener() );

        class RemovePlayerListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.removePlayer();
            }
        }
        removePlayerButton.addActionListener( new RemovePlayerListener() );

        playerBtnPanel.add(addPlayerButton);
        playerBtnPanel.add(removePlayerButton);

        constraints.weightx = 0;
        constraints.gridx = 1;
        centerPanel.add(playerBtnPanel, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.weightx = 1;
        constraints.insets = new Insets(10,0,0,0);
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
        centerPanel.add(playersPanel, constraints);

        constraints.weighty = 1;
        centerPanel.add(new JPanel(), constraints);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // JOUER

        JButton playButton = new KingButton("Jouer !");
        playButton.setFont(KingDominoDesign.getInstance().textFont.deriveFont(KingDominoDesign.textMd).deriveFont(Font.BOLD));
        class PlayButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.createGame(StartMenu.this);
            }
        }
        playButton.addActionListener( new PlayButtonListener());

        mainPanel.add(playButton, BorderLayout.PAGE_END);

        // PARAMETRES ET AFFICHAGE :

        add(mainPanel);

        setSize(600, 540);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("KingDomino - Menu principal");
        setVisible(true);

    }

    public boolean isMiddle() {
        return middleCheckBox.isSelected();
    }

    public boolean isHarmony() {
        return harmonyCheckBox.isSelected();
    }

    public void refreshPlayers(Stack<PlayerEditor> players) {
        playersPanel.removeAll();
        int i = 0;
        for (PlayerEditor p:players) {
            p.setPlayerColor(Colors.values()[i]);
            i++;
            playersPanel.add(p);
        }

        playersPanel.setMaximumSize( playersPanel.getPreferredSize() );

        this.getContentPane().validate();
        this.getContentPane().repaint();

        addPlayerButton.setEnabled(controller.canAddPlayer());
        removePlayerButton.setEnabled(controller.canRemovePlayer());
    }

}
