package artcreator.domain;

import artcreator.domain.port.Domain;
import artcreator.domain.port.IConfigService;
import artcreator.domain.port.IImageLoadingService;

public interface DomainFactory {

	DomainFactory FACTORY = new DomainFacade();
	
	Domain domain();
	IConfigService configService();

	IImageLoadingService imageLoadingService();

	}
