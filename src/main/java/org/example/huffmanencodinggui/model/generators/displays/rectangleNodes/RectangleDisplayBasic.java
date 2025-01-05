package org.example.huffmanencodinggui.model.generators.displays.rectangleNodes;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.example.huffmanencodinggui.model.generators.displays.Displayable;
import org.example.huffmanencodinggui.model.generators.displays.Layer;

import java.util.HashMap;

public abstract class RectangleDisplayBasic implements Displayable {
    public void display(HashMap<Layer, Group> layers, int xPosition, int yPosition, String content) {
        Text nodeText = new Text(xPosition, yPosition, content);
        formatText(nodeText);
        centerTextOnPoint(nodeText);
        layers.get(Layer.CONTENT_LAYER).getChildren().add(nodeText);

        Rectangle rect = makeRectangleBackground(nodeText, getColor());
        layers.get(Layer.NODE_LAYER).getChildren().add(rect);
    }

    abstract String getColor();
}
