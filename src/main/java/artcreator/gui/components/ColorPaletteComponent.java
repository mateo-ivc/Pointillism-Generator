package artcreator.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ColorPaletteComponent extends JPanel {

    public ColorPaletteComponent(Collection<Color> colors){
        this.setLayout(null);

        int xOffset = 0;
        for (Color color : colors) {
            JPanel colorPanel = new JPanel(null);
            colorPanel.setBackground(color);
            colorPanel.setBounds(xOffset, 0, 32, 32);
            add(colorPanel);
            xOffset+=32;
        }

        JButton selectColorPaletteButton = new JButton("...");
        selectColorPaletteButton.setBounds(xOffset + 32, 0, 32, 32);
        add(selectColorPaletteButton);

    }


}
