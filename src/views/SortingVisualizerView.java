package views;

import components.AlgorithmPane;
import components.InputPaneBottom;
import components.InputPaneTop;
import javafx.scene.layout.Pane;
import models.ElementArray;

public class SortingVisualizerView extends Pane {
    private final InputPaneTop inputPaneTop;
    private final AlgorithmPane algorithmPane;
    private final InputPaneBottom inputPaneBottom;
    private ElementArray model;

    public SortingVisualizerView(ElementArray model) {
        this.model = model;

        // Create the top input pane
        inputPaneTop = new InputPaneTop();
        inputPaneTop.relocate(109, 40);

        // Create the algorithm pane - this pane visually demonstrates the sorting processes of different sorting algorithms
        algorithmPane = new AlgorithmPane();
        algorithmPane.relocate(35, 100);

        // Create the bottom input pane
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

    public String getSortType() {
        return inputPaneTop.getSortChoiceBox().getValue();
    }

    public double getSortSpeed() {
        return inputPaneBottom.getSpeedSlider().getValue();
    }

    public void displayCurrentElementOrder() {
        algorithmPane.update(model.getSorter().getKeyFrameArr());
    }

    public void displayUnsortedElements() {
        algorithmPane.update(model.getElements());
    }

    /**
     * Update component states for sorting.
     */
    public void disableInputComponents(boolean v) {
        inputPaneTop.setComponentsDisabled(v);
        inputPaneBottom.buttonVisibility(v);
        inputPaneBottom.getPlayButton().setDisable(v);
        inputPaneBottom.getSpeedSlider().setDisable(v);
    }

    public void sortIsPaused(boolean v) {
        inputPaneBottom.getPlayButton().setDisable(!v);
        inputPaneBottom.getPauseButton().setDisable(v);
    }
}
