package v2.views;

import v2.controllers.GameController;
import v2.models.Game;
import v2.models.GameObserver;
import v2.models.Kingdom;
import v2.models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame implements GameObserver {

    private final JLabel gameLabel = new JLabel("Instruction");
    private final JLabel playerLabel = new JLabel("Joueur");

    private final DrawView actualDraw;

    private final JPanel gamePanel = new JPanel();
    private final JPanel controlPanel = new JPanel();

    public GameView(GameController controller, Game game) {

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

        KingButton menuBtn = new KingButton("Quitter la partie");
        class MenuButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.exitGame();
            }
        }
        menuBtn.addActionListener(new MenuButtonListener());
        header.add(menuBtn);

        mainContainer.add(header, BorderLayout.PAGE_START);


        // CONTENU

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints contentGbc = new GridBagConstraints();

        // Pioches
        DrawView oldDraw = new DrawView("Pioche précédente", game.getDraw().getSize(), controller);
        actualDraw = new DrawView("Pioche actuelle", game.getDraw().getSize(), controller);

        oldDraw.setPreferredSize(new Dimension(150,300));
        game.getLastDraw().addObserver(oldDraw);

        actualDraw.setPreferredSize(new Dimension(250,300));
        game.getDraw().addObserver(actualDraw);

        contentGbc.weighty = 1;
        contentPanel.add(oldDraw, contentGbc);
        contentGbc.gridx = 1;
        contentGbc.insets = new Insets(0,20,0,20);
        contentPanel.add(actualDraw, contentGbc);
        contentGbc.insets = new Insets(0,0,0,0);

        // Instructions
        gamePanel.setOpaque(false);
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        playerLabel.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.textMd).deriveFont(Font.BOLD));
        playerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        gamePanel.add(playerLabel);

        gameLabel.setFont(KingDominoDesign.getInstance().titleFont.deriveFont(KingDominoDesign.textLg));
        gameLabel.setForeground(KingDominoDesign.YELLOW);
        gameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        gamePanel.add(gameLabel);

        controlPanel.setOpaque(false);
        controlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        gamePanel.add(controlPanel);

        contentGbc.gridx = 2;
        contentGbc.weightx = 1;
        contentPanel.add(gamePanel, contentGbc);

        contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        mainContainer.add(contentPanel, BorderLayout.CENTER);


        // CREATION DES KINGDOMVIEWS QUI OBSERVENT LEURS ROYAUMES

        java.util.List<Player> playerList = game.getAllPlayers();
        JPanel kingdomGrid = new JPanel();
        kingdomGrid.setLayout(new BoxLayout(kingdomGrid, BoxLayout.X_AXIS));

        kingdomGrid.setAlignmentX(Component.CENTER_ALIGNMENT);
        kingdomGrid.setOpaque(false);
        for (Player p:playerList) {
            Kingdom kingdom = p.getKingdom();
            KingdomView kview = new KingdomView(kingdom, controller);
            kingdom.addObserver(kview);
            kingdomGrid.add(kview);
            game.addObserver(kview);
        }

        mainContainer.add(kingdomGrid, BorderLayout.PAGE_END);


        // PARAMETRES ET OUVERTURE

        add(mainContainer);

        setResizable(false);
        setSize(1200, 800);
        setTitle("KingDomino - Jeu");
        setVisible(true);

    }

    public void reactGame(Game game) {

        // Actualisation du joueur actuel
        playerLabel.setText(game.getCurrentPlayer().getName()+",");
        playerLabel.setForeground(KingDominoDesign.getColor(game.getCurrentPlayer().getColor()));
        gamePanel.updateUI();

    }

    public void setAction(String text, JPanel content) {

        // Modification de l'instruction
        gameLabel.setText(text);

        // Modification de l'action
        controlPanel.removeAll();
        controlPanel.add(content);

        // Actualisation de la fenêtre
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }

    public DrawView getDrawView() {
        return actualDraw;
    }

}
