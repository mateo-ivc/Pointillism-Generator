package artcreator.creator.port;

import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public interface IGenerator {
    void savePrintableDocument(TemplateConfig config, BufferedImage input);

    BufferedImage generatePreview(TemplateConfig config, BufferedImage input);
    List<Color> quantizeImage(BufferedImage image, int n);
}
