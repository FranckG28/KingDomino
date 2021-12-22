package v1.views;

import v1.controllers.GameController;
import v1.models.GameObserver;
import v1.models.Kingdom;
import v1.models.Player;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame implements GameObserver {

    private GameController controller;

    private JPanel drawPanel = new JPanel();

    private JLabel gameLabel = new JLabel("Instruction");
    private JLabel playerLabel = new JLabel("Joueur");

    public GameView(GameController controller, v1.models.Game game) {

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

    public void reactGame(v1.models.Game game) {
        // REFRESH CURRENT PLAYER + INSTRUCTIONS

        // REFRESH DRAW

    }

}
