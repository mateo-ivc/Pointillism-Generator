package artcreator.creator.impl;

import artcreator.domain.impl.PartialTemplate;
import artcreator.domain.impl.Pin;
import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PreviewGeneratorImpl extends Generator {
    @Override
    public Template generateTemplate(TemplateConfig config, BufferedImage input) {

        //Todo scale image
        //TODO apply dither
        //TODO Generate Pin Positions
        //TODO Map color to pins

        //Insegesammte Bild länge in pixel: Format Bildlänge / Pin Distance
        // Scale Faktor:

        BufferedImage scaledInput = scaleImage(config, input);

        List<Color> colorPalette = MedianCutColorGenerator.extractColors(scaledInput, 5);

        BufferedImage ditheredImage = FloydSteinbergDitherer.dither(scaledInput, colorPalette);

        Template template = new Template();
        PartialTemplate whole = new PartialTemplate();
        whole.setxPos(0);
        whole.setyPos(0);
        whole.setTemplateHeight(ditheredImage.getHeight());
        whole.setTemplateWidth(ditheredImage.getWidth());
        template.setParts(new PartialTemplate[]{whole});

        List<Pin> pins = new ArrayList<>();

        for (int y = 0; y < ditheredImage.getHeight(); y++) {
            for (int x = 0; x < ditheredImage.getWidth(); x++) {
                pins.add(new Pin(1, new Color(ditheredImage.getRGB(x,y)), x, y));
            }
        }

        whole.setPins(pins.toArray(new Pin[]{}));

        return template;
    }

    public static BufferedImage scaleImage(TemplateConfig config, BufferedImage input) {
        int scaledImageWidth = (int)(config.getTemplateWidth() / config.getPinDistance());
        int scaledImageHeight = (int)(config.getTemplateHeight() / config.getPinDistance());
        float heightScale = (float) scaledImageHeight / input.getHeight();
        float widthScale = (float) scaledImageWidth / input.getWidth();

        BufferedImage scaledInput = new BufferedImage(scaledImageWidth, scaledImageHeight, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(widthScale, heightScale);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        scaledInput = scaleOp.filter(input, scaledInput);
        return scaledInput;
    }


}
