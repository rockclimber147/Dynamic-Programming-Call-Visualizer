package org.example.huffmanencodinggui.model.generators.construct;

import org.example.huffmanencodinggui.model.generators.displays.rectangleNodes.RectangleDisplayColorFound;

public class ConstructDisplay extends RectangleDisplayColorFound {
    private String currentTarget;
    private String fragmentUsed;

    public void setCurrentTarget(String currentTarget) {
        this.currentTarget = currentTarget;
    }


    public void setFragmentUsed(String fragmentUsed) {
        this.fragmentUsed = fragmentUsed;
    }

    public String toString() {
        return currentTarget + "\n" + fragmentUsed;
    }
}
