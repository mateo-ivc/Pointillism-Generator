package artcreator.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import artcreator.creator.CreatorFacade;
import artcreator.creator.port.Creator;
import artcreator.creator.impl.TemplateGenerator;
import artcreator.domain.DomainFacade;
import artcreator.domain.impl.ColorConfig;
import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;
import artcreator.gui.panels.CheckImagePanel;
import artcreator.gui.panels.EditorPanel;
import artcreator.gui.panels.SelectImagePanel;
import artcreator.gui.utils.PaperFormatEnum;
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
    private ColorConfig colorConfig;

    private BufferedImage image;
    private EditorPanel editorPanel;

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
                loadImage();
                if (this.image != null) {
                    StateMachineFacade.FACTORY.stateMachine().setState(State.S.EDIT_PARAMETERS);
                }
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
            case "REVERT_TO_IMAGE_SELECTION":
                StateMachineFacade.FACTORY.stateMachine().setState(State.S.SELECT_IMAGE);
                break;
            case "PRINT_TEMPLATE":
                CreatorFacade.FACTORY.generator().savePrintableDocument(DomainFacade.FACTORY.configService().getCurrentConfig(), image);
            // Add cases for other actions
            default:
                break;
        }
    }

    public void update(State newState) {
        /* modify controller or view if necessary */
        switch (newState) {
            case State.S.SELECT_IMAGE:
                break;
            case State.S.EDIT_PARAMETERS:
                List<Color> colorPalette = generateColorPallete(4);
                saveColorPalette(colorPalette);
                editorPanel.setColorPalette(colorPalette);
                break;
            case State.S.CHECK_IMAGE:

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + newState);
        }

    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void generatePrintableDocument() {

    }

    public BufferedImage generatePreview() {
        if (this.image != null) {
            return CreatorFacade.FACTORY.generator().generatePreview(DomainFacade.FACTORY.configService().getCurrentConfig(), image);
        }
        return new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
    }

    public List<Color> generateColorPallete(int size) {
        if (this.image != null) {
            return CreatorFacade.FACTORY.generator().quantizeImage(image, size);
        }
        return null;
    }

    public void loadImage() {
        this.image = DomainFacade.FACTORY.imageLoadingService().loadImage(myView);
    }

    public void validatePinSize(String value) {
        float f = Float.parseFloat(value);
        DomainFacade.FACTORY.configService().getCurrentConfig().setPinDiameter(f);
    }

    public void validatePinDistance(String value) {
        float f = Float.parseFloat(value);
        System.out.println("Set pin distance to: " + f);
        DomainFacade.FACTORY.configService().getCurrentConfig().setPinDistance(f);
    }

    public void loadConfigFromFile() {

    }

    public void saveConfigToFile() {

    }

    public void loadColorPalette() {

    }

    public void autoGenerateColorPalette() {

    }

    public void saveColorPalette(List<Color> colorPalette) {
        //todo: entweder am Anfag direkt ein ColorPalette Objekt erstelle oder nur mit List<Color> arbeiten
        //DomainFacade.FACTORY.configService().getCurrentConfig().setColorPalette(colorPalette);
    }

    public TemplateConfig getTemplateConfig() {
        return DomainFacade.FACTORY.configService().getCurrentConfig();
    }

    public void setPartialTemplateFormat(PaperFormatEnum format) {
        DomainFacade.FACTORY.configService().getCurrentConfig().setPartialTemplateFormat(format);
    }

    public void setEditorPanel(EditorPanel editorPanel) {
        this.editorPanel = editorPanel;
    }

}
