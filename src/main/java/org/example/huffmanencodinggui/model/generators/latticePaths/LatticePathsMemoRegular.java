package org.example.huffmanencodinggui.model.generators.latticePaths;

public class LatticePathsMemoRegular extends LatticePathsMemo {
    @Override
    public String getKey() {
        return "Lattice Paths Memo Regular";
    }

    @Override
    public String generateKey(int rows, int cols) {
        return rows + "'" + cols;
    }
}
