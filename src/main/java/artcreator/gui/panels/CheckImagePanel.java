package artcreator.gui.panels;

import artcreator.gui.Controller;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CheckImagePanel extends JPanel {
    public CheckImagePanel(Controller controller){
        setLayout(null);
        ImagePanel imagePanel = new ImagePanel(300);
        imagePanel.setImage(controller.getImage());
        imagePanel.setBorder(new LineBorder(Color.BLACK, 5));
        imagePanel.setBounds(32,0, 400, 400);
        add(imagePanel);

        JLabel checkParametersLabel = new JLabel("Parameterüberprüfung");
        checkParametersLabel.setBounds(500, 0, 400, 50);
        checkParametersLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(checkParametersLabel);

        JPanel divider = new JPanel();
        divider.setBounds(500, 48, 400, 2); // x, y, width, height
        divider.setBackground(Color.BLACK); // Black color line
        add(divider);

        JLabel pinDistanceLabel = new JLabel("Abstand: " + controller.getTemplateConfig().getPinDistance());
        pinDistanceLabel.setBounds(500, 64, 200, 32);
        pinDistanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(pinDistanceLabel);

    }
}
