package artcreator.domain.impl;

import artcreator.domain.port.Template;

public class TemplateConfig {
    // Kann man doch bestimmt irgendwie zusammenfassen, als wieder jedes einzelne Attribut aufzulisten

    // pinDiameter und pinDistance kann man doch auch im Template selbst speichern?
    private float pinDiameter;
    private float pinDistance;
    private ColorPalette colorPalette;
    private String partialTemplateFormat;
    private float templateHeight;
    private float templateWidth;

    private Template[] template;

    public void serialize() {
    }

    public void deserialize() {
    }


}
