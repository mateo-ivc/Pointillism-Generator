package artcreator.domain;

import artcreator.domain.impl.ColorPalette;
import artcreator.domain.impl.ConfigService;
import artcreator.domain.impl.DomainImpl;
import artcreator.domain.impl.TemplateConfig;
import artcreator.domain.port.Domain;
import artcreator.domain.port.IConfigService;

public class DomainFacade implements DomainFactory, Domain, IConfigService {


    private DomainImpl domain = new DomainImpl();

    private ConfigService configService;


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
    public void saveColorPalette(ColorPalette colorPalette) {

    }

    @Override
    public ColorPalette loadColorPalette() {
        return null;
    }
}
