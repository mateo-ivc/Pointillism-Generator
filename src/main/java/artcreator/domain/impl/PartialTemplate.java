package artcreator.domain.impl;


public class PartialTemplate {
    private float templateHeight;
    private float templateWidth;
    private Pin[] pins;
    private int xPos;
    private int yPos;

    public float getTemplateHeight() {
        return templateHeight;
    }

    public void setTemplateHeight(float templateHeight) {
        this.templateHeight = templateHeight;
    }

    public float getTemplateWidth() {
        return templateWidth;
    }

    public void setTemplateWidth(float templateWidth) {
        this.templateWidth = templateWidth;
    }

    public Pin[] getPins() {
        return pins;
    }

    public void setPins(Pin[] pins) {
        this.pins = pins;
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
