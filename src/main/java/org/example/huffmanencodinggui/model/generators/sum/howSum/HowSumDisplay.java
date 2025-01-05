package org.example.huffmanencodinggui.model.generators.sum.howSum;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.example.huffmanencodinggui.model.generators.Layer;
import org.example.huffmanencodinggui.model.generators.sum.SumDisplayBasic;

import java.util.ArrayList;
import java.util.HashMap;

public class HowSumDisplay extends SumDisplayBasic {
    private final ArrayList<Integer> numbersUsed;
    public HowSumDisplay(int sum, int value) {
        super(sum, value);
        numbersUsed = new ArrayList<>();
    }

    public ArrayList<Integer> getNumbersUsed() { return numbersUsed; }

    public String toString() {
        return this.sum + "\n" + this.getNumbersUsed();
    }

    public void add(int number) {
        this.numbersUsed.add(number);
    }

    public void add(ArrayList<Integer> numbers) {
        this.numbersUsed.addAll(numbers);
    }

    public void display(HashMap<Layer, Group> layers, int xPosition, int yPosition, String content) {
        Text nodeText = new Text(xPosition, yPosition, content);
        centerTextOnPoint(nodeText);
        formatText(nodeText);
        layers.get(Layer.CONTENT_LAYER).getChildren().add(nodeText);

        Rectangle rect = makeRectangleBackground(nodeText, getColor());
        layers.get(Layer.NODE_LAYER).getChildren().add(rect);
    }
}
