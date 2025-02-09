package artcreator.domain.impl;

import artcreator.domain.port.Domain;

public class Template {
    private PartialTemplate[] parts;
    private float boarderSize;

    public PartialTemplate[] getParts() {
        return parts;
    }

    public void setParts(PartialTemplate[] parts) {
        this.parts = parts;
    }

    public float getBoarderSize() {
        return boarderSize;
    }

    public void setBoarderSize(float boarderSize) {
        this.boarderSize = boarderSize;
    }
}
