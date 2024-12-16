package artcreator.creator.impl;

import artcreator.domain.impl.ColorPalette;
import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;

abstract class Generator {
    protected float pinDiameter;
    protected float pinDistance;
    protected float height;
    protected float width;
    protected float printFormatWidth;
    protected float printFormatHeight;
    protected ColorPalette colors;

    public abstract Template generateTemplate(TemplateConfig config);
}
