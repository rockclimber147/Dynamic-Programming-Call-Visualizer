package org.example.huffmanencodinggui.model.generators.canSum;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.example.huffmanencodinggui.model.generators.Displayable;
import org.example.huffmanencodinggui.model.generators.Layer;

import java.util.HashMap;

public class CanSumDisplay implements Displayable {
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

    public void display(HashMap<Layer, Group> layers, int xPosition, int yPosition, String content) {
        Text nodeText = new Text(xPosition, yPosition, content);
        formatText(nodeText);

        String color;
        if (found) {
            color = "green";
        } else {
            color = "lightgrey";
        }

        Circle circle = new Circle(xPosition, yPosition, nodeText.getLayoutBounds().getWidth(), Paint.valueOf(color));
        layers.get(Layer.NODE_LAYER).getChildren().add(circle);
        layers.get(Layer.CONTENT_LAYER).getChildren().add(nodeText);
    }
}
