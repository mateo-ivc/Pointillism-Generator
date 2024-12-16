package artcreator.creator.port;

import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;

public interface IGenerator {
    Template generatePrintableDocument(TemplateConfig config);

    Template generatePreview(TemplateConfig config);
}
