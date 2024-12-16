package artcreator.creator;

import artcreator.creator.port.Creator;
import artcreator.creator.port.IGenerator;

public interface CreatorFactory {
	
	CreatorFactory FACTORY = new CreatorFacade();
	Creator creator();

	IGenerator generator();

}
