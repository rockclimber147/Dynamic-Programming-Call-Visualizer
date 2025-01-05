package org.example.huffmanencodinggui.model.generators.sum;

import org.example.huffmanencodinggui.model.generators.displays.rectangleNodes.RectangleDisplayColorFound;

public class SumDisplayBasic extends RectangleDisplayColorFound {
    protected final int sum;
    protected final int value;

    public SumDisplayBasic(int sum, int value) {
        super();
        this.sum = sum;
        this.value = value;
    }



    public String toString() {
        return sum + ", " + value + "\n" + getFound();
    }
}
