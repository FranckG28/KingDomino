package views;

import models.Colors;
import javax.swing.*;

public class PlayerEditor extends JPanel {

    private final JTextField name;
    private final JComboBox<Colors> color;

    public PlayerEditor() {

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        name = new JTextField();
        color = new JComboBox<>(Colors.values());

        container.add(name);
        container.add(color);

    }

    public String getPlayerName() {
        return name.getText();
    }

    public Colors getPlayerColor() {
        return (Colors)color.getSelectedItem();
    }

}
