package v1.views;

import v1.models.Colors;
import javax.swing.*;

public class PlayerEditor extends JPanel {

    private final JTextField name;
    private final JComboBox<Colors> color;

    public PlayerEditor() {

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        name = new JTextField("", 10);
        color = new JComboBox<>(Colors.values());

        add(name);
        add(color);

    }

    public String getPlayerName() {
        return name.getText();
    }

    public Colors getPlayerColor() {
        return (Colors)color.getSelectedItem();
    }

}
