package artcreator.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanelComponent extends JPanel {
    private BufferedImage image;
    int width, height;

    public ImagePanelComponent(int width, int height) {
    this.width = width;
    this.height = height;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0,400,400, this);
        }
    }
}
