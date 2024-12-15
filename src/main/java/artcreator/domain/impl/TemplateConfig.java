package artcreator.domain.impl;

import artcreator.domain.port.Template;

public class TemplateConfig {
    // Kann man doch bestimmt irgendwie zusammenfassen, als wieder jedes einzelne Attribut aufzulisten

    // pinDiameter und pinDistance kann man doch auch im Template selbst speichern?
    private float pinDiameter;
    private float pinDistance;
    private ColorPalette colorPalette = new ColorPalette();
    private String partialTemplateFormat = "A4";
    private float templateHeight;
    private float templateWidth;

    private boolean mirrored;

    private Template[] template;

    public TemplateConfig() {
        System.out.println("Created Template Config");
    }

    public float getPinDiameter() {
        return pinDiameter;
    }

    public void setPinDiameter(float pinDiameter) {
        this.pinDiameter = pinDiameter;
    }

    public float getPinDistance() {
        return pinDistance;
    }

    public void setPinDistance(float pinDistance) {
        this.pinDistance = pinDistance;
    }

    public ColorPalette getColorPalette() {
        return colorPalette;
    }

    public void setColorPalette(ColorPalette colorPalette) {
        this.colorPalette = colorPalette;
    }

    public String getPartialTemplateFormat() {
        return partialTemplateFormat;
    }

    public void setPartialTemplateFormat(String partialTemplateFormat) {
        this.partialTemplateFormat = partialTemplateFormat;
    }

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

    public Template[] getTemplate() {
        return template;
    }

    public void setTemplate(Template[] template) {
        this.template = template;
    }

    public boolean isMirrored(){
        return mirrored;
    }

    public void setMirrored(boolean mirrored){
        this.mirrored = mirrored;
    }

    public void serialize() {
    }

    public void deserialize() {
    }


}
