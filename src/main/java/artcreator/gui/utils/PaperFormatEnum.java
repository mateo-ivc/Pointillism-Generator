package artcreator.gui.utils;

import java.awt.*;

public enum PaperFormatEnum {
    A4(new Dimension(210, 297)),
    A3(new Dimension(297, 420)),
    A2(new Dimension(420, 594));

    private Dimension size;

    PaperFormatEnum(Dimension size) {
        this.size = size;
    }

    public Dimension getDimension() {
        return size;
    }
}
