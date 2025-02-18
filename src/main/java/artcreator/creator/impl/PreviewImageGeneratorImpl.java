package artcreator.creator.impl;

import artcreator.domain.impl.Pin;
import artcreator.domain.impl.Template;

import java.awt.image.BufferedImage;

public class PreviewImageGeneratorImpl {
    public BufferedImage generatePreviewFromTemplate(Template template){
        BufferedImage output = new BufferedImage((int)template.getParts()[0].getTemplateWidth(), (int)template.getParts()[0].getTemplateHeight(), BufferedImage.TYPE_INT_RGB);
        for (Pin pin : template.getParts()[0].getPins()) {
            output.setRGB(pin.getxPos(), pin.getyPos(), pin.getColor().getRGB());
        }
        return output;
    }
}
