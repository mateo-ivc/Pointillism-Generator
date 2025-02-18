package artcreator.creator.impl;


import artcreator.domain.impl.Pin;
import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class TemplateGenerator extends PreviewGeneratorImpl {

    public static int PRINT_RESOLUTION_HEIGHT = 3508;
    public static int PRINT_RESOLUTION_WIDTH = 2480;

    public BufferedImage generateTemplateImage(TemplateConfig config, BufferedImage input) {

        BufferedImage scaledInput = PreviewGeneratorImpl.scaleImage(config, input);
        List<Color> colors = MedianCutColorGenerator.extractColors(scaledInput, 5);

        Template template = super.generateTemplate(config, input);
        BufferedImage bufferedImage = new BufferedImage(PRINT_RESOLUTION_WIDTH, PRINT_RESOLUTION_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, PRINT_RESOLUTION_WIDTH, PRINT_RESOLUTION_HEIGHT);
        for (Pin pin : template.getParts()[0].getPins()) {
            float relativeX = pin.getxPos() / template.getParts()[0].getTemplateWidth();
            float relativeY = pin.getyPos() / template.getParts()[0].getTemplateHeight();
            int actualX = (int) (relativeX * PRINT_RESOLUTION_WIDTH);
            int actualY = (int) (relativeY * PRINT_RESOLUTION_HEIGHT);

            g.setColor(pin.getColor());
            g.drawLine(actualX - 5, actualY - 5, actualX + 5, actualY + 5);
            g.drawLine(actualX - 5, actualY + 5, actualX + 5, actualY - 5);
            g.drawString(String.valueOf(colors.indexOf(pin.getColor())), actualX + 7, actualY + 7);

        }
        return bufferedImage;
    }
}
