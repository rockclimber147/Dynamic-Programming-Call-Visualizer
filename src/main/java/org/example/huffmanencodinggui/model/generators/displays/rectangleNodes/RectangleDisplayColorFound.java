package org.example.huffmanencodinggui.model.generators.displays.rectangleNodes;

import org.example.huffmanencodinggui.model.generators.displays.Displayable;

public class RectangleDisplayColorFound extends RectangleDisplayBasic {
    protected boolean found;

    protected RectangleDisplayColorFound() {
        this.found = false;
    }
    @Override
    protected String getColor() {
        if (found) return Displayable.LIGHT_GREEN;
        return Displayable.LIGHT_GREY;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isFound() {return found;}
}
