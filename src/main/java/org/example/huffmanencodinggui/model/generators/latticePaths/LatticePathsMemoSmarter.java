package org.example.huffmanencodinggui.model.generators.latticePaths;

public class LatticePathsMemoSmarter extends LatticePathsMemo {
    @Override
    public String getKey() {
        return "Lattice Paths Memo Smarter";
    }

    @Override
    public String generateKey(int rows, int cols) {
        return Math.min(rows, cols) + "," + Math.max(rows, cols);
    }
}
