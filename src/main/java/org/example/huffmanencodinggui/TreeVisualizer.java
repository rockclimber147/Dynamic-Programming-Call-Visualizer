package org.example.huffmanencodinggui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.example.huffmanencodinggui.model.generators.TreeGenerator;
import org.example.huffmanencodinggui.model.visualTree.Node;
import org.example.huffmanencodinggui.model.visualTree.TreeHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class TreeVisualizer extends VisualizerBase{
    private final int NODE_LAYER_OFFSET = 50;
    private TextField argsField;
    ComboBox<String> generatorComboBox;

    private HashMap<String, TreeGenerator<?>> generators;
    private TreeGenerator<?> currentGenerator;

    public void start(Stage stage) {
        this.initScene(stage);
        this.initGeneratorMap();
        this.argsField = new TextField();

        Button btn = new Button();
        btn.setText("Generate");
        btn.setMinWidth(100);

        this.argsField.setMinWidth( (double) APP_WIDTH / 2);
        this.displayBox.getChildren().addAll(argsField, generatorComboBox, btn);
        this.displayBox.setSpacing(0);
        stage.setTitle("Dynamic Programming!");
        stage.setScene(scene);
        stage.show();

        btn.setOnAction(this::onButtonPress);
    }

    private void initGeneratorMap() {
        this.generators = TreeGenerator.getGenerators();

        ObservableList<String> keys = FXCollections.observableArrayList(this.generators.keySet());
        generatorComboBox = new ComboBox<>(keys);
        generatorComboBox.setPromptText("Select an option");
        generatorComboBox.setOnAction(event -> {
            String selectedKey = generatorComboBox.getValue();
            this.currentGenerator = this.generators.get(selectedKey);
        });
    }

    private void onButtonPress(ActionEvent event) {
        Platform.runLater(() -> {
            clearTree();
            if (currentGenerator == null) return;
            String args = argsField.getText();
            try {
                loadTree(currentGenerator.call(args));
                drawTree();
            } catch (IllegalArgumentException e) {
                argsField.setText(e.getMessage());
            }


            stage.setScene(scene);
        });
    }

    private  <T> void loadTree(Node<T> root) {
        int rootX = APP_WIDTH / 2;
        loadTreeRecursive(root, rootX, NODE_LAYER_OFFSET, rootX, NODE_LAYER_OFFSET);
    }

    private <T> void loadNode(Node<T> node, int xPosition, int yPosition) {
        Text nodeText = new Text(xPosition, yPosition, node.getContentString());
        String NODE_TEXT_STYLE = "-fx-font-weight: bold";
        nodeText.setStyle(NODE_TEXT_STYLE);
        nodeText.setTextAlignment(TextAlignment.CENTER);
        nodeText.setTranslateX(nodeText.getTranslateX() - (nodeText.getLayoutBounds().getWidth() / 2));
        nodeText.setTranslateY(nodeText.getTranslateY() + (nodeText.getLayoutBounds().getHeight() / 4));

        Circle circle = new Circle(xPosition, yPosition, nodeText.getLayoutBounds().getWidth(), Paint.valueOf("lightgrey"));


        this.nodeBackground.getChildren().add(circle);
        this.nodeText.getChildren().add(nodeText);
    }

    private <T> void loadTreeRecursive(Node <T> node, int parentX, int parentY, int nodeX, int nodeY) {
        this.nodeLines.getChildren().add(new Line(parentX, parentY, nodeX, nodeY));
        loadNode(node, nodeX, nodeY);

        int leftBound = nodeX - TreeHelper.getWidth(node)/2;


        ArrayList<Node<T>> children = node.getChildren();
        int childRowYCoordinate = nodeY + NODE_LAYER_OFFSET;


        for (Node<T> child : children) {
            int childWidth = TreeHelper.getWidth(child);

            loadTreeRecursive(child, nodeX, nodeY, leftBound + childWidth / 2, childRowYCoordinate);
            leftBound += childWidth;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}