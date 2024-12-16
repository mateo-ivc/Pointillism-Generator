package artcreator.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanelComponent extends JPanel {
    private BufferedImage image;
    int width;

    public ImagePanelComponent(int width) {
    this.width = width;

    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            // Calculate the height while maintaining the aspect ratio

            int height = (int) ((double) image.getHeight() / image.getWidth() * width);

            // Draw the image with the calculated dimensions
            g.drawImage(image, 0, 0, width, height, this);
        }
    }
}
