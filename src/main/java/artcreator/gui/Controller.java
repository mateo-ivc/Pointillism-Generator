package artcreator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.CompletableFuture;

import javax.swing.JButton;

import artcreator.creator.port.Creator;
import artcreator.domain.impl.TemplateGenerator;
import artcreator.domain.impl.ColorConfig;
import artcreator.domain.port.Template;
import artcreator.domain.impl.TemplateConfig;
import artcreator.statemachine.port.Observer;
import artcreator.statemachine.port.State;
import artcreator.statemachine.port.Subject;

public class Controller implements ActionListener, Observer {

    @SuppressWarnings("unused")
    private CreatorFrame myView;
    private Creator myModel;
    private Subject subject;

    private TemplateGenerator templateGenerator;
    private Template previewTemplate;
    private TemplateConfig templateConfig;
    private ColorConfig colorConfig;

    private File image;

    public Controller(CreatorFrame view, Subject subject, Creator model) {
        this.myView = view;
        this.myModel = model;
        this.subject = subject;
        this.subject.attach(this);
    }

    public synchronized void actionPerformed(ActionEvent e) {

        /* read input */
        String str = (((JButton) e.getSource()).getText());

        CompletableFuture.runAsync(() -> this.myModel.sysop(str));
    }

    public void update(State newState) {
        /* modify controller or view if necessary */
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
