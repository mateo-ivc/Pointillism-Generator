package artcreator.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanelComponent extends JPanel {
    private BufferedImage image;


    public ImagePanelComponent() {
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }
}
