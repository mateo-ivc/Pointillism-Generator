package artcreator.domain;

import artcreator.domain.port.Domain;
import artcreator.domain.port.IConfigService;

public interface DomainFactory {

	DomainFactory FACTORY = new DomainFacade();
	
	Domain domain();
	public IConfigService configService();

	}
