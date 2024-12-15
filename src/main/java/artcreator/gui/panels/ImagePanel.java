package artcreator.gui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private BufferedImage image;

    // Constructor to set maximum height
    public ImagePanel(int maxHeight) {
        // Set the preferred size with a default width (e.g., 400px)
        setPreferredSize(new Dimension(400, maxHeight));
        setMaximumSize(new Dimension(400, maxHeight)); // Restrict maximum height
        setMinimumSize(new Dimension(400, maxHeight)); // Restrict minimum height
    }

    // Setter to dynamically set the image
    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();  // Repaint the panel when the image is updated
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Draw the image at (0, 0) of the panel. It will scale if needed.
            g.drawImage(image, 0, 0, this);
        }
    }
}
