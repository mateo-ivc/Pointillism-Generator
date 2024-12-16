package artcreator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import artcreator.creator.CreatorFacade;
import artcreator.creator.CreatorFactory;
import artcreator.creator.port.Creator;
import artcreator.creator.impl.TemplateGenerator;
import artcreator.domain.impl.ColorConfig;
import artcreator.domain.impl.Template;
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
        this.templateConfig = new TemplateConfig();
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
            case "CONTINUE_TO_VALIDATION":
                StateMachineFacade.FACTORY.stateMachine().setState(State.S.CHECK_IMAGE);
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

    public void validatePinSize(String value) {
        float f = Float.parseFloat(value);
        this.templateConfig.setPinDiameter(f);
    }

    public void validatePinDistance(String value) {
        float f = Float.parseFloat(value);
        System.out.println("Set pin distance to: " + f);
        this.templateConfig.setPinDistance(f);
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

    public TemplateConfig getTemplateConfig(){
        return templateConfig;
    }

}
