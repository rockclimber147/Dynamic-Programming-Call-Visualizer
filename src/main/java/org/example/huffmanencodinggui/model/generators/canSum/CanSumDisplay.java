package org.example.huffmanencodinggui.model.generators.canSum;

public class CanSumDisplay {
    private final int sum;
    private final int value;
    private boolean found;

    public CanSumDisplay(int sum, int value) {
        this.sum = sum;
        this.value = value;
        this.found = false;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean getFound() {return found;}

    public String toString() {
        return sum + ", " + value + "\n" + getFound();
    }
}
