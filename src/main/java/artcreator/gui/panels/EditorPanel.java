package artcreator.gui.panels;

import artcreator.gui.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditorPanel extends JPanel {
    private Controller controller;

    public EditorPanel(Controller controller) {
        this.controller = controller;
        ImagePanel leftPanel = new ImagePanel(300);

        leftPanel.setBorder(new LineBorder(Color.BLACK, 5));


        add(leftPanel, BorderLayout.WEST);

        // Right panel will hold various controls, but avoid stretching
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));


        // Create parameter panel
        JPanel parameterPanel = new JPanel();
        parameterPanel.setLayout(new BoxLayout(parameterPanel, BoxLayout.Y_AXIS));
        parameterPanel.add(createLabeledField("Steckelement"));
        parameterPanel.add(createLabeledField("Abstand"));
        parameterPanel.add(createLabeledField("Durchmesser"));

        parameterPanel.add(Box.createVerticalStrut(10)); // Adding vertical spacing

        rightPanel.add(parameterPanel);

        // Mirror options (checkbox)
        JPanel mirrorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mirrorPanel.add(new JLabel("Spiegelung"));
        mirrorPanel.add(new JCheckBox(""));
        rightPanel.add(mirrorPanel);

        rightPanel.add(Box.createVerticalStrut(10));  // Add spacing

        // Color palette
        JPanel colorPalettePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        colorPalettePanel.add(new JLabel("Farbpalette"));
        rightPanel.add(colorPalettePanel);

        // Image size fields
        rightPanel.add(createLabeledField("Bildbreite"));
        rightPanel.add(createLabeledField("Bildhöhe"));

        // Paper format combo box
        rightPanel.add(new JLabel("Papierformat"));
        rightPanel.add(new JComboBox<>(new String[]{"A4", "A3", "A5"})); // Example paper formats

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(new JButton("Laden"));
        buttonPanel.add(new JButton("Speichern"));
        rightPanel.add(buttonPanel);

        // Wrap rightPanel in a JScrollPane to allow vertical scrolling if the content is too long

        rightPanel.setPreferredSize(new Dimension(400, 350));  // Set a max height for the scroll pane

        add(rightPanel, BorderLayout.CENTER);  // Add the scroll pane containing the right panel

    }

    private JPanel createLabeledField(String labelText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(labelText));
        JTextField field = new JTextField(5);
        // todo: find way to send whole value on state change
        field.setActionCommand(labelText);
        panel.add(field);
        return panel;
    }

}
