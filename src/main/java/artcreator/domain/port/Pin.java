package artcreator.domain.port;

import java.awt.*;

public class Pin {
    public Pin(float diameter, Color color, int xPos, int yPos) {
        this.diameter = diameter;
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    private float diameter;
    private Color color;
    private int xPos;
    private int yPos;
}
