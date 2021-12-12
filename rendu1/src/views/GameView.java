package views;

import controllers.GameController;
import models.*;

import javax.swing.*;

public class GameView extends JFrame implements GameObserver {

    private GameController controller;

    public GameView(GameController controller) {

        this.controller = controller;


        // PARAMETRE ET OUVERTURE

        setSize(800, 800);

        setTitle("KingDomino - Jeu");
        setVisible(true);

    }

    public void reactGame(Game game) {

    }

}
