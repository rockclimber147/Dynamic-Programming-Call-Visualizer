package org.example.huffmanencodinggui.model.generators.construct;

import org.example.huffmanencodinggui.model.generators.displays.rectangleNodes.RectangleDisplayColorFound;

import java.util.HashMap;

public class ConstructDisplay extends RectangleDisplayColorFound {
    private String currentTarget;
    private String fragmentUsed;
    private boolean found;

    public String getCurrentTarget() {
        return currentTarget;
    }

    public void setCurrentTarget(String currentTarget) {
        this.currentTarget = currentTarget;
    }

    public String getFragmentUsed() {
        return fragmentUsed;
    }

    public void setFragmentUsed(String fragmentUsed) {
        this.fragmentUsed = fragmentUsed;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String toString() {
        return currentTarget + "\n" + fragmentUsed;
    }
}
