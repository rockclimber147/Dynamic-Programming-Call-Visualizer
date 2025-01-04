package org.example.huffmanencodinggui.model.generators.latticePaths;

import org.example.huffmanencodinggui.model.generators.TreeGenerator;

public abstract class LatticePaths extends TreeGenerator<LatticePathsDisplay> {
    private int rows;
    private int columns;
    @Override
    public String getErrorMessage() {
        return "Arguments need to be integers separated by a comma";
    }

    @Override
    public void parseArgs(String args) {
        String[] argList = args.split(",");
        this.rows = Integer.parseInt(argList[0]);
        this.columns = Integer.parseInt(argList[1]);
    }

    @Override
    public void doWork() {
        latticePaths(rows, columns);
    }

    public abstract LatticePathsDisplay latticePaths(int rows, int columns);
}
