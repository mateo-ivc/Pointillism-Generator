package artcreator.creator.port;

import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;

import java.awt.image.BufferedImage;

public interface IGenerator {
    Template generatePrintableDocument(TemplateConfig config, BufferedImage input);

    BufferedImage generatePreview(TemplateConfig config, BufferedImage input);
}
