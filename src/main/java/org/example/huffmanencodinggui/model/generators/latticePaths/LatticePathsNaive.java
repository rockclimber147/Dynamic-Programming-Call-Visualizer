package org.example.huffmanencodinggui.model.generators.latticePaths;

public class LatticePathsNaive extends LatticePaths {
    @Override
    public String getKey() {
        return "Lattice Paths Naive";
    }

    @Override
    public LatticePathsDisplay latticePaths(int rows, int columns) {
        this.caller.callFunction();
        LatticePathsDisplay display = new LatticePathsDisplay(rows, columns);
        if (rows == 0 || columns == 0) {
            display.setPaths(0);
            return caller.returnValue(display);
        }

        if (rows == 1 && columns == 1) {
            display.setPaths(1);
            return caller.returnValue(display);
        }
        display.setPaths(latticePaths(rows - 1, columns).getPaths()
        + latticePaths(rows, columns - 1).getPaths());

        return caller.returnValue(display);
    }
}
