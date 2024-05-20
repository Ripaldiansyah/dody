package component;

import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import com.formdev.flatlaf.FlatClientProperties;

public class ButtonCustom extends JButton {

    public ButtonCustom(String label, Icon icon, ActionListener actionListener) {
        super(label);
        setIcon(icon);
        putClientProperty(FlatClientProperties.STYLE, ""

                + "margin:4,6,4,6;"
                + "arc:20;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");

        addActionListener(actionListener);
    }

    public JButton getButton() {
        return this;
    }
}
