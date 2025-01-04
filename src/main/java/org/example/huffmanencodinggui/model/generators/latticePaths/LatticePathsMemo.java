package org.example.huffmanencodinggui.model.generators.latticePaths;

import java.util.HashMap;

public abstract class LatticePathsMemo extends LatticePaths {
    HashMap<String, LatticePathsDisplay> memo;

    @Override
    public LatticePathsDisplay latticePaths(int rows, int columns) {
        this.memo = new HashMap<>();
        return latticePathsMemo(rows, columns);
    }

    private LatticePathsDisplay latticePathsMemo(int rows, int cols) {
        caller.callFunction();
        LatticePathsDisplay display = new LatticePathsDisplay(rows, cols);
        String key = generateKey(rows, cols);
        if (this.memo.containsKey(key)) {
            return caller.returnValue(memo.get(key));
        }

        if (rows == 0 || cols == 0) {
            display.setPaths(0);
            memo.put(key, display);
            return caller.returnValue(display);
        }

        if (rows == 1 && cols == 1) {
            display.setPaths(1);
            memo.put(key, display);
            return caller.returnValue(display);
        }

        int value = latticePathsMemo(rows - 1, cols).getPaths() + latticePathsMemo(rows, cols - 1).getPaths();
        display.setPaths(value);
        memo.put(key, display);
        return caller.returnValue(display);
    }

    public abstract String generateKey(int rows, int cols);
}
