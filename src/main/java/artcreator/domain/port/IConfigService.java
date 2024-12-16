package artcreator.domain.port;

import artcreator.domain.impl.ColorPalette;
import artcreator.domain.impl.TemplateConfig;

public interface IConfigService {
    void saveConfigToFile(TemplateConfig templateConfig);
    TemplateConfig loadConfigFromFile();

    void saveColorPalette(ColorPalette colorPalette);
    ColorPalette loadColorPalette();

}
