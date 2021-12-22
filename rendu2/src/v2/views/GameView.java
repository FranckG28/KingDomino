package v2.views;

import v2.models.Game;
import v2.controllers.GameController;
import v2.models.GameObserver;
import v2.models.Kingdom;
import v2.models.Player;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame implements GameObserver {

    private GameController controller;

    private JPanel drawPanel = new JPanel();

    private JLabel gameLabel = new JLabel("Instruction");
    private JLabel playerLabel = new JLabel("Joueur");

    public GameView(GameController controller, v2.models.Game game) {

        this.controller = controller;

        // CONTENEUR PRINCIPAL :

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // CREATION DES KINGDOMVIEWS QUI OBSERVENT LEURS ROYAUMES

        JPanel kingdomGrid = new JPanel(new GridLayout(2,2));
        for (Player p:game.getAllPlayers()) {
            Kingdom kingdom = p.getKingdom();
            KingdomView kview = new KingdomView(kingdom);
            kingdom.addObserver(kview);
            kingdomGrid.add(kview);
        }

        // PLACEMENTS DES LABELS




        // PARAMETRES ET OUVERTURE

        setSize(1200, 800);

        setTitle("KingDomino - Jeu");
        setVisible(true);

    }

    public void reactGame(Game game) {
        // REFRESH CURRENT PLAYER + INSTRUCTIONS

        // REFRESH DRAW

    }

}
