package v2.views;

import v2.models.Game;
import v2.controllers.GameController;
import v2.models.GameObserver;
import v2.models.Kingdom;
import v2.models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame implements GameObserver {

    private GameController controller;

    private JLabel gameLabel = new JLabel("Instruction");
    private JLabel playerLabel = new JLabel("Joueur");

    private DrawView oldDraw = new DrawView();
    private DrawView actualDraw = new DrawView();

    public GameView(GameController controller, Game game) {

        this.controller = controller;


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
        title.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.getInstance().textMd));
        header.add(title);
        header.add(Box.createHorizontalGlue());

        KingButton menuBtn = new KingButton("Menu principal");
        class MenuButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                System.out.println("Menu principal");
                // TODO: Ouvrir le menu principal, quitter la partie
            }
        }
        menuBtn.addActionListener(new MenuButtonListener());
        header.add(menuBtn);

        mainContainer.add(header, BorderLayout.PAGE_START);


        // CONTENU

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints contentGbc = new GridBagConstraints();

        contentPanel.add(oldDraw, contentGbc);
        contentGbc.gridx = 1;
        contentPanel.add(actualDraw, contentGbc);

        JPanel gamePanel = new JPanel();
        gamePanel.setOpaque(false);
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        playerLabel.setFont(KingDominoDesign.getInstance().textFont.deriveFont(KingDominoDesign.getInstance().textMd));
        playerLabel.setForeground(KingDominoDesign.RED);
        gamePanel.add(playerLabel);

        gameLabel.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.getInstance().textLg));
        gameLabel.setForeground(KingDominoDesign.YELLOW);
        gamePanel.add(gameLabel);

        contentGbc.gridx = 2;
        contentGbc.gridwidth = 3;
        contentPanel.add(gamePanel, contentGbc);

        mainContainer.add(contentPanel, BorderLayout.CENTER);


        // CREATION DES KINGDOMVIEWS QUI OBSERVENT LEURS ROYAUMES

        java.util.List<Player> playerList = game.getAllPlayers();
        JPanel kingdomGrid = new JPanel();
        kingdomGrid.setLayout(new BoxLayout(kingdomGrid, BoxLayout.X_AXIS));

        kingdomGrid.setAlignmentX(Component.CENTER_ALIGNMENT);
        kingdomGrid.setOpaque(false);
        for (Player p:playerList) {
            Kingdom kingdom = p.getKingdom();
            KingdomView kview = new KingdomView(kingdom);
            kingdom.addObserver(kview);
            kingdomGrid.add(kview);
        }
        
        mainContainer.add(kingdomGrid, BorderLayout.PAGE_END);


        // PARAMETRES ET OUVERTURE

        add(mainContainer);

        setSize(1200, 800);

        setTitle("KingDomino - Jeu");
        setVisible(true);

    }

    public void reactGame(Game game) {
        // REFRESH CURRENT PLAYER + INSTRUCTIONS

        // REFRESH DRAW

    }

}
