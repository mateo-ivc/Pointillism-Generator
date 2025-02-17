package artcreator.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ColorPaletteComponent extends JPanel {
    private List<Color> colors; // Store colors dynamically

    public ColorPaletteComponent(Collection<Color> colors) {
        this.colors = new ArrayList<>(colors); // Store as a mutable list
        this.setLayout(null);
        updatePalette(); // Initial rendering
    }

    // Method to update the color palette dynamically
    public void updatePalette() {
        this.removeAll(); // Clear previous components

        int xOffset = 0;
        for (Color color : colors) {
            JPanel colorPanel = new JPanel(null);
            colorPanel.setBackground(color);
            colorPanel.setBounds(xOffset, 0, 32, 32);
            add(colorPanel);
            xOffset += 32;
        }

        // Button to trigger external updates (e.g., changing the palette)
        JButton selectColorPaletteButton = new JButton("...");
        selectColorPaletteButton.setBounds(xOffset + 32, 0, 32, 32);
        add(selectColorPaletteButton);

        // Repaint UI
        revalidate();
        repaint();
    }

    // Method to set new colors
    public void setColors(Collection<Color> newColors) {
        this.colors.clear();
        this.colors.addAll(newColors);
        updatePalette();
    }
}
