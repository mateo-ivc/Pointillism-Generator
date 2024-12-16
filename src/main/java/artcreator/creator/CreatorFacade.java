package artcreator.creator;

import artcreator.creator.impl.CreatorImpl;
import artcreator.creator.impl.PreviewGenerator;
import artcreator.creator.impl.TemplateGenerator;
import artcreator.creator.port.Creator;
import artcreator.creator.port.IGenerator;
import artcreator.domain.DomainFactory;
import artcreator.domain.impl.Template;
import artcreator.domain.impl.TemplateConfig;
import artcreator.statemachine.StateMachineFactory;
import artcreator.statemachine.port.StateMachine;
import artcreator.statemachine.port.State.S;

public class CreatorFacade implements CreatorFactory, Creator, IGenerator {

    private CreatorImpl creator;
    private StateMachine stateMachine;

    private PreviewGenerator previewGenerator;

    private TemplateGenerator templateGenerator;

    @Override
    public Creator creator() {
        return init();
    }
    @Override
    public IGenerator generator() {
        return init();
    }

    @Override
    public synchronized void sysop(String str) {
        if (this.stateMachine.getState().isSubStateOf(S.CREATE_TEMPLATE /* choose right state*/))
            this.creator.sysop(str);
    }

    @Override
    public Template generatePrintableDocument(TemplateConfig config) {
        return templateGenerator.generateTemplate(config);
    }

    @Override
    public Template generatePreview(TemplateConfig config) {
        return previewGenerator.generateTemplate(config);
    }

    private CreatorFacade init() {
        if (this.creator == null) {
            this.stateMachine = StateMachineFactory.FACTORY.stateMachine();
            this.creator = new CreatorImpl(stateMachine, DomainFactory.FACTORY.domain());
            this.previewGenerator = new PreviewGenerator();
            this.templateGenerator = new TemplateGenerator();
        }
        return this;
    }
}
