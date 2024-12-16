package artcreator.gui.panels;

import artcreator.gui.Controller;
import artcreator.gui.components.ColorPaletteComponent;
import artcreator.gui.components.ImagePanelComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.border.LineBorder;

public class EditorPanel extends JPanel {
    private Controller controller;

    public EditorPanel(Controller controller) {
        this.controller = controller;
        setLayout(null);
        ImagePanelComponent imagePanel = new ImagePanelComponent();
        imagePanel.setImage(controller.getImage());

        imagePanel.setBorder(new LineBorder(Color.BLACK, 5));
        imagePanel.setBounds(32,0, 400, 400);
        add(imagePanel);

        JLabel pinSettingsLabel = new JLabel("Steckelemente");
        pinSettingsLabel.setBounds(500, 0, 200, 50);
        pinSettingsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(pinSettingsLabel);

        JPanel divider = new JPanel();
        divider.setBounds(500, 48, 400, 2); // x, y, width, height
        divider.setBackground(Color.BLACK); // Black color line
        add(divider);

        JLabel pinSizeLabel = new JLabel("Durchmesser");
        pinSizeLabel.setBounds(500, 64, 200, 32);
        pinSizeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(pinSizeLabel);

        JTextField pinSizeInputField = new JTextField(
                String.valueOf(controller.getTemplateConfig().getPinDiameter())
        );
        pinSizeInputField.setBounds(700, 64, 200, 32);
        pinSizeInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                controller.validatePinSize(pinSizeInputField.getText());
            }
        });
        add(pinSizeInputField);

        JLabel pinDistanceLabel = new JLabel("Abstand");
        pinDistanceLabel.setBounds(500, 96, 200, 32);
        pinDistanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(pinDistanceLabel);

        JTextField pinDistanceInputField = new JTextField(String.valueOf(controller.getTemplateConfig().getPinDistance()));
        pinDistanceInputField.setBounds(700, 96, 200, 32);
        pinDistanceInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                controller.validatePinDistance(pinDistanceInputField.getText());
            }
        });
        add(pinDistanceInputField);

        JPanel divider2 = new JPanel();
        divider2.setBounds(500, 144, 400, 2); // x, y, width, height
        divider2.setBackground(Color.BLACK); // Black color line
        add(divider2);

        JLabel mirroredLabel = new JLabel("Spiegelung");
        mirroredLabel.setBounds(500, 160, 200, 32);
        mirroredLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(mirroredLabel);

        JCheckBox mirroredCheckBox = new JCheckBox((Icon) null, controller.getTemplateConfig().isMirrored());
        mirroredCheckBox.setBounds(700, 160, 100, 32);
        mirroredCheckBox.addChangeListener(e -> {
            controller.getTemplateConfig().setMirrored(mirroredCheckBox.isSelected());
        });
        add(mirroredCheckBox);

        JPanel divider3 = new JPanel();
        divider3.setBounds(500, 204, 400, 2); // x, y, width, height
        divider3.setBackground(Color.BLACK); // Black color line
        add(divider3);

        JLabel colorPaletteLabel = new JLabel("Farbpalette");
        colorPaletteLabel.setBounds(500, 220, 200, 32);
        colorPaletteLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(colorPaletteLabel);

        ColorPaletteComponent colorPalette = new ColorPaletteComponent(List.of(Color.RED, Color.GREEN, Color.PINK));
        colorPalette.setBounds(700, 220, 200, 32);
        add(colorPalette);

        JPanel divider4 = new JPanel();
        divider4.setBounds(500, 268, 400, 2); // x, y, width, height
        divider4.setBackground(Color.BLACK); // Black color line
        add(divider4);

        JLabel imageWidthLabel = new JLabel("Bildbreite");
        imageWidthLabel.setBounds(500, 284, 200, 32);
        imageWidthLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(imageWidthLabel);

        JTextField imageWidthInputField = new JTextField("10");
        imageWidthInputField.setBounds(700, 284, 200, 32);
        add(imageWidthInputField);

        JLabel imageHeightLabel = new JLabel("Bildhöhe");
        imageHeightLabel.setBounds(500, 316, 200, 32);
        imageHeightLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(imageHeightLabel);

        JTextField imageHeightInputField = new JTextField("10");
        imageHeightInputField.setBounds(700, 316, 200, 32);
        add(imageHeightInputField);

        JPanel divider5 = new JPanel();
        divider5.setBounds(500, 364, 400, 2); // x, y, width, height
        divider5.setBackground(Color.BLACK); // Black color line
        add(divider5);

        JLabel printFormatLabel = new JLabel("Papierformat");
        printFormatLabel.setBounds(500, 380, 200, 32);
        printFormatLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(printFormatLabel);

        JComboBox<String> formatSelectionDropdown = new JComboBox<>(new String[]{"A4", "A3", "A2"});
        formatSelectionDropdown.setBounds(700, 380, 200, 32);
        add(formatSelectionDropdown);

        JPanel divider6 = new JPanel();
        divider6.setBounds(500, 428, 400, 2); // x, y, width, height
        divider6.setBackground(Color.BLACK); // Black color line
        add(divider6);

        JButton loadConfigButton = new JButton("Laden");
        loadConfigButton.setBounds(500, 444, 100, 32);
        loadConfigButton.addActionListener(e -> controller.loadConfigFromFile());
        add(loadConfigButton);

        JButton saveConfigButton = new JButton("Speichern");
        saveConfigButton.setBounds(700, 444, 100, 32);
        saveConfigButton.addActionListener(e -> controller.saveConfigToFile());
        add(saveConfigButton);

        JButton revertToImageSelectionButton = new JButton("Zurück zur Bildauswahl");
        revertToImageSelectionButton.setBounds(32, 494, 400, 32);
        revertToImageSelectionButton.setActionCommand("REVERT_TO_IMAGE_SELECTION");
        revertToImageSelectionButton.addActionListener(controller);
        add(revertToImageSelectionButton);


        JButton continueToValidationButton = new JButton("Weiter zur Überprüfung");
        continueToValidationButton.setBounds(500, 494, 400, 32);
        continueToValidationButton.setActionCommand("CONTINUE_TO_VALIDATION");
        continueToValidationButton.addActionListener(controller);
        add(continueToValidationButton);

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
