package artcreator.creator;

import artcreator.creator.port.Creator;
import artcreator.creator.port.IGenerator;
import artcreator.creator.port.IImageLoader;

public interface CreatorFactory {
	
	CreatorFactory FACTORY = new CreatorFacade();
	Creator creator();

	IGenerator generator();


	IImageLoader imageLoader();
}
