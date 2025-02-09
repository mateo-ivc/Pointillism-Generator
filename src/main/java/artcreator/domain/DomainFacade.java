package artcreator.domain;

import artcreator.domain.impl.*;
import artcreator.domain.port.Domain;
import artcreator.domain.port.IConfigService;
import artcreator.domain.port.IImageLoadingService;
import artcreator.statemachine.StateMachineFacade;
import artcreator.statemachine.port.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DomainFacade implements DomainFactory, Domain, IConfigService, IImageLoadingService {


    private DomainImpl domain = new DomainImpl();

    private ConfigService configService;
    private ImageLoaderImpl imageLoader;


    @Override
    public synchronized Domain domain() {
        if (this.domain == null) {
            this.domain = new DomainImpl();
        }
        return this;
    }

    @Override
    public synchronized IConfigService configService() {
        if (this.configService == null) {
            this.configService = new ConfigService();
            this.configService.setTemplateConfig(new TemplateConfig());
        }
        return this;
    }

    @Override
    public IImageLoadingService imageLoadingService() {
        if(this.imageLoader == null){
            this.imageLoader = new ImageLoaderImpl();
        }
        return this;
    }


    @Override
    public synchronized Object mkObject() {
        return this.domain.mkObject();
    }


    @Override
    public void saveConfigToFile(TemplateConfig templateConfig) {

    }

    @Override
    public TemplateConfig loadConfigFromFile() {
        return null;
    }

    @Override
    public TemplateConfig getCurrentConfig() {
        return this.configService.getTemplateConfig();
    }

    @Override
    public void saveColorPalette(ColorPalette colorPalette) {

    }

    @Override
    public ColorPalette loadColorPalette() {
        return null;
    }

    @Override
    public ColorPalette getCurrentColorPalette() {
        return null;
    }


    @Override
    public BufferedImage loadImage(Component parent) {
        BufferedImage bufferedImage = this.imageLoader.loadImage(parent);
        StateMachineFacade.FACTORY.stateMachine().setState(State.S.EDIT_PARAMETERS);
        return bufferedImage;
    }
}
