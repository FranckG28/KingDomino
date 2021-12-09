package views;


import controllers.GameCreator;

import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {

    private final JPanel playersFrame = new JPanel();

    private java.util.List<PlayerEditor> players;

    public StartMenu(GameCreator controller) {

        JPanel mainFrame = new JPanel();
        mainFrame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Kingdomino");
        mainFrame.add(title, BorderLayout.PAGE_START);

        add(mainFrame);

        setSize(400, 400);

        setTitle("KingDomino - Menu principal");
        setVisible(true);

    }

}
