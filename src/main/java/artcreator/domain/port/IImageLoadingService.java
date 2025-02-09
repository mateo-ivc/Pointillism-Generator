package artcreator.domain.port;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IImageLoadingService {
    BufferedImage loadImage(Component parent);
}
