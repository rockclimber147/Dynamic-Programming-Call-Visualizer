package org.example.huffmanencodinggui.model.generators.latticePaths;

import org.example.huffmanencodinggui.model.generators.Displayable;

public class LatticePathsDisplay implements Displayable {
    private final int rows;
    private final int cols;
    private long paths;

    public LatticePathsDisplay(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.paths = -1;
    }

    public void setPaths(long paths) {
        this.paths = paths;
    }
    public long getPaths() { return this.paths;}

    public String toString(){
        return paths + "\n" + rows + "," + cols;
    }
}
