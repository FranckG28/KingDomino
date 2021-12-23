package v2.views;

import v2.models.MovableInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PositionArrows extends JPanel {

    public JButton leftButton = new KingButton("◀");
    public JButton rightButton = new KingButton("▶");
    public JButton upButton = new KingButton("▲");
    public JButton downButton = new KingButton("▼");

    public PositionArrows() {

        setLayout(new GridBagLayout());
        setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 1;
        add(upButton, gbc);

        gbc.gridy = 2;
        add(downButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(leftButton, gbc);

        gbc.gridx = 2;
        add(rightButton, gbc);

    }

    public JButton getLeftButton() {
        return leftButton;
    }

    public JButton getRightButton() {
        return rightButton;
    }

    public JButton getDownButton() {
        return downButton;
    }

    public JButton getUpButton() {
        return upButton;
    }

    public void bindAction(MovableInterface controller) {
        class LeftButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.moveLeft();
            }
        }
        class RightButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.moveRight();
            }
        }
        class UpButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.moveUp();
            }
        }
        class DownButtonListener implements ActionListener {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                controller.moveDown();
            }
        }
        leftButton.addActionListener(new LeftButtonListener());
        rightButton.addActionListener(new RightButtonListener());
        upButton.addActionListener(new UpButtonListener());
        downButton.addActionListener(new DownButtonListener());

    }


}
