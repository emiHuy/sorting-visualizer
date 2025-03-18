package views;

import components.AlgorithmPane;
import components.InputPaneBottom;
import components.InputPaneTop;
import javafx.scene.layout.Pane;
import models.Element;

public class SortingVisualizerView extends Pane {
    private InputPaneTop inputPaneTop;
    private AlgorithmPane algorithmPane;
    private InputPaneBottom inputPaneBottom;

    public SortingVisualizerView() {
        inputPaneTop = new InputPaneTop();
        inputPaneTop.relocate(109, 40);

        algorithmPane = new AlgorithmPane();
        algorithmPane.relocate(35, 100);

        inputPaneBottom = new InputPaneBottom();
        inputPaneBottom.relocate(35, 650);
        getChildren().addAll(inputPaneTop, algorithmPane, inputPaneBottom);
    }

    public InputPaneTop getInputPaneTop() {
        return inputPaneTop;
    }

    public InputPaneBottom getInputPaneBottom() {
        return inputPaneBottom;
    }

    public void reset() {
        inputPaneTop.enable();
        inputPaneBottom.getSpeedSlider().setDisable(false);
        inputPaneBottom.buttonVisibility(false);
    }

    public void updateVisualizer(Element[] elements) {
        algorithmPane.update(elements);
    }

    public void updateInputComponentsForSort() {
        inputPaneTop.disable();
        inputPaneBottom.buttonVisibility(true);
        inputPaneBottom.getPlayButton().setDisable(true);
        inputPaneBottom.getSpeedSlider().setDisable(true);
    }
}
