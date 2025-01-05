package org.example.huffmanencodinggui.model.generators.sum.bestSum;

import org.example.huffmanencodinggui.model.generators.displays.Displayable;
import org.example.huffmanencodinggui.model.generators.sum.SumDisplayWithNumbersUsed;

public class BestSumDisplay extends SumDisplayWithNumbersUsed {
    private boolean isBestSolution;
    public BestSumDisplay(int sum, int value) {
        super(sum, value);
        this.isBestSolution = false;
    }

    @Override
    protected String getColor() {
        if (isBestSolution) return Displayable.GREEN;
        if (found) return Displayable.LIGHT_GREEN;
        return Displayable.LIGHT_GREY;
    }

    public boolean isBestSolution() {
        return isBestSolution;
    }

    public void setBestSolution(boolean bestSolution) {
        isBestSolution = bestSolution;
    }
}
