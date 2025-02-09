package artcreator.domain.impl;


import artcreator.gui.utils.PaperFormatEnum;

public class TemplateConfig {
    // Kann man doch bestimmt irgendwie zusammenfassen, als wieder jedes einzelne Attribut aufzulisten

    // pinDiameter und pinDistance kann man doch auch im Template selbst speichern?
    private float pinDiameter = 2;
    private float pinDistance = 10;
    private ColorPalette colorPalette = new ColorPalette();
    private PaperFormatEnum partialTemplateFormat = PaperFormatEnum.A4;
    private float templateHeight = 1000;
    private float templateWidth = 1000;

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

    
    public PaperFormatEnum getPartialTemplateFormat() {
        return partialTemplateFormat;
    }

    
    public void setPartialTemplateFormat(PaperFormatEnum partialTemplateFormat) {
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
