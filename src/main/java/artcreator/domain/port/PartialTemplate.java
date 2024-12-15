package artcreator.domain.port;

public class PartialTemplate implements Domain{
    private float templateHeight;
    private float templateWidth;
    private Pin[] pins;
    private int xPos;
    private int yPos;

    @Override
    public Object mkObject() {
        return new PartialTemplate();
    }
}
