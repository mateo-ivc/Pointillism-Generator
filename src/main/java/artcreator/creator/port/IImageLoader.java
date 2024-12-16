package artcreator.creator.port;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface IImageLoader {
    public BufferedImage loadImage(File file);
}
