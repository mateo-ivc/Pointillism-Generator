package artcreator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;

import artcreator.creator.port.Creator;
import artcreator.domain.impl.TemplateGenerator;
import artcreator.domain.impl.ColorConfig;
import artcreator.domain.port.Template;
import artcreator.domain.impl.TemplateConfig;
import artcreator.statemachine.StateMachineFacade;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;

public class Controller implements ActionListener, Observer {


    private CreatorFrame myView;
    private Creator myModel;
    private Subject subject;

    private TemplateGenerator templateGenerator;
    private Template previewTemplate;
    private TemplateConfig templateConfig;
    private ColorConfig colorConfig;

    private BufferedImage image;

    public Controller(CreatorFrame view, Subject subject, Creator model) {
        this.myView = view;
        this.myModel = model;
        this.subject = subject;
        this.subject.attach(this);
    }

    public synchronized void actionPerformed(ActionEvent e) {
        /* read input */
        String command = e.getActionCommand();
        System.out.println("something has been triggered");
        // Check the command and perform corresponding actions
        switch (command) {
            case "SELECT_IMAGE":
                this.image = loadImage();
                StateMachineFacade.FACTORY.stateMachine().setState(State.S.EDIT_PARAMETERS);
                break;
            case "GenerateTemplate":
//                generateTemplate();
                break;
            case "SaveConfig":
//                saveConfigToFile();
                break;
            // Add cases for other actions
            default:
                break;
        }
    }

    public void update(State newState) {
        /* modify controller or view if necessary */
    }

    private BufferedImage loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".jpeg");
                }
            }

            @Override
            public String getDescription() {
                return "JPG Images (*.jpg)";
            }
        });
        int result = fileChooser.showOpenDialog(this.myView);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                BufferedImage picture = ImageIO.read(file);
                return picture;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        }
        return null;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void generatePrintableDocument() {

    }

    public void validatePinSize() {

    }

    public void validatePinDistance() {

    }

    public void loadConfigFromFile() {

    }

    public void saveConfigToFile() {

    }

    public void loadColorPalette() {

    }

    public void autoGenerateColorPalette() {

    }

    public void saveColorPalette() {

    }

}
