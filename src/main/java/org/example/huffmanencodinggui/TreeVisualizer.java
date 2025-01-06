package org.example.huffmanencodinggui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import org.example.huffmanencodinggui.model.generators.displays.Layer;
import org.example.huffmanencodinggui.model.generators.TreeGenerator;
import org.example.huffmanencodinggui.model.generators.displays.Displayable;
import org.example.huffmanencodinggui.model.visualTree.Node;
import org.example.huffmanencodinggui.model.visualTree.TreeHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class TreeVisualizer extends VisualizerBase{
    private static int nodeLayerOffset = 50;
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
        btn.setOnAction(this::onButtonPress);

        Button clearButton = new Button();
        clearButton.setText("Clear");
        clearButton.setMinWidth(100);
        clearButton.setOnAction(this::onClearButtonPress);

        Button resetButton = new Button();
        resetButton.setText("Reset");
        resetButton.setMinWidth(100);
        resetButton.setOnAction(this::onResetButtonPress);

        this.argsField.setMinWidth( (double) APP_WIDTH / 2);
        this.displayBox.getChildren().addAll(argsField, generatorComboBox, btn, resetButton, clearButton);
        this.displayBox.setSpacing(0);
        stage.setTitle("Dynamic Programming!");
        stage.setScene(scene);
        stage.show();
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

    private void onClearButtonPress(ActionEvent event) {
        Platform.runLater(() -> {
            clearTree();
            argsField.setText("");
            resetScene();
        });
    }
    private void onResetButtonPress(ActionEvent event) {
        Platform.runLater(() -> {
            clearTree();
            if (currentGenerator != null) argsField.setText(currentGenerator.getExampleArgs());
            resetScene();
        });
    }

    private void resetScene() {
        stage.setScene(scene);

        nodeGroup.setScaleX(1);
        nodeGroup.setScaleY(1);

        nodeGroup.setTranslateX(0);
        nodeGroup.setTranslateY(0);
    }

    private void initGeneratorMap() {
        this.generators = TreeGenerator.getGenerators();

        ObservableList<String> keys = FXCollections.observableArrayList(this.generators.keySet());
        keys = FXCollections.observableArrayList(
                keys.stream()
                        .sorted(Comparator.naturalOrder()) // For alphabetical order
                        .toList()
        );
        generatorComboBox = new ComboBox<>(keys);
        generatorComboBox.setPromptText("Select an option");
        generatorComboBox.setOnAction(event -> {
            String selectedKey = generatorComboBox.getValue();
            this.currentGenerator = this.generators.get(selectedKey);

            if (argsField.getText().isEmpty()) {
                argsField.setText(currentGenerator.getExampleArgs());
            }
        });
    }



    private  <T extends Displayable> void loadTree(Node<T> root) {
        int rootX = APP_WIDTH / 2;
        nodeLayerOffset = 2 * TreeHelper.getMaxNodeHeight(root);
        loadTreeRecursive(root, rootX, nodeLayerOffset, rootX, nodeLayerOffset);
    }

    private <T extends Displayable> void loadNode(Node<T> node, int xPosition, int yPosition) {
        T displayable = node.getContent();
        displayable.display(layers, xPosition, yPosition, node.getContentString());
    }

    private <T extends Displayable> void loadTreeRecursive(Node<T> node, int parentX, int parentY, int nodeX, int nodeY) {
        getLayer(Layer.LINES_LAYER).getChildren().add(new Line(parentX, parentY, nodeX, nodeY));
        loadNode(node, nodeX, nodeY);

        int leftBound = nodeX - TreeHelper.getWidth(node)/2;


        ArrayList<Node<T>> children = node.getChildren();
        int childRowYCoordinate = nodeY + nodeLayerOffset;


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