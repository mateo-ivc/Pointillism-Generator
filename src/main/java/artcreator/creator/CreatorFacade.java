package artcreator.creator;

import artcreator.creator.impl.CreatorImpl;
import artcreator.creator.impl.PreviewGenerator;
import artcreator.creator.impl.PreviewImageGenerator;
import artcreator.creator.impl.TemplateGenerator;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreatorFacade implements CreatorFactory, Creator, IGenerator, IImageLoader {

    private CreatorImpl creator;
    private StateMachine stateMachine;

    private PreviewGenerator previewGenerator;

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
    public Template generatePrintableDocument(TemplateConfig config, BufferedImage input) {
        return templateGenerator.generateTemplate(config, input);
    }

    @Override
    public BufferedImage generatePreview(TemplateConfig config, BufferedImage input) {
        return new PreviewImageGenerator().generatePreviewFromTemplate(previewGenerator.generateTemplate(config, input));
    }

    private CreatorFacade init() {
        if (this.creator == null) {
            this.stateMachine = StateMachineFactory.FACTORY.stateMachine();
            this.creator = new CreatorImpl(stateMachine, DomainFactory.FACTORY.domain());
            this.previewGenerator = new PreviewGenerator();
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
