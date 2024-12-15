package artcreator.domain.port;

import java.awt.*;

public class Pin implements Domain {

    private float diameter; //measured in mm
    private Color color;
    private int xPos;
    private int yPos;

    // Constructor for creating a Pin with specific properties
    public Pin(float diameter, Color color, int xPos, int yPos) {
        this.diameter = diameter;
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    @Override
    public Object mkObject() {
        return new Pin(2.0f, Color.BLACK, 0, 0);
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}