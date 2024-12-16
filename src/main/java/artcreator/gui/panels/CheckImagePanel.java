package artcreator.gui.panels;

import artcreator.gui.Controller;
import artcreator.gui.components.ImagePanelComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CheckImagePanel extends JPanel {
    public CheckImagePanel(Controller controller){
        setLayout(null);
        ImagePanelComponent imagePanel = new ImagePanelComponent(400);
        imagePanel.setImage(controller.getImage());
        imagePanel.setBorder(new LineBorder(Color.BLACK, 5));
        imagePanel.setBounds(32,0, 400, 400);
        add(imagePanel);

        JLabel checkParametersLabel = new JLabel("Parameterüberprüfung");
        checkParametersLabel.setBounds(500, 0, 400, 50);
        checkParametersLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(checkParametersLabel);

        JPanel divider = new JPanel();
        divider.setBounds(500, 48, 400, 2);
        divider.setBackground(Color.BLACK); // Black color line
        add(divider);

        JLabel pinDistance = new JLabel("Abstand: " + controller.getTemplateConfig().getPinDistance());
        pinDistance.setBounds(500, 64, 200, 32);
        pinDistance.setFont(new Font("Arial", Font.BOLD, 20));
        add(pinDistance);

        JLabel pinDiameter = new JLabel("Durchmesser: " + controller.getTemplateConfig().getPinDiameter());
        pinDiameter.setBounds(700, 64, 200, 32);
        pinDiameter.setFont(new Font("Arial", Font.BOLD, 20));
        add(pinDiameter);

        JLabel mirrored = new JLabel("Spiegelung: " + (controller.getTemplateConfig().isMirrored() ? "Ja" : "Nein"));
        mirrored.setBounds(500, 96, 200, 32);
        mirrored.setFont(new Font("Arial", Font.BOLD, 20));
        add(mirrored);

        JLabel imageWidth = new JLabel("Bildbreite: " + controller.getTemplateConfig().getTemplateWidth());
        imageWidth.setBounds(500, 128, 200, 32);
        imageWidth.setFont(new Font("Arial", Font.BOLD, 20));
        add(imageWidth);

        JLabel imageHeight = new JLabel("Bildhöhe: " + controller.getTemplateConfig().getTemplateHeight());
        imageHeight.setBounds(700, 128, 200, 32);
        imageHeight.setFont(new Font("Arial", Font.BOLD, 20));
        add(imageHeight);

        JLabel templatesLabel = new JLabel("Vorlagen");
        templatesLabel.setBounds(500, 192, 200, 32);
        templatesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(templatesLabel);

        JPanel divider2 = new JPanel();
        divider2.setBounds(500, 240, 400, 2);
        divider2.setBackground(Color.BLACK); // Black color line
        add(divider2);
        
        JButton materialAndColorList = new JButton("Material- / Farbliste");
        materialAndColorList.setBounds(500, 256, 200, 32);
        materialAndColorList.setFont(new Font("Arial", Font.BOLD, 20));
        add(materialAndColorList);

        JButton colorLegend = new JButton("Farblegende");
        colorLegend.setBounds(500, 288, 200, 32);
        colorLegend.setFont(new Font("Arial", Font.BOLD, 20));
        add(colorLegend);

        JButton template = new JButton("Vorlage");
        template.setBounds(500, 320, 200, 32);
        template.setFont(new Font("Arial", Font.BOLD, 20));
        add(template);

    }
}
