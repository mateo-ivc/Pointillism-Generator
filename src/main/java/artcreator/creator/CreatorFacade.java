package artcreator.creator;

import artcreator.creator.impl.*;
import artcreator.creator.port.Creator;
import artcreator.creator.port.IGenerator;
import artcreator.creator.port.IImageLoader;
import artcreator.domain.DomainFactory;
import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.StateMachine;
import artcreator.statemachine.port.State.S;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CreatorFacade implements CreatorFactory, Creator, IGenerator, IImageLoader {

    private CreatorImpl creator;
    private StateMachine stateMachine;

    private PreviewGeneratorImpl previewGenerator;

    private TemplateGenerator templateGenerator;

    @Override
    public Creator creator() {
        return init();
    }

    @Override
    public IGenerator generator() {
        return init();
    }

    @Override
    public IImageLoader imageLoader() {
        return init();
    }

    @Override
    public synchronized void sysop(String str) {
        if (this.stateMachine.getState().isSubStateOf(S.CREATE_TEMPLATE /* choose right state*/))
            this.creator.sysop(str);
    }

    @Override
    public void savePrintableDocument(TemplateConfig config, BufferedImage input) {
        BufferedImage bufferedImage = templateGenerator.generateTemplateImage(config, input);

        JFrame parentFrame = new JFrame();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                ImageIO.write(bufferedImage, "jpg", fileToSave);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
    }

    @Override
    public BufferedImage generatePreview(TemplateConfig config, BufferedImage input) {
        return new PreviewImageGeneratorImpl().generatePreviewFromTemplate(previewGenerator.generateTemplate(config, input));
    }

    @Override
    public List<Color> quantizeImage(BufferedImage image, int n) {
        return MedianCutColorGenerator.extractColors(image, n);
    }

    private CreatorFacade init() {
        if (this.creator == null) {
            this.stateMachine = StateMachineFactory.FACTORY.stateMachine();
            this.creator = new CreatorImpl(stateMachine, DomainFactory.FACTORY.domain());
            this.previewGenerator = new PreviewGeneratorImpl();
            this.templateGenerator = new TemplateGenerator();
        }
        return this;
    }

    @Override
    public BufferedImage loadImage(File file) {
        //todo: auslagern in eigene klasse
        try {
            BufferedImage image = ImageIO.read(file);
            BufferedImage scaledImage;

            StateMachineFactory.FACTORY.stateMachine().setState(S.EDIT_PARAMETERS);

            return image;
        } catch (IOException e) {
            StateMachineFactory.FACTORY.stateMachine().setState(S.ERROR_STATE);
        }
        return null;
    }
}
