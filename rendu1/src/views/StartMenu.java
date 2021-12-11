package views;


import controllers.GameCreator;
import models.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class StartMenu extends JFrame {

    private final JPanel playersPanel = new JPanel();
    private final JButton addPlayerButton = new JButton("Ajouter un joueur");
    private final JButton removePlayerButton = new JButton("Supprimer un joueur");

    private final GameCreator controller;

    public StartMenu(GameCreator controller) {

        this.controller = controller;

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // HEADER

        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Kingdomino", SwingConstants.CENTER);
        Font font = new Font("Space Grotesk", Font.BOLD,30);
        title.setFont(font);
        mainPanel.add(title, BorderLayout.PAGE_START);

        // JOUEURS

        JPanel playersSection = new JPanel();
        playersSection.setLayout(new BoxLayout(playersSection, BoxLayout.Y_AXIS));

        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));

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

        playersSection.add(playersPanel);
        playersSection.add(playerBtnPanel);

        mainPanel.add(playersSection, BorderLayout.CENTER);

        // JOUER

        JButton playButton = new JButton("Jouer !");

        mainPanel.add(playButton, BorderLayout.PAGE_END);

        // PARAMETRES ET AFFICHAGE :

        add(mainPanel);

        setSize(400, 400);

        setTitle("KingDomino - Menu principal");
        setVisible(true);

    }

    public java.util.List<Player> getPlayers() {
        return null;
    }

    public boolean isMiddle() {
        return false;
    }

    public boolean isHarmony() {
        return false;
    }

    public void refreshPlayers(Stack<PlayerEditor> players) {
        playersPanel.removeAll();
        for (PlayerEditor p:players) {
            playersPanel.add(p);
            System.out.println("Player ajouté");
        }

        playersPanel.setMaximumSize( playersPanel.getPreferredSize() );

        this.getContentPane().validate();
        this.getContentPane().repaint();



        addPlayerButton.setEnabled(controller.canAddPlayer());
        removePlayerButton.setEnabled(controller.canRemovePlayer());
    }

}
